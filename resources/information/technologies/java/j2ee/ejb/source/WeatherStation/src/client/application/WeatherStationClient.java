package za.co.solms.weather;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.util.*;

public class WeatherStationClient
{
  public void run()
  {
    try
    {
      WeatherStation weatherStation = connect();

      String location = "Gammadoelas";

      Date now = new Date();
      weatherStation.addReading(location, now.getTime(), 23);

      Date start = new GregorianCalendar(2000,0,1).getTime();
      Date end = new GregorianCalendar(2004,0,1).getTime();

      double avg
        = weatherStation.getAverageTemperature(location, start, end);

      System.out.println("avg: " + avg);

      weatherStation.remove();
    }
    catch (Exception e)
    {
      e.printStackTrace(System.out);
    }
  }

  public WeatherStation connect()
  {
    try
    {
      InitialContext jndiContext = new InitialContext();

      System.out.println("Now looking up session bean " + jndiName + " ...");

      Object beanHomeRef = jndiContext.lookup(jndiName);

      System.out.println("got it");

      WeatherStationHome home =
        (WeatherStationHome)PortableRemoteObject.narrow
          (beanHomeRef, WeatherStationHome.class);

      return home.create();
    }
    catch (Exception e)
    {
      System.out.println("Could not connect to weather station.");
      e.printStackTrace(System.out);
      System.exit(0);
    }
    return null;
  }

  public static void main(String[] args)
  {
    new WeatherStationClient().run();
  }

  private static final String jndiName = "ejb/WeatherStation";
  private static final java.text.DateFormat dateFormat
    = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
}

