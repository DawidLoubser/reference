package example.jsf;

import javax.ejb.EJB;
import example.*;

/** A View Controller (Command object) that performs unit 
 * conversion. A reference to the unit converter EJB is
 * injected. */
public class UnitConversionCommand
{
  /** The distance which is to be converted */
  public Distance getRequestDistance()
  {
    return requestDistance;
  }

  public void setRequestDistance(Distance requestDistance)
  {
    this.requestDistance = requestDistance;
  }

  /** The unit to which the requestDistance is to be converted */
  public String getRequestUnit()
  {
    return requestUnit;
  }

  public void setRequestUnit(String requestUnit)
  {
    this.requestUnit = requestUnit;
  }

  /** The result after the conversion has been performed */
  public Distance getResultDistance()
  {
    return resultDistance;
  }
  
  
  /** Action method to invoke the command, and return a String 
   * 'outcome' upon which to base navigation rules.
   */
  public String performConversion()
  {
    try
    {
      this.resultDistance = 
        unitConverter.convertDistance( requestDistance, requestUnit );
      return "conversion performed";
    }
    catch (UnsupportedUnitException e)
    {
      return "unsupported unit";
    }
  }
  
  
  // Input and result values
  private Distance requestDistance = new Distance();
  private Distance resultDistance;
  private String requestUnit;

  @EJB
  private UnitConverter unitConverter;
}