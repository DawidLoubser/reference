package za.co.solms.weather;

import java.util.Date;

public interface WeatherReadingLocal extends javax.ejb.EJBLocalObject
{
  public long getDateTime();
  public String getLocation();
  public double getTemperature();

  public void setDateTime(long dateTime);
  public void setLocation(String location);
  public void setTemperature(double temperature);
}

