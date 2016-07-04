package za.co.solms.weather;

import java.util.*;

public class WeatherStationBean implements javax.ejb.SessionBean
{
  public WeatherStationBean() {}

  public void addReading(String location, long dateTime,
                        double temperature)
                          throws java.rmi.RemoteException,
                            javax.ejb.EJBException
  {
    try
    {
      javax.naming.Context namingContext = new javax.naming.InitialContext();


      System.out.println
        (namingContext.lookup("java:comp/env/ejb/WeatherReading").getClass());

      WeatherReadingLocalHome weatherReadingHome = (WeatherReadingLocalHome)
        namingContext.lookup("java:comp/env/ejb/WeatherReading");

      System.out.println(weatherReadingHome.getClass());

      weatherReadingHome.create(location, dateTime, temperature);

    }
    catch (Exception e)
    {
      throw new javax.ejb.EJBException(e);
    }
  }

  public double getAverageTemperature(String location,
                                     java.util.Date start,
                                     java.util.Date end)
  {
    double avg = 0;
    try
    {
      javax.naming.Context namingContext = new javax.naming.InitialContext();

      WeatherReadingLocalHome weatherReadingHome = (WeatherReadingLocalHome)
        namingContext.lookup("java:comp/env/ejb/WeatherReading");

      Collection readings
        = weatherReadingHome.findReadings(location,
                                          start.getTime(),
		          end.getTime());

      double sum = 0;
      Iterator iter = readings.iterator();
      while (iter.hasNext())
      {
        Object o = iter.next();
        WeatherReadingLocal reading = (WeatherReadingLocal)o;
        System.out.println("reading: " + reading);
        System.out.println("  temp: " + reading.getTemperature());
        sum += reading.getTemperature();
      }
      avg = sum / readings.size();
    }
    catch (Exception e)
    {
      throw new javax.ejb.EJBException(e);
    }
    return avg;
  }

  //-------------------------------------------------------------------
  //  Further methods required by session beans
  //-------------------------------------------------------------------

  public void ejbCreate() throws javax.ejb.CreateException {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbPostCreate() {}

  public void setSessionContext(javax.ejb.SessionContext context)
  {
    this.context = context;
  }

  public void unsetSessionContext() {context = null;}

  protected javax.ejb.SessionContext context;
}
