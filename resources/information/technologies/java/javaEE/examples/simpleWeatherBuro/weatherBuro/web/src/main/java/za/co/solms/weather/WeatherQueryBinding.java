/**
 * 
 */
package za.co.solms.weather;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import za.co.solms.dateTime.DatePeriodBinding;
import za.co.solms.locations.LocationSelectorBinding;
import za.co.solms.weather.WeatherServices.NoWeatherReadingsException;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class WeatherQueryBinding implements Serializable
{
	public String generateReport()
	{
		logger.info("***** Requested report for " + locationSelectorBinding.getLocationName()
				+ "( " + locationSelectorBinding.getLocation() + ")");
		
		weatherReadings = weatherServices.getWeatherReadingsForLocation
				(locationSelectorBinding.getLocation(), 
				 datePeriodBinding.getStartDate(), 
				 datePeriodBinding.getEndDate());
		logger.info("Num readings = " + weatherReadings.size());
		
		try
		{
			averageTemperature = weatherServices.getAverageTemperature
					(locationSelectorBinding.getLocation(), 
							 datePeriodBinding.getStartDate(), 
							 datePeriodBinding.getEndDate());
			
			return "";
		}
		catch (NoWeatherReadingsException e)
		{
			logger.info("*** No weather readings in requested period");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No weather readings in specified period for specified location.", 
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage("", msg);
			return "";
		}
	}
	
	public double getAverageTemperature()
	{
		return averageTemperature;
	}

	public void setAverageTemperature(double averageTemperature)
	{
		this.averageTemperature = averageTemperature;
	}

	public List<WeatherReading> getWeatherReadings()
	{
		return weatherReadings;
	}

	public void setWeatherReadings(List<WeatherReading> weatherReadings)
	{
		this.weatherReadings = weatherReadings;
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

	public DatePeriodBinding getDatePeriodBinding()
	{
		return datePeriodBinding;
	}

	public void setDatePeriodBinding(DatePeriodBinding datePeriodBinding)
	{
		this.datePeriodBinding = datePeriodBinding;
	}

	public WeatherServices getWeatherServices()
	{
		return weatherServices;
	}

	public void setWeatherServices(WeatherServices weatherServices)
	{
		this.weatherServices = weatherServices;
	}

	public Logger getLogger()
	{
		return logger;
	}

	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}



	private double averageTemperature;
	
	private List<WeatherReading> weatherReadings;
	
	@ManagedProperty(value="#{locationSelectorBinding}")
	private LocationSelectorBinding locationSelectorBinding;
	
	@ManagedProperty(value="#{datePeriodBinding}")
	private DatePeriodBinding datePeriodBinding;
	
	@EJB
	private WeatherServices weatherServices;
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(WeatherQueryBinding.class.getName());
}
