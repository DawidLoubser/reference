/**
 * 
 */
package za.co.solms.weather;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import za.co.solms.dateTime.DateTimeBinding;
import za.co.solms.locations.LocationSelectorBinding;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class AddWeatherReadingBinding implements Serializable
{
	public AddWeatherReadingBinding() {}

	public String addWeatherReading()
	{
		logger.severe("***** Requested to add weather reading");
		WeatherReading weatherReading 
			= new WeatherReading(locationSelectorBinding.getLocation(), 
					dateTimeBinding.getDateTime(), 
					weatherDetailsBinding.getTemperature(),
					weatherDetailsBinding.getHumidity(),
					weatherDetailsBinding.getAmbiance());
		logger.info("***** Extracted weather reading for " + weatherReading.getLocation() 
				+ " on " + weatherReading.getDateTime() + ", temp = " + weatherReading.getTemperature());
		weatherServices.persistWeatherReading(weatherReading);
		logger.info("Weather reading persisted.");
		return "";
	}
	
	public LocationSelectorBinding getLocationSelectorBinding()
	{
		return locationSelectorBinding;
	}

	public void setLocationSelectorBinding(
			LocationSelectorBinding locationSelectorBinding)
	{
		this.locationSelectorBinding = locationSelectorBinding;
	}

	public DateTimeBinding getDateTimeBinding()
	{
		return dateTimeBinding;
	}

	public void setDateTimeBinding(DateTimeBinding dateTimeBinding)
	{
		this.dateTimeBinding = dateTimeBinding;
	}

	public WeatherDetailsBinding getWeatherDetailsBinding()
	{
		return weatherDetailsBinding;
	}

	public void setWeatherDetailsBinding(WeatherDetailsBinding weatherDetailsBinding)
	{
		this.weatherDetailsBinding = weatherDetailsBinding;
	}

	@ManagedProperty(value="#{locationSelectorBinding}")
	private LocationSelectorBinding locationSelectorBinding;
	
	@ManagedProperty(value="#{dateTimeBinding}")
	private DateTimeBinding dateTimeBinding;
	
	@ManagedProperty(value="#{weatherDetailsBinding}")
	private WeatherDetailsBinding weatherDetailsBinding;
	
	@EJB
	private WeatherServices weatherServices;
	
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AddWeatherReadingBinding.class.getName());
}
