package za.co.solms.location;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Locations
{
	public Locations() {}
	
	public Locations(List<Location> locations)
	{
		setLocations(locations);
	}
	
	public List<Location> getLocations()
	{
		return locations;
	}

	public void setLocations(List<Location> locations)
	{
		this.locations = locations;
	}

	private List<Location> locations = new LinkedList<Location>();
}
