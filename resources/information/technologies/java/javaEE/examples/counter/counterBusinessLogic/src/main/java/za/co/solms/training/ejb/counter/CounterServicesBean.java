/**
 * 
 */
package za.co.solms.training.ejb.counter;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author fritz@solms.co.za
 *
 */
@Stateless
public class CounterServicesBean implements CounterServices
{

	/* (non-Javadoc)
	 * @see za.co.solms.training.ejb.counter.CounterServices#createCounter(java.lang.String)
	 */
	@Override
	public Counter createCounter(String counterName)
			throws DuplicateCounterException
	{
		try
		{
			this.getCounter(counterName);
			throw new DuplicateCounterException();
		}
		catch (NoSuchCounterException e)
		{
			Counter counter = new Counter(counterName);
			logger.info("Persisting " + counter);
			entityManager.persist(counter);
			return counter;
		}	
	}

	/* (non-Javadoc)
	 * @see za.co.solms.training.ejb.counter.CounterServices#incrementCounter(java.lang.String)
	 */
	@Override
	public long incrementCounter(String counterName)
			throws NoSuchCounterException
	{
		Counter counter = getCounter(counterName);
		counter.increment();
		return counter.getCount();
	}

	/* (non-Javadoc)
	 * @see za.co.solms.training.ejb.counter.CounterServices#getCount(java.lang.String)
	 */
	@Override
	public long getCount(String counterName) throws NoSuchCounterException
	{
		Counter counter = getCounter(counterName);
		return counter.getCount();
	}
	
	public void updateCounter(Counter counter)
	{
		entityManager.merge(counter);
	}
	
	public void removeCounter(String counterName)
	{
		try
		{
			Counter counter = getCounter(counterName);

			entityManager.remove(entityManager.merge(counter));
		}
		catch (NoSuchCounterException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public Counter getCounter(String counterName) throws NoSuchCounterException
	{
		Query query = entityManager.createNamedQuery("findCounterByName");
		query.setParameter("counterName", counterName);
		List<Counter> counters = query.getResultList();
		if (counters.size() == 0)
			throw new NoSuchCounterException();
		else
			return counters.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Counter> getCounters()
	{
		return (List<Counter>)entityManager.createNamedQuery("findAllCounters").getResultList();
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(CounterServicesBean.class.getName());
}
