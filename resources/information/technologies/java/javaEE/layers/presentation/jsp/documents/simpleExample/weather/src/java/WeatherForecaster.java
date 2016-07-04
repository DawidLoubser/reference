package za.org.example;

/** A contract for a simplistic weather forecasting service */
public interface WeatherForecaster
{
  /** Returns the forecast for the next day for the given locality.
   * @param localityName The official name of the town, city or area
   * @throws UnknownLocalityException If the locality is not known
   * @throws NoForecastAvailableException If the weather service is aware of
   * the locality, but no forecast is available.
   */
  public WeatherForecast getOneDayForecast( String localityName )
  throws UnknownLocalityException, NoForecastAvailableException;
}