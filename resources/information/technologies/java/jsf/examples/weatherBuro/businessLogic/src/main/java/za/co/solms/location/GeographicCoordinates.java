/**
 * 
 */
package za.co.solms.location;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents a position on earth in the form of degrees longitude and latitude.
 * 
 * @author fritz@solms.co.za
 *
 */
@Entity
public class GeographicCoordinates implements Serializable
{
	public GeographicCoordinates() {}
	
	public GeographicCoordinates(double longitude, double latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	
	public boolean equals(Object o)
	{
		try
		{
			GeographicCoordinates arg = (GeographicCoordinates)o;
			return (this.latitude == arg.latitude) 
				&& (this.longitude == arg.longitude);
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return new Double(longitude).hashCode() + new Double(latitude).hashCode();
	}
	
	private int id;
	private double longitude, latitude;

	private static final long serialVersionUID = 1L;
}
