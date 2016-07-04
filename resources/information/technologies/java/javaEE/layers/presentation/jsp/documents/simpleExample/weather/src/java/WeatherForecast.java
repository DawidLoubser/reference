package za.org.example;

import java.util.Date;

/** Represents a single weather forecast for a certain locality,
 * for a certain date. */
public class WeatherForecast implements java.io.Serializable
{
  public String getLocality()
  {
    return locality;
  }

  public void setLocality(String locality)
  {
    this.locality = locality;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public double getMinTemp()
  {
    return minTemp;
  }

  public void setMinTemp(double minTemp)
  {
    this.minTemp = minTemp;
  }

  public double getMaxTemp()
  {
    return maxTemp;
  }

  public void setMaxTemp(double maxTemp)
  {
    this.maxTemp = maxTemp;
  }

  public Conditions getConditions()
  {
    return conditions;
  }

  public void setConditions(Conditions conditions)
  {
    this.conditions = conditions;
  }
  
  
  private String locality;
  private Date date;
  private double minTemp, maxTemp;
  private Conditions conditions;

  
  public enum Conditions
  {
    Cloudy, Sun, Rain, Snow;
  }
}