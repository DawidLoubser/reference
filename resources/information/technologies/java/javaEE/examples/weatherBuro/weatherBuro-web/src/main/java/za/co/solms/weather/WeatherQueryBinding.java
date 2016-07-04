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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import za.co.solms.dateTime.DatePeriodBackingBean;
import za.co.solms.locations.LocationSelectorBackingBean;
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
		logger.info("***** Requested report for " + locationSelectorBackingBean.getLocationName()
				+ "( " + locationSelectorBackingBean.getLocation() + ")");
		
		weatherReadings = weatherServices.getWeatherReadingsForLocation
				(locationSelectorBackingBean.getLocation(), 
				 datePeriodBackingBean.getStartDate(), 
				 datePeriodBackingBean.getEndDate());
		logger.info("Num readings = " + weatherReadings.size());
		
		try
		{
			averageTemperature = weatherServices.getAverageTemperature
					(locationSelectorBackingBean.getLocation(), 
							 datePeriodBackingBean.getStartDate(), 
							 datePeriodBackingBean.getEndDate());
			
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

	public LocationSelectorBackingBean getLocationSelectorBackingBean()
	{
		return locationSelectorBackingBean;
	}

	public void setLocationSelectorBackingBean(
			LocationSelectorBackingBean locationSelectorBackingBean)
	{
		this.locationSelectorBackingBean = locationSelectorBackingBean;
	}

	public DatePeriodBackingBean getDatePeriodBackingBean()
	{
		return datePeriodBackingBean;
	}

	public void setDatePeriodBackingBean(DatePeriodBackingBean datePeriodBackingBean)
	{
		this.datePeriodBackingBean = datePeriodBackingBean;
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
	
	@ManagedProperty(value="#{locationSelectorBackingBean}")
	private LocationSelectorBackingBean locationSelectorBackingBean;
	
	@ManagedProperty(value="#{datePeriodBackingBean}")
	private DatePeriodBackingBean datePeriodBackingBean;
	
	@EJB
	private WeatherServices weatherServices;
	
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(WeatherQueryBinding.class.getName());
}
