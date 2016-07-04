package za.co.solms.astrology;

import java.io.Serializable;
import java.util.Date;

/** A contract for an astrology forecast service */
public interface AstrologyFeeder
{
  /**
   * Generates a forecast based on the provided personal information.
   * @param personInfo the information about the person for which the 
   *  forecast is requested. 
   * @return text representing the forecast.
   */
  public String generateForecast( PersonInfo personInfo) 
  throws WontSayException;


  /**
   * The personal information required for a forecast. 
   */
  public class PersonInfo implements Serializable
  {
    public PersonInfo( String name, 
        String eMailAddress, Date dateOfBirth)
    {
      this.name = name;
      this.eMailAddress = eMailAddress;
      // To enforce encapsulation (composition
      // relationship) we clone the given date
      this.dateOfBirth = (Date)dateOfBirth.clone();
    }

    public String getName()
    {
      return name;
    }

    public String getEMailAddress()
    {
      return eMailAddress;
    }

    public Date getDateOfBirth()
    {
      return (Date) dateOfBirth.clone();
    }

    private String name, eMailAddress;
    private Date dateOfBirth;
    private static final long serialVersionUID = 200609192036L;
  }


  /**
   * This exception is thrown if the Astrology service is not prepared to
   * publish the forecast for the person for which the forecast was requested. 
   */
  public class WontSayException extends Exception {}
}