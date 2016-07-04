/**
 * 
 */
package za.co.solms.weather;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import za.co.solms.location.Location;

/**
 * @author fritz@solms.co.za
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="findAllWeatherReadings", 
		query="Select wr from WeatherReading wr"),
	@NamedQuery(name="findAllWeatherReadingsForLocation", 
		query="select wr from WeatherReading wr where wr.location = :location"),
	@NamedQuery(name="findAllWeatherReadingsForLocationAndPeriod", 
			query="select wr from WeatherReading wr where wr.location = :location" 
				+ " and wr.dateTime > :after and wr.dateTime <= :onOrBefore")
})

public class WeatherReading implements Serializable
{
	public WeatherReading() {}
	
	public WeatherReading(Location location, Date dateTime, 
			double temperature, double humidity, Ambiance ambiance)
	{
		this.location = location;
		this.dateTime = dateTime;
		this.temperature = temperature;
		this.humidity = humidity;
		this.ambiance = ambiance;
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
	
	public double getTemperature()
	{
		return temperature;
	}
	
	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}
	
	public double getHumidity()
	{
		return humidity;
	}
	
	public void setHumidity(double humidity)
	{
		this.humidity = humidity;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(Date dateTime)
	{
		this.dateTime = dateTime;
	}
	
	public Ambiance getAmbiance()
	{
		return ambiance;
	}
	
	public void setAmbiance(Ambiance ambiance)
	{
		this.ambiance = ambiance;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public boolean equals(Object o)
	{
		try
		{
			WeatherReading arg = (WeatherReading)o;
			return (this.id == arg.id) && (this.temperature == arg.temperature)
				&& (this.humidity == arg.humidity) && (this.ambiance == arg.ambiance);
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return id + dateTime.hashCode() + new Double(temperature).hashCode() 
				+ new Double(humidity).hashCode();
	}
	
	private int id;
	private double temperature, humidity;
	private Date dateTime;
	private Ambiance ambiance;
	private Location location;

	private static final long serialVersionUID = 1L;
}
