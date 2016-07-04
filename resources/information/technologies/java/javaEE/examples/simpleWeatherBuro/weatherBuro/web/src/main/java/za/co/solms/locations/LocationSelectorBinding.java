/**
 * 
 */
package za.co.solms.locations;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import za.co.solms.location.Location;
import za.co.solms.location.LocationServices;
import za.co.solms.location.LocationServices.NoSuchLocationException;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean(eager=true)
@RequestScoped
public class LocationSelectorBinding implements Serializable
{
	public List<Location> getLocations()
	{
		return locationServices.getAllLocations();
	}
	
	public Location getLocation()
	{
		try
		{
			return locationServices.getLocationByName(locationName);
		}
		catch (NoSuchLocationException e)
		{
			assert false: "no selected location";
			logger.severe("Drop down slist selection for location null");
			return null;
		}
	}

	public String getLocationName()
	{
		return locationName;
	}

	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	private String locationName;

	private static final long serialVersionUID = 1L;

	@EJB
	private LocationServices locationServices;
	
	private static Logger logger = Logger.getLogger(LocationSelectorBinding.class.getName());
}
