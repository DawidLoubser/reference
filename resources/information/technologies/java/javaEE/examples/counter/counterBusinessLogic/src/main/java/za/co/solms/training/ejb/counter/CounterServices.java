/**
 * 
 */
package za.co.solms.training.ejb.counter;

import java.util.List;

/**
 * @author fritz@solms.co.za
 *
 */
public interface CounterServices
{
	public Counter createCounter(String counterName)
						throws DuplicateCounterException;
	
	public long incrementCounter(String counterName)
						throws NoSuchCounterException;
	
	public long getCount(String counterName)
						throws NoSuchCounterException;
	
	public Counter getCounter(String counterName) 
						throws NoSuchCounterException;
	
	public List<Counter> getCounters();
	
	public void updateCounter(Counter counter);
	
	public void removeCounter(String counterName);
	
	public class NoSuchCounterException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
	
	public class DuplicateCounterException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
}
