package za.co.solms.location;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a location with a name, address and geographic location.
 * 
 * @author fritz@solms.co.za
 *
 */
@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="findAllLocationNames",
			query="select l.name from Location l"),
	@NamedQuery(name="findAllLocations", 
		query="Select l from Location l"),
	@NamedQuery(name="findLocationByName", 
		query="select l from Location l where l.name = :locationName")
})
public class Location implements Serializable
{
	public Location() {}
	
	public Location(String name, String address, GeographicCoordinates geographicCoordinates)
	{
		this.name = name;
		this.address = address;
		this.geographicCoordinates = geographicCoordinates;
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

	@Column(unique=true, nullable=false)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public GeographicCoordinates getGeographicLocation()
	{
		return geographicCoordinates;
	}

	public void setGeographicLocation(GeographicCoordinates geographicCoordinates)
	{
		this.geographicCoordinates = geographicCoordinates;
	}
	
	public boolean equals(Object o)
	{
		try
		{
			Location arg = (Location)o;
			return this.getName().equals(arg.getName())
					&& this.getAddress().equals(arg.getAddress())
					&& this.getGeographicLocation().equals(arg.getGeographicLocation());
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return name.hashCode() + address.hashCode() + geographicCoordinates.hashCode();
	}
	
	public String toString() {return name;}

	private int id;
	private String name, address;
	private GeographicCoordinates geographicCoordinates;

	private static final long serialVersionUID = 1L;
}
