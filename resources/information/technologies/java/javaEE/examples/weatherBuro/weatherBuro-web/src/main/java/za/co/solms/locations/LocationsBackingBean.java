package za.co.solms.locations;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import za.co.solms.location.Location;
import za.co.solms.location.LocationServices;

/**
 * 
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class LocationsBackingBean implements Serializable
{

	public String removeLocation()
	{
		logger.info("Requested to remove " + selectedLocation);
		locationServices.removeLocation(selectedLocation);
		return "";
	}
	
	public List<Location> getAllLocations()
	{
		return locationServices.getAllLocations();
	}

	public Location getSelectedLocation()
	{
		return selectedLocation;
	}

	public void setSelectedLocation(Location selectedLocation)
	{
		this.selectedLocation = selectedLocation;
	}

	private Location selectedLocation;
	
	@EJB
	private LocationServices locationServices;
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(LocationsBackingBean.class.getName());
}
