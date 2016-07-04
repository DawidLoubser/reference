package za.org.weather.service;

import java.util.Calendar;
import java.util.List;

/** A contract for a simple weather forecasting service */
public interface WeatherForecastService
{
  /** Requests all the known forecasts for a particular place,
   * from a particular date henceforth.
   * @param place The place (locality)
   * @param startDate The starting date (earliest forecast)
   * @return A list of all forecasts meeting the criteria
   * @throws UnknownLocalityException If the locality is not known
   */
  public List<WeatherForecast> getAllForecasts( 
    Locality place, 
    Calendar startDate )
  throws UnknownLocalityException;
}