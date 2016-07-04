/**
 * 
 */
package za.co.solms.location;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A services implementation which will be manifested as a stateless session
 * bean when deployed in an EJB application server. 
 * 
 * @author fritz@solms.co.za
 *
 */
@Local(LocationServices.class)
@Remote(LocationServices.class)
@Stateless
public class LocationServicesBean implements LocationServices
{

	/* (non-Javadoc)
	 * @see solms.co.za.location.LocationServices#getAllLocations()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAllLocations()
	{
		return entityManager.createNamedQuery("findAllLocations").getResultList();
	}

	/* (non-Javadoc)
	 * @see solms.co.za.location.LocationServices#getLocationByName()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Location getLocationByName(String locationName) throws NoSuchLocationException
	{
		Query query = entityManager.createNamedQuery("findLocationByName");
		query.setParameter("locationName", locationName);
		List<Location> matchingLocations = query.getResultList();
		assert matchingLocations.size()<2 
			: "uniqueness constraint on location name seems not enforced";
		if (matchingLocations.size() == 0)
			throw new NoSuchLocationException();
		else
			return matchingLocations.get(0);
	}

	/* (non-Javadoc)
	 * @see solms.co.za.location.LocationServices#persistLocation(solms.co.za.location.Location)
	 */
	@Override
	public Location persistLocation(Location location) throws LocationNameInUseException
	{
		try
		{
			getLocationByName(location.getName());
			// if previous request does not throw an exception, the location name is used already.
			throw new LocationNameInUseException();
		}
		catch (NoSuchLocationException e)
		{
			entityManager.persist(location);
			return location;
		}
	}

	@Override
	public void removeLocation(Location location)
	{
		logger.info("***** Removing location " + location);
		entityManager.remove(entityManager.merge(location));
		logger.info("***** done removing location.");
	}

	@Override
	public void removeAllLocations()
	{
		for (Location location: this.getAllLocations())
			this.removeLocation(location);
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(LocationServicesBean.class.getName());
}
