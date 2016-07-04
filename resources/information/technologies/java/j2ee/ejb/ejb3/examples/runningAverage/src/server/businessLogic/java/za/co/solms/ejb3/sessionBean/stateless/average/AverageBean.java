package za.co.solms.ejb3.sessionBean.stateless.average;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

/**
 * 
 * @author fritz@solms.co.za
 * 
 * A stateless session bean calculating the average of a 
 * collection of floating point numbers. The bean is 
 * accessible via local and remote interfaces.
 */
@Stateless public class AverageBean implements AverageRemote, AverageLocal
{
  public double average(Collection<Double> data)
  {
    if (data.size() == 0)
      return 0;

    double result = 0;
    for (Double d : data)
    {
      result += d.doubleValue();
    }
    return result / data.size();
  }
}
