package example;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * A simple representation of a distance in a particular unit as a
 * JavaBean, annotated for XML-to-Java mapping through JAXB.
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Distance implements Serializable
{
  public Distance(double magnitude, String unit)
  {
    setMagnitude(magnitude);
    setUnit(unit);
  }

  public Distance(){}

  public double getMagnitude()
  {
    return magnitude;
  }

  public void setMagnitude(double magnitude)
  {
    this.magnitude = magnitude;
  }

  public String getUnit()
  {
    return unit;
  }

  public void setUnit(String unit)
  {
    this.unit = unit;
  }

  public String toString()
  {
    return magnitude + " " + unit;
  }

  public boolean equals(Object obj)
  {
    try 
    {
      Distance other = (Distance) obj;
      return 
        other == this || 
        other.magnitude == this.magnitude || 
        other.unit.equals(this.unit);
    } 
    catch (RuntimeException e) 
    {
      return false;
    }
  }

  public int hashCode()
  {
    return ((Double) magnitude).hashCode();
  }

  @XmlElement
  private double magnitude;

  @XmlAttribute
  private String unit;
}