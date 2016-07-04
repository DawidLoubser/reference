/**
 * 
 */
package za.co.solms.weather;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import za.co.solms.location.Location;

/**
 * @author fritz@solms.co.za
 *
 */
@Local(WeatherServices.class)
@Stateless
@WebService(endpointInterface = "za.co.solms.weather.WeatherServices",serviceName="WeatherService")
public class WeatherServicesBean implements WeatherServices
{
	public WeatherServicesBean () {}

	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#getAverageTemperature(za.co.solms.location.Location, java.util.Date, java.util.Date)
	 */
	@Override
	@WebMethod
	public double getAverageTemperature(Location location, Date onOrBefore, Date after)
			throws NoWeatherReadingsException
	{
		Collection<WeatherReading> readings 
			= this.getWeatherReadingsForLocation(location, onOrBefore, after);
		
		if (readings.isEmpty())
			throw new NoWeatherReadingsException();
		
		double sum = 0;
		for (WeatherReading weatherReading : readings)
			sum += weatherReading.getTemperature();
		return sum / readings.size();
	}

	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#getWeatherReadingsForLocation(za.co.solms.location.Location)
	 */
	@Override
	public List<WeatherReading> getWeatherReadingsForLocation(Location location) 
	{
	    // Create a criteria query which yields weather readings,
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<WeatherReading> criteriaQuery 
	      = criteriaBuilder.createQuery(WeatherReading.class);
	    
	    // The root of the query tree is a weather reading
	    Root<WeatherReading> weatherReading = criteriaQuery.from(WeatherReading.class);
	    
	    // Define a predicate which affirms the the location of a weather reading is the requested location
	    Predicate atLocation 
	      = criteriaBuilder.equal(weatherReading.get(WeatherReading_.location), location);
	      
	    // Add a WHERE clause for the predicate  
	    criteriaQuery.where(atLocation);
	 
	    criteriaQuery.orderBy(criteriaBuilder.desc(weatherReading.get(WeatherReading_.temperature)));

	    //Create a JPQL query from the criteria query and execute it:
	    TypedQuery<WeatherReading> query = entityManager.createQuery(criteriaQuery);
	    return query.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#getWeatherReadingsForLocation(za.co.solms.location.Location, java.util.Date, java.util.Date)
	 */
	@Override
	@SuppressWarnings("unchecked")
	@WebMethod
	public List<WeatherReading> getWeatherReadingsForLocation(Location location,
			Date after, Date onOrBefore)
	{
		Query query = entityManager.createNamedQuery("findAllWeatherReadingsForLocationAndPeriod");
		query.setParameter("location", location);
		query.setParameter("after", after);
		query.setParameter("onOrBefore", onOrBefore);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#persistWeatherReading(za.co.solms.weather.WeatherReading)
	 */
	@Override
	@WebMethod
	public WeatherReading persistWeatherReading(WeatherReading weatherReading)
	{
		entityManager.persist(weatherReading);
		return weatherReading;
	}

	@Override
	@WebMethod
	public WeatherReading updateWeatherReading(WeatherReading weatherReading)
	{
		return entityManager.merge(weatherReading);
	}

	@Override
	@WebMethod
	public void removeAllWeatherReadings()
	{
		for (WeatherReading weatherReading: this.getAllWeatherReadings())
			this.removeWeatherReading(weatherReading);
	}

	@Override
	@WebMethod
	public void removeWeatherReading(WeatherReading weatherReading)
	{
		entityManager.remove(entityManager.merge(weatherReading));
	}

	@Override
	@SuppressWarnings("unchecked")
	@WebMethod
	public List<WeatherReading> getAllWeatherReadings()
	{
		Query query = entityManager.createNamedQuery("findAllWeatherReadings");
		logger.info("query = " + query);
		return query.getResultList();
	}

	@Override
	@WebMethod
	public WeatherReading getWeatherReading(int id)
			throws NoWeatherReadingsException
	{
		return entityManager.find(WeatherReading.class, id);
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(WeatherServicesBean.class.getName());
}
