package za.co.solms.locations;

import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import za.co.solms.location.GeographicCoordinates;
import za.co.solms.location.Location;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.impl.provider.entity.XMLRootElementProvider;

public class LocationsTest
{
	@BeforeClass
	public static void initialize()
	{
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getProperties().put(
	          ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		client = Client.create(clientConfig); 
	}
	  
	@Test
	public void test()
	{        
		String locationName = "home";
		String locationUri = baseUri + "locations/" + locationName;
		
		client.resource(locationUri).delete(ClientResponse.class);
		
		Location location = new Location(locationName,"Summerstrand, PE", 
				new GeographicCoordinates(-23, 24));

		WebResource resource = client.resource(baseUri + "locations/add");

		ClientResponse response = resource.type(MediaType.APPLICATION_XML)
      	.put(ClientResponse.class, location);
		
		logger.info("Response: " +response);
		
		assertEquals(201, response.getStatus());
	    
		Location retrievedLocation 
			= client.resource(locationUri).accept
				(MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_JSON_TYPE).get(Location.class);
		
		logger.info("Retrieved location: " + retrievedLocation);
		
		assertEquals(location, retrievedLocation);
		
		response = client.resource(locationUri).delete(ClientResponse.class);
		
		
	}
	
	private static Client client;
	
	private static String baseUri = "http://localhost:8080/weatherBuro/jaxrs/";
	
	private static Logger logger = Logger.getLogger(LocationsTest.class.getName());
}
