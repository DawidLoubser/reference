/**
 * 
 */
package za.co.solms.weather;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import za.co.solms.javaee.glassfish.container.embedded.EjbContainerUtility;
import za.co.solms.location.GeographicCoordinates;
import za.co.solms.location.Location;
import za.co.solms.location.LocationServices;
import za.co.solms.location.LocationServices.LocationNameInUseException;
import za.co.solms.weather.WeatherServices.NoWeatherReadingsException;

import static junit.framework.Assert.*;

/**
 * @author fritz@solms.co.za
 *
 */
public class WeatherServicesTest
{
	@BeforeClass
	public static void initialize()
	{
		context = EjbContainerUtility.getContainer().getContext();		
		lookupServiceBeans();
	}
	
	private static void lookupServiceBeans() 
	{  
	    try
	    {
	   	 locationServices = (LocationServices)context.lookup
				("java:global/classes/LocationServicesBean");
	   	 assertNotNull(locationServices);
	   	 
	   	 weatherServices = (WeatherServices)context.lookup
	   	 							("java:global/classes/WeatherServicesBean");
		 assertNotNull(weatherServices);		 
	    }
	    catch (NamingException e)
	    {
	   	 fail("Could not lookup session bean " + e.toString());
	    }
	 }

	
	@Before
	public void setupTestData()
	{
		d1 = new GregorianCalendar(2010,0,20,6,0,0).getTime();
		d2 = new GregorianCalendar(2010,2,20,17,30,0).getTime();
		d3 = new GregorianCalendar(2010,3,20,12,0,0).getTime();
		d4 = new GregorianCalendar(2010,5,5,6,0,0).getTime();
		
		l1 = new Location("l1","addr1",new GeographicCoordinates(1,2));
		l2 = new Location("l2","addr2",new GeographicCoordinates(3,4));
		
		try
		{
			locationServices.persistLocation(l1);
			locationServices.persistLocation(l2);
			
			r1 = new WeatherReading(l1, d1, 22, 85, Ambiance.overcast);
			r2 = new WeatherReading(l2, d1, 27, 95, Ambiance.sunny);
			r3 = new WeatherReading(l1, d2, -12, 70, Ambiance.snowing);
			r4 = new WeatherReading(l1, d3, 15, 100, Ambiance.raining);
			r5 = new WeatherReading(l2, d3, 20, 85, Ambiance.overcast);
			r6 = new WeatherReading(l1, d4, 24, 65, Ambiance.sunny);
		}
		catch (LocationNameInUseException e)
		{
			fail("Claims location name already in use.");
		}
		weatherServices.persistWeatherReading(r1);
		weatherServices.persistWeatherReading(r2);
		weatherServices.persistWeatherReading(r3);
		weatherServices.persistWeatherReading(r4);
		weatherServices.persistWeatherReading(r5);
		weatherServices.persistWeatherReading(r6);
	}
	
	@After
	public void cleanupTestData()
	{
		weatherServices.removeAllWeatherReadings();
		locationServices.removeAllLocations();
	}
	
	@Test
	public void testPersist()
	{
		try
		{
			WeatherReading r = weatherServices.getWeatherReading(r1.getId());
			
			assertEquals(r1, r);
		}
		catch (NoWeatherReadingsException e)
		{
			fail("Could not retrieve persisted weather reading.");
		}
	}
	
	@Test
	public void testGetAverageTemperature()
	{
		Date after = d1;
		Date onOrBefore = d4;
		try
		{
			double avgTemp = weatherServices.getAverageTemperature(l1, after, onOrBefore);
			
			logger.info("***** avg temp = " + avgTemp);
			assertEquals(9, avgTemp, doubleEps);
		}
		catch (NoWeatherReadingsException e)
		{
			fail("Supposedly no weather readings within period");
		}
	}
	
	@Test
	public void testGetWeatherReadingsForLocation()
	{
		List<WeatherReading> weatherReadings = weatherServices.getWeatherReadingsForLocation(l1);
		assertEquals(4, weatherReadings.size());
	}
	
	@EJB
	private static WeatherServices weatherServices;

	@EJB
	private static LocationServices locationServices;
	
	private Date d1, d2, d3, d4;
	private Location l1, l2;
	private WeatherReading r1, r2, r3, r4, r5, r6;
	
	private static Context context;
	
	private final static double doubleEps = 1e-9;
	
	Logger logger = Logger.getLogger(WeatherServicesTest.class.getName());
}
