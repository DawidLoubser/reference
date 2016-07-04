package za.co.solms.weather.webServices;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import za.co.solms.location.GeographicCoordinates;
import za.co.solms.location.Location;
import za.co.solms.location.LocationNameInUseException_Exception;
import za.co.solms.location.LocationService;
import za.co.solms.location.LocationServices;
import za.co.solms.weather.WeatherReading;

public class WebServicesTest
{
	@Before
	public void initializeTestData()
	{
		connectToLocationService();
		
		logger.info("Initial num locations: " + locationServices.getAllLocations().size());
		
		logger.info("Creating and persisting some locations");
		try
		{
			locationServices.persistLocation(createLocation("Gamadoelas", 
					"23 Gamas Ave, Thornbush 3, Namaqualand, South Africa",
					createGeographicCoordinates(-27, 23)));
		}
		catch (LocationNameInUseException_Exception e)
		{
			fail();
		}

		try
		{
			locationServices.persistLocation(createLocation("Lekki Peninsula", 
					"Ahmadu Bello Ave, Victoria Island, Lagos, Nigeria",
					createGeographicCoordinates(6.4, 3.4)));
		}
		catch (LocationNameInUseException_Exception e)
		{
			fail();
		}
		
		try
		{
			locationServices.persistLocation(createLocation("University of Amsterdam", 
					"Binnengasthuisstraat 9, Amsterdam, Netherlands",
					createGeographicCoordinates(52.3, 4.9)));
		}
		catch (LocationNameInUseException_Exception e)
		{
			fail();
		}
	}
	
	@After
	public void cleanup()
	{
		locationServices.removeAllLocations();
	}
	
	public void connectToLocationService()
	{
		logger.info("connecting to location service");
		locationServices = new LocationService().getLocationServicesBeanPort();
		logger.info("Location service: " + locationServices);
	}
	
	@Test
	public void generalTest()
	{
		logger.info("Num locations: " + locationServices.getAllLocations().size());
		
		for (String locationName:locationServices.getAllLocationNames())
		{
			logger.info("Location name: " + locationName);
		}
	}

	private static GeographicCoordinates createGeographicCoordinates
			(double degreesLongitude, double degreesLatitude)
	{
		GeographicCoordinates geographicCoordinates = new GeographicCoordinates();
		geographicCoordinates.setLongitude(degreesLongitude);
		geographicCoordinates.setLatitude(degreesLatitude);
		return geographicCoordinates;
	}
	
	private static Location createLocation(String locationName,
														String locationAddress,
														GeographicCoordinates geographicCoordinates)
	{
		Location location = new Location();
		location.setName(locationName);
		location.setAddress(locationAddress);
		location.setGeographicLocation(geographicCoordinates);
		return location;
	}
	
	private LocationServices locationServices;
	
	private List<Location> locations = new LinkedList<Location>();
	private List<WeatherReading> weatherReadings = new LinkedList<WeatherReading>();
	
	private static Logger logger = Logger.getLogger(WebServicesTest.class.getName());
}