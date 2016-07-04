package za.co.solms.location;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import za.co.solms.javaee.glassfish.container.embedded.EjbContainerUtility;
import za.co.solms.location.LocationServices.LocationNameInUseException;
import za.co.solms.location.LocationServices.NoSuchLocationException;

import static junit.framework.Assert.*;



/**
 * A unit test for the location services.
 *  
 * @author fritz@solms.co.za
 *
 */
public class LocationServicesTest
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
	    }
	    catch (NamingException e)
	    {
	   	 fail("Could not lookup session bean " + e.toString());
	    }
	 }

	
	@Before
	public void setupTestData()
	{
		location1 = new Location("Soccer City", "1a Pirates Ave\nSoweto",
				new GeographicCoordinates(29.8, 19.3));
		
		location2 = new Location("Gamadoelas CBD", "1 Krokodil Str\nGamadoelas",
				new GeographicCoordinates(26.1, 27.6));

		location3 = new Location("Soccer City", "15 Nyala Rd\nGamadoelas",
				new GeographicCoordinates(29.9, 19.5));
	}
	
	@After
	public void cleanupTestData()
	{
		locationServices.removeAllLocations();
	}
	
	@Test
	public void testPersistence()
	{
		int numLocations = locationServices.getAllLocations().size();
		
		try
		{
			locationServices.persistLocation(location1);
			locationServices.persistLocation(location2);
			assertEquals(numLocations+2, locationServices.getAllLocations().size());
	
			try
			{
				Location location = locationServices.getLocationByName("Soccer City");
				assertEquals(location1, location);
			}
			catch (NoSuchLocationException e)
			{
				fail("Could not retrieve persisted location");
			}
		}
		catch (LocationNameInUseException e)
		{
			fail("Location name already used????");
		}
	}
	
	@Test
	public void testNameUniqueness()
	{
		try
		{
			locationServices.persistLocation(location1);
		}
		catch (LocationNameInUseException e)
		{
			fail("Location name already used????");
		}

		try
		{
			locationServices.persistLocation(location3);
			fail("Location name uniqueness not enforced.");
		}
		catch (LocationNameInUseException e)
		{
		}
	}
	
	@Test
	public void testGetLocationByName()
	{
		try
		{
			locationServices.persistLocation(location1);
		}
		catch (LocationNameInUseException e)
		{
			fail("Location name already used????");
		}
		
		try
		{
			assertEquals(location1, locationServices.getLocationByName("Soccer City"));
		}
		catch (NoSuchLocationException e)
		{
			fail("Could not retrieve location by name.");
		}
	}

	@EJB
	private static LocationServices locationServices;
	
	private Location location1, location2, location3;

	private static Context context;
}
