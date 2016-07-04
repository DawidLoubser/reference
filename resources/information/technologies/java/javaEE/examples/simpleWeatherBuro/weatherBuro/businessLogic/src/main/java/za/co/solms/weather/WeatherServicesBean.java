/**
 * 
 */
package za.co.solms.weather;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import za.co.solms.location.Location;

/**
 * @author fritz@solms.co.za
 *
 */
@Local(WeatherServices.class)
@Stateless
public class WeatherServicesBean implements WeatherServices
{

	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#getAverageTemperature(za.co.solms.location.Location, java.util.Date, java.util.Date)
	 */
	@Override
	public double getAverageTemperature(Location location, Date onOrBefore, Date after)
			throws NoWeatherReadingsException
	{
		Collection<WeatherReading> readings 
			= this.getWeatherReadingsForLocation(location, onOrBefore, after);
		
		if (readings.isEmpty())
			throw new NoWeatherReadingsException();
		
		double sum = 0;
		for (WeatherReading weatherReading : readings)
			sum += weatherReading.getTemperature();
		return sum / readings.size();
	}

	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#getWeatherReadingsForLocation(za.co.solms.location.Location, java.util.Date, java.util.Date)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<WeatherReading> getWeatherReadingsForLocation(Location location,
			Date after, Date onOrBefore)
	{
		Query query = entityManager.createNamedQuery("findAllWeatherReadingsForLocationAndPeriod");
		query.setParameter("location", location);
		query.setParameter("after", after);
		query.setParameter("onOrBefore", onOrBefore);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see za.co.solms.weather.WeatherServices#persistWeatherReading(za.co.solms.weather.WeatherReading)
	 */
	@Override
	public WeatherReading persistWeatherReading(WeatherReading weatherReading)
	{
		entityManager.persist(weatherReading);
		return weatherReading;
	}

	@Override
	public WeatherReading updateWeatherReading(WeatherReading weatherReading)
	{
		return entityManager.merge(weatherReading);
	}

	@Override
	public void removeAllWeatherReadings()
	{
		for (WeatherReading weatherReading: this.getAllWeatherReadings())
			this.removeWeatherReading(weatherReading);
	}

	@Override
	public void removeWeatherReading(WeatherReading weatherReading)
	{
		entityManager.remove(entityManager.merge(weatherReading));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<WeatherReading> getAllWeatherReadings()
	{
		return entityManager.createNamedQuery("findAllWeatherReadings").getResultList();
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public WeatherReading getWeatherReading(int id)
			throws NoWeatherReadingsException
	{
		return entityManager.find(WeatherReading.class, id);
	}
}
