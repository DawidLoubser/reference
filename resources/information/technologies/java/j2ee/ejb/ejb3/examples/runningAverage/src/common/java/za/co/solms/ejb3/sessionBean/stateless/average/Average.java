package za.co.solms.ejb3.sessionBean.stateless.average;

import java.util.Collection;

/**
 * @author fritz@solms.co.za
 * 
 * A interface for java classes calculating the average of 
 * a collection of floating point numbers.
 */
public interface Average
{
  public double average(Collection<Double> data);
}
