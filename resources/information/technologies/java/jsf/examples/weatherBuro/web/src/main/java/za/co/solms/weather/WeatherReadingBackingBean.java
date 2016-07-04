package za.co.solms.weather;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import za.co.solms.dateTime.DateTimeBackingBean;
import za.co.solms.locations.LocationSelectorBackingBean;

@ManagedBean
@RequestScoped
public class WeatherReadingBackingBean implements Serializable
{
	public WeatherReadingBackingBean() {}
	
	public WeatherReading getWeatherReading()
	{
		WeatherReading weatherReading
			= new WeatherReading(locationSelectorBackingBean.getLocation(), 
					dateTimeBackingBean.getDateTime(), 
					weatherDetailsBackingBean.getTemperature(),
					weatherDetailsBackingBean.getHumidity(),
					weatherDetailsBackingBean.getAmbiance());
		return weatherReading;
	}

	public void setWeatherReading(WeatherReading weatherReading)
	{
		if (weatherReading.getLocation() != null)
			locationSelectorBackingBean.setLocationName(weatherReading.getLocation().getName());
		dateTimeBackingBean.setDateTime(weatherReading.getDateTime());
		weatherDetailsBackingBean.setTemperature(weatherReading.getTemperature());
		weatherDetailsBackingBean.setHumidity(weatherReading.getHumidity());
		weatherDetailsBackingBean.setAmbiance(weatherReading.getAmbiance());
	}
	
	public LocationSelectorBackingBean getLocationSelectorBackingBean()
	{
		return locationSelectorBackingBean;
	}

	public void setLocationSelectorBackingBean(
			LocationSelectorBackingBean locationSelectorBackingBean)
	{
		this.locationSelectorBackingBean = locationSelectorBackingBean;
	}

	public DateTimeBackingBean getDateTimeBackingBean()
	{
		return dateTimeBackingBean;
	}

	public void setDateTimeBackingBean(DateTimeBackingBean dateTimeBackingBean)
	{
		this.dateTimeBackingBean = dateTimeBackingBean;
	}

	public WeatherDetailsBackingBean getWeatherDetailsBackingBean()
	{
		return weatherDetailsBackingBean;
	}

	public void setWeatherDetailsBackingBean(WeatherDetailsBackingBean weatherDetailsBackingBean)
	{
		this.weatherDetailsBackingBean = weatherDetailsBackingBean;
	}

	public boolean isDummy() {return dummy;}

	public void setDummy(boolean dummy)
	{
		this.dummy = dummy;
	}

	private boolean dummy;

	@ManagedProperty(value="#{locationSelectorBackingBean}")
	private LocationSelectorBackingBean locationSelectorBackingBean;
	
	@ManagedProperty(value="#{dateTimeBackingBean}")
	private DateTimeBackingBean dateTimeBackingBean;
	
	@ManagedProperty(value="#{weatherDetailsBackingBean}")
	private WeatherDetailsBackingBean weatherDetailsBackingBean;
}
