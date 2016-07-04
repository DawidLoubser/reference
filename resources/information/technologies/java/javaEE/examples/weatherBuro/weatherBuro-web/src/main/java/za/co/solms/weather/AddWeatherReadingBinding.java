/**
 * 
 */
package za.co.solms.weather;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import za.co.solms.dateTime.DateTimeBackingBean;
import za.co.solms.locations.LocationSelectorBackingBean;

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
		
		WeatherReading weatherReading = weatherReadingBackingBean.getWeatherReading();
		
		logger.info("***** Extracted weather reading for " + weatherReading.getLocation() 
				+ " on " + weatherReading.getDateTime() + ", temp = " + weatherReading.getTemperature());
		
		weatherServices.persistWeatherReading(weatherReading);

		logger.info("Weather reading persisted.");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Weather reading stored.", 
				"The reading is saved into the database");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "";
	}
	
	public WeatherReadingBackingBean getWeatherReadingBackingBean()
	{
		return weatherReadingBackingBean;
	}

	public void setWeatherReadingBackingBean(
			WeatherReadingBackingBean weatherReadingBackingBean)
	{
		this.weatherReadingBackingBean = weatherReadingBackingBean;
	}

	@ManagedProperty(value="#{weatherReadingBackingBean}")	
	private WeatherReadingBackingBean weatherReadingBackingBean;
	
	@EJB
	private WeatherServices weatherServices;

	private static Logger logger = Logger.getLogger(AddWeatherReadingBinding.class.getName());
}
