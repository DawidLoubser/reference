package za.org.weather.service;

import java.util.Calendar;

/** Contract for a single, simple weather forecast (data object), 
 * for a particular place on a particular date.
 */
public interface WeatherForecast
{
  /** Where the forecast is for */
  public Locality getLocality();
	
  /** The date for which a this forecast applies */
  public Calendar getForecastDate();
	
  /** Minimum expected temperature */
  public double getMinTemp();
	
  /** Maximum expected temperature */
  public double getMaxTemp();
	
  /** The expected conditions */
  public Conditions getConditions();
	
  
  /** Mutable view of a weather forecast */
  public interface Mutable extends WeatherForecast
  {
    public void setLocality( Locality locality );
    public void setForecastDate( Calendar forecastDate );
    public void setMinTemp( double minTemp );
    public void setMaxTemp( double maxTemp );
    public void setConditions( Conditions conditions );
  }
	
  /** The standard available 'conditions' supported
   * by the weather service.
   */
  public enum Conditions
  {
    Sunny, PartlyCloudy, Cloudy, Rain, Snow
  }
}