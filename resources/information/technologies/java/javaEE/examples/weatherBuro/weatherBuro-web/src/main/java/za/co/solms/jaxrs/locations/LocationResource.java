package za.co.solms.jaxrs.locations;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import za.co.solms.location.Location;
import za.co.solms.location.LocationServices;
import za.co.solms.location.LocationServices.LocationNameInUseException;
import za.co.solms.location.LocationServices.NoSuchLocationException;

@Path("/locations")
public class LocationResource
{
	public LocationResource() {}
	
	@Path("/{locationName}")
	@Produces("application/xml")
	@GET
	public Object getLocation(@PathParam("locationName") String locationName)
	{
		logger.info("*** Requesting location " + locationName);
		try
		{
			return locationServices.getLocationByName(locationName);
		}
		catch (NoSuchLocationException e)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@Path("/add")
	@Consumes("application/xml")
	@Produces("application/xml")
	@PUT
	public Response createLocation(Location location) throws IOException
	{
		logger.info("*** Adding location " + location);
		try
		{
			location = locationServices.persistLocation(location);
			
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			URI locationUri = uriBuilder.path(location.getName()).build();

			return Response.created(locationUri).entity(location).build();
		}
		catch (LocationNameInUseException e)
		{
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	@Path("/{locationName}")
	@DELETE
	public Response removeLocation(@PathParam("locationName") String locationName)
	{
		logger.info("*** Removing location " + locationName);
		
		try
		{
			Location location = locationServices.getLocationByName(locationName);
			locationServices.removeLocation(location);
		}
		catch (NoSuchLocationException e) {}
		
		return Response.ok().build();
	}

	@EJB
	private LocationServices locationServices;
	
	@Context
	UriInfo uriInfo;
	
	private static final Logger logger = Logger.getLogger(LocationResource.class.getName());
}
