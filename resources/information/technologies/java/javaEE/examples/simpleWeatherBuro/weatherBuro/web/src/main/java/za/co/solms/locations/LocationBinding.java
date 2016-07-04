/**
 * 
 */
package za.co.solms.locations;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import za.co.solms.location.Location;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class LocationBinding implements Serializable
{
	public LocationBinding() {}

	public Location getLocation()
	{
		location.setGeographicLocation(geographicCoordinatesBinding.getGeographicCoordinates());
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public GeographicCoordinatesBinding getGeographicCoordinatesBinding()
	{
		return geographicCoordinatesBinding;
	}

	public void setGeographicCoordinatesBinding(
			GeographicCoordinatesBinding geographicCoordinatesBinding)
	{
		this.geographicCoordinatesBinding = geographicCoordinatesBinding;
	}


	private Location location = new Location();
	
	@ManagedProperty(value="#{geographicCoordinatesBinding}")
	private GeographicCoordinatesBinding geographicCoordinatesBinding;

	private static final long serialVersionUID = 1L;
}
