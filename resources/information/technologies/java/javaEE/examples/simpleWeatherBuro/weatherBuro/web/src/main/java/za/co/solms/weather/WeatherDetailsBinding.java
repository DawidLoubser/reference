/**
 * 
 */
package za.co.solms.weather;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class WeatherDetailsBinding implements Serializable
{
	public Ambiance[] getAmbianceValues()
	{
		return Ambiance.values();
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

	public Ambiance getAmbiance()
	{
		return ambiance;
	}

	public void setAmbiance(Ambiance ambiance)
	{
		this.ambiance = ambiance;
	}

	private double temperature, humidity;
	private Ambiance ambiance;
	
	private static final long serialVersionUID = 1L;
}
