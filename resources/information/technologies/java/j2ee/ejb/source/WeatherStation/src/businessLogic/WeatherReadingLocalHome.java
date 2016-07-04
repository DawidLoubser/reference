package za.co.solms.weather;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;
import java.util.Date;

public interface WeatherReadingLocalHome extends javax.ejb.EJBLocalHome
{
  public WeatherReadingLocal create(String location, long dateTime,
                                    double temperature)
                                     throws CreateException;

  public WeatherReadingLocal findByPrimaryKey(WeatherReadingPK key)
                                 throws FinderException;

  public Collection findAll() throws FinderException;

  public Collection findAll(String location) throws FinderException;

  public Collection findReadings(String location,
                                 long start, long end) throws FinderException;
}

