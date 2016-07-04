/**
 * 
 */
package za.co.solms.locations;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import za.co.solms.location.Location;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class LocationBackingBean implements Serializable
{
	public LocationBackingBean() {}

	public Location getLocation()
	{
		location.setGeographicLocation(geographicCoordinatesBackingBean.getGeographicCoordinates());
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public GeographicCoordinatesBackingBean getGeographicCoordinatesBackingBean()
	{
		return geographicCoordinatesBackingBean;
	}

	public void setGeographicCoordinatesBackingBean(
			GeographicCoordinatesBackingBean geographicCoordinatesBackingBean)
	{
		this.geographicCoordinatesBackingBean = geographicCoordinatesBackingBean;
	}


	private Location location = new Location();
	
	@ManagedProperty(value="#{geographicCoordinatesBackingBean}")
	private GeographicCoordinatesBackingBean geographicCoordinatesBackingBean;

	private static final long serialVersionUID = 1L;
}
