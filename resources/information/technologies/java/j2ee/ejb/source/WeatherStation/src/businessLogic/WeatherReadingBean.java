package za.co.solms.weather;

import java.util.Date;

public abstract class WeatherReadingBean implements javax.ejb.EntityBean
{
  public WeatherReadingBean() {}

  //-------------------------------------------------------------------
  //  Abstract accessor methods
  //-------------------------------------------------------------------

  public abstract long getDateTime();
  public abstract String getLocation();
  public abstract double getTemperature();

  public abstract void setDateTime(long dateTime);
  public abstract void setLocation(String location);
  public abstract void setTemperature(double temperature);

  //-------------------------------------------------------------------
  //  Create and postCreate methods
  //-------------------------------------------------------------------

  public WeatherReadingPK ejbCreate(String location, long dateTime,
                                    double temperature)
                                     throws javax.ejb.CreateException
  {
    setLocation(location);
    setDateTime(dateTime);
    setTemperature(temperature);

    return new WeatherReadingPK(location, dateTime);
  }

  public void ejbPostCreate(String location, long dateTime,
                           double temperature) {}

  //-------------------------------------------------------------------
  //  Further methods required by entity beans
  //-------------------------------------------------------------------

  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbLoad() {}
  public void ejbStore() {}

  public void setEntityContext(javax.ejb.EntityContext context)
  {
    this.context = context;
  }

  public void unsetEntityContext() {context = null;}

  protected javax.ejb.EntityContext context;
}

