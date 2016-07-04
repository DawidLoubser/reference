package za.co.solms.weather;

public interface WeatherStationHome extends javax.ejb.EJBHome
{
  WeatherStation create() throws java.rmi.RemoteException,
                                javax.ejb.CreateException;
}

