package za.co.solms.weather;

public interface WeatherStation extends javax.ejb.EJBObject
{
  public void addReading(String location, long dateTime,
                        double temperature) 
                        throws java.rmi.RemoteException;

  public double getAverageTemperature(String location,
                                     java.util.Date start,
                                     java.util.Date end)
                                       throws java.rmi.RemoteException;
}
