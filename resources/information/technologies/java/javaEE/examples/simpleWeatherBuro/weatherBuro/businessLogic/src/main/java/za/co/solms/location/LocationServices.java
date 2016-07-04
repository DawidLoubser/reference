package za.co.solms.location;

import java.util.List;

/**
 * The contract for the location services to be provided.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface LocationServices
{
	/**
	 * Persists the provided location in the database.
	 * 
	 * @param location the location to be persisted
	 * @return the entity object for the persisted location with its generated id.
	 * @throws LocationNameInUseException if there is already a location in persistent
	 * 	storage which has the same name as the provided location.
	 */
	public Location persistLocation(Location location) throws LocationNameInUseException;

	/**
	 * REmoves the provided location from persistent storage.
	 * 
	 * @param location the location to be deleted from storage.
	 */
	public void removeLocation(Location location);
	
	/**
	 * Removes all locations from persistent storage.
	 */
	public void removeAllLocations();
	
	/**
	 * Provide a list of all locations stored in the database.
	 * 
	 * @return a list of all locations.
	 */
	public List<Location> getAllLocations();

	/**
	 * Returns the location with the specified name. Since names
	 * are enforced to be unique there will be at most one matching
	 * location.
	 * 
	 * @return the location with the specified name
	 * @throws NoSuchLocationException if there is no location with the specified name.
	 */
	public Location getLocationByName(String locationName) throws NoSuchLocationException;

	/**
	 * This exception is used to notify a requester of a service that the service is
	 * not going to be provided because a specified location did not exist.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public class NoSuchLocationException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}

	/**
	 * Thrown if a service cannot be provided due to a particular location
	 * existing already.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public class LocationExistsException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
	
	/**
	 * This exception is thrown if the request service cannot be provided because
	 * a certain location name is already in use, i.e. there exists already a location
	 * with a particular name.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public class LocationNameInUseException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
}
