package za.co.solms.weather;

import java.util.Date;
import java.util.List;

import za.co.solms.location.Location;

/**
 * 
 * @author fritz@solms.co.za
 *
 */
public interface WeatherServices
{
	/**
	 * Returns the weather reading which has the specified id.
	 * @param id the id of the required weather reading
	 * @return the weather reading with the specified id.
	 * @throws NoWeatherReadingsException if there is no weather reading with the
	 * 		specified id.
	 */
	public WeatherReading getWeatherReading(int id) throws NoWeatherReadingsException;
	
	/**
	 * This service stores the provided weather reading in persistent storage (a database).
	 * The primary key will have been set for the returned entity.
	 * 
	 * @param reading the value object which should be persisted.
	 * @return the entity which has been persisted (with its primary key)
	 */
	public WeatherReading persistWeatherReading(WeatherReading reading);
	
	/**
	 * Returns a list of all weather readings for a specified location
	 * which fall in a particular period.
	 * @param location the location for which weather readings are requested
	 * @param after the date-time after which readings are required
	 * @param OnOrBefore the date-time up to which readings are required.
	 * @return the list of weather readings within the requested period 
	 * 	for the specified location
	 * 
	 */
	public List<WeatherReading> getWeatherReadingsForLocation(Location location,
					Date after, Date onOrBefore);
	
	/**
	 * Returns the average temperature for the specified location and specified 
	 * period. This is a simplistic average looking simply at the average of all
	 * readings within the period.
	 *  
	 * @param location the location for which the average temperature is required
	 * @param after the start of the period for which the average temperature is required
	 * @param onOrBefore the end of the period for which the average temperature is required
	 * @return the average temperature for the specified location and period
	 * @throws NoWeatherReadingsException if there are no weather readings for
	 * the specified location over the specified period
	 */
	public double getAverageTemperature(Location location, Date after, Date onOrBefore)
		throws NoWeatherReadingsException;
	
	/**
	 * Returns a list of all weather stored weather readings.
	 * @return a list of all weather readings contained in persistent storage.
	 */
	public List<WeatherReading> getAllWeatherReadings();
	
	/**
	 * Removes the provided weather reading from persistent storage.
	 * 
	 * @param weatherReading
	 */
	public void removeWeatherReading(WeatherReading weatherReading);
	
	/**
	 * Removes all weather readings from persistent storage.
	 */
	public void removeAllWeatherReadings();
	
	/**
	 * An exception used to notify a client that the request service could not
	 * be provided because there were no applicable weather readings.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public class NoWeatherReadingsException extends Exception
	{
		private static final long serialVersionUID = 1L;
	}
	
	/**
	 * Updates the persisted weather reading to the state of the provided value object.
	 * @param weatherReading the weather reading to be updated in persistent storage
	 * @return the updated weather reading entity.
	 */
	public WeatherReading updateWeatherReading(WeatherReading weatherReading);
}
