package za.co.solms.average;

import java.util.Collection;
import javax.ejb.Stateless;
import za.co.solms.average.AverageLocal;
import za.co.solms.average.AverageRemote;

/** A stateless session bean calculating the average of a 
 * collection of floating point numbers. The bean is 
 * accessible via local and remote interfaces.
 */
@Stateless 
public class AverageBean 
implements AverageRemote, AverageLocal
{
  public double average(Collection<Double> data)
  {
    if (data.size() == 0)
      return 0;

    double result = 0;
    for (Double d : data)
    {
      result += d;
    }
    return result / data.size();
  }
}