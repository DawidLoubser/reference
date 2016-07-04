package za.co.solms.locations;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import za.co.solms.location.Location;
import za.co.solms.location.LocationServices;
import za.co.solms.location.LocationServices.NoSuchLocationException;

@ManagedBean
@ViewScoped
public class AjaxLocationsBinding implements Serializable
{         
	public AjaxLocationsBinding() {}

	public List<String> getLocationNames()
	{
		logger.info("Getting location names");
		return locationServices.getAllLocationNames();
	}
	             
	public void changeLocationName()
	{
		logger.info("changing location name to " + locationName);
	}
	
	public String refreshPage()
	{
		return "";
	}
	
	public String getLocationName()
	{
		return locationName;
	}

	public void setLocationName(String locationName)
	{
		logger.info("Setting location name to " + locationName);
		this.locationName = locationName;
	}

	public Location getLocation()
	{
		try
		{
			return locationServices.getLocationByName(locationName);
		}
		catch (NoSuchLocationException e)
		{
			FacesMessage msg = new FacesMessage("No such location - should not have happened");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}

	private List<String> locationNames;

	private String locationName;
	
	@EJB
	private LocationServices locationServices;
	
	private Logger logger = Logger.getLogger(AjaxLocationsBinding.class.getName());
}
