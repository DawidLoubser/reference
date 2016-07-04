package za.co.solms.weather;

import java.util.Date;

public class WeatherReadingPK
                implements java.io.Serializable
{
  public WeatherReadingPK() {}

  public WeatherReadingPK(String location, long dateTime)
  {
    this.location = location;
    this.dateTime = dateTime;
  }

  public String toString()
  {
    return location + ": " + dateFormat.format(new Date(dateTime));
  }

  public boolean equals(Object obj)
  {
    try
    {
      WeatherReadingPK arg = (WeatherReadingPK)obj;
      return (location.equals(arg.location)
              && (dateTime == arg.dateTime));
    }
    catch (ClassCastException e)
    {
      return false;
    }
  }

  public int hashCode()
  {
    return (int)(dateTime + location.hashCode());
  }

  public String location;
  public long dateTime;

  private static final java.text.DateFormat dateFormat
    = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
}
