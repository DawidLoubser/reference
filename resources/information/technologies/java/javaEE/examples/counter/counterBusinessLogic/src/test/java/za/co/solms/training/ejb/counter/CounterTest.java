/**
 * 
 */
package za.co.solms.training.ejb.counter;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import za.co.solms.training.ejb.counter.CounterServices.DuplicateCounterException;
import za.co.solms.training.ejb.counter.CounterServices.NoSuchCounterException;

import container.embedded.glassfish.EjbContainerUtility;

/**
 * @author fritz@solms.co.za
 *
 */
public class CounterTest
{
	@BeforeClass // Executed before each test - can have multiple befores
   public static void lookupServiceBeans() 
	{  
	    try
	    {
	   	 counterServices = (CounterServices)context.lookup("java:global/classes/CounterServicesBean");
	    }
	    catch (NamingException e)
	    {
	   	 fail("Could not lookup session bean");
	    }

	    assertNotNull(counterServices);		 
	}
	
	@After
	public void cleanup()
	{
		List<Counter> counters = counterServices.getCounters();
		for (Counter counter:counters)
			counterServices.removeCounter(counter.getName());
	}
	
	@Test
	public void testCounterService()
	{
		String counterName = "my first counter";
		try
		{
			counterServices.createCounter(counterName);
			counterServices.incrementCounter(counterName);
			counterServices.incrementCounter(counterName);
			assertEquals(2, counterServices.getCount(counterName));
		}
		catch (Exception e)
		{
			fail(e.getStackTrace().toString());
		}
	}
	
	@Test
	public void testUniqueCounterName()
	{
		String counterName = "my first counter";
		
		try
		{
			counterServices.createCounter(counterName);
		}
		catch (DuplicateCounterException e)
		{
			fail("Claims to already have counter with that name");
		}

		try
		{
			counterServices.createCounter(counterName);
			fail("Should have thrown DuplicateCounterException");
		}
		catch (DuplicateCounterException e)
		{
		}
	}
	
	
	@Test
	public void testCounterValueObject()
	{
		String counterName = "my first counter";
		try
		{
			Counter counter = counterServices.createCounter(counterName);
			
			counter.increment();
			counter.increment();
			counter.increment();

			counterServices.updateCounter(counter);
			
			counter = counterServices.getCounter(counterName);
			assertEquals(counterName, counter.getName());
			assertEquals(3, counter.getCount());
			
			counter.increment();
			counterServices.updateCounter(counter);

			counter = counterServices.getCounter(counterName);
			assertEquals(4, counter.getCount());
		}	
		catch (DuplicateCounterException e)
		{
			fail();
		}
		catch (NoSuchCounterException e)
		{
			fail();
		}
	}

   private static CounterServices counterServices;

	@BeforeClass // Executed once before any test is run, but not for each test
   public static void setupEnvironment() 
	{  
	    context = EjbContainerUtility.getContainer().getContext();		
	}

	private static Context context;
  
   private static Logger logger = Logger.getLogger(CounterTest.class.getName());

}
