/**
 * 
 */
package za.co.solms.locations;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import za.co.solms.location.GeographicCoordinates;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean(eager=true) 
@RequestScoped
public class GeographicCoordinatesBinding implements Serializable
{
	public GeographicCoordinatesBinding() {}
	
	public GeographicCoordinates getGeographicCoordinates()
	{
		return geographicCoordinates;
	}

	public void setGeographicLocation(GeographicCoordinates geographicCoordinates)
	{
		this.geographicCoordinates = geographicCoordinates;
	}

	private GeographicCoordinates geographicCoordinates = new GeographicCoordinates(); 

	private static final long serialVersionUID = 1L;
}
