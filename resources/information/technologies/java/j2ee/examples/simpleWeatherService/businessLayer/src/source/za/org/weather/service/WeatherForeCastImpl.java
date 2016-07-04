package za.org.weather.service;

import java.util.Calendar;

/** Simple implementation of a mutable weather forecast
 * (data object) */
public class WeatherForeCastImpl implements WeatherForecast.Mutable
{
  /** Create with the given conditions */
  public WeatherForeCastImpl( Conditions conditions,
      Calendar forecastDate, Locality locality,
      double maxTemp, double minTemp)
  {
    setConditions(conditions);
    setForecastDate(forecastDate);
    setLocality(locality);
    setMaxTemp(maxTemp);
    setMinTemp(minTemp);
  }

  public void setConditions(Conditions conditions)
  {
    this.conditions = conditions;
  }

  public void setForecastDate(Calendar forecastDate)
  {
    this.forecastDate = forecastDate;
  }

  public void setLocality(Locality locality)
  {
    this.locality = locality;
  }

  public void setMaxTemp(double maxTemp)
  {
    this.maxTemp = maxTemp;
  }

  public void setMinTemp(double minTemp)
  {
    this.minTemp = minTemp;
  }

  public Conditions getConditions()
  {
    return conditions;
  }

  public Calendar getForecastDate()
  {
    return forecastDate;
  }

  public Locality getLocality()
  {
    return locality;
  }

  public double getMaxTemp()
  {
    return maxTemp;
  }

  public double getMinTemp()
  {
    return minTemp;
  }

  public String toString()
  {
    return "Forecast for " + locality
    + " (forecastDate=" + forecastDate
    + ", conditions=" + conditions
    + ", maxTemp=" + maxTemp
    + ", minTemp=" + minTemp;
  }
  
  private Conditions conditions;
  private Calendar forecastDate;
  private Locality locality;
  private double maxTemp, minTemp;
}