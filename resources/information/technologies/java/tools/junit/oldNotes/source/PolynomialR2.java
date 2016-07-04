
package za.co.solmstraining.math;

import java.util.*;

public class PolynomialR2 implements FunctionR2
{
  public PolynomialR2(SortedMap coefficients)
  {
    this.coefficients = new TreeMap(coefficients);
  }
  
  public double value(double x)
  {
    double sum = 0;
    Iterator iter = coefficients.keySet().iterator();
    while (iter.hasNext())
    {
      Integer power = (Integer)iter.next();
      double coefficient = ((Double)coefficients.get(power)).doubleValue();
      sum += coefficient * Math.pow(x, power.intValue());
    }  
      
    return sum;
  }
  
  public double getCoefficient(int power)
  {
    Double coefficient = (Double)coefficients.get(new Integer(power));
    if (coefficient == null)
      return 0;
    else
      return coefficient.doubleValue();  
  }
      
  public String toString()
  {
    StringBuffer result = new StringBuffer();

    boolean first = true;
    Iterator iter = coefficients.keySet().iterator();
    while (iter.hasNext())
    {
      Integer power = (Integer)iter.next();
      double coefficient = ((Double)coefficients.get(power)).doubleValue();
      
      if ((!first) && (coefficient > 0))
        result.append("+");
    
      int pow = power.intValue();
      result.append(coefficient);
      if (pow == 1)
        result.append("x");
      else if (pow > 1)
        result.append("x^").append(pow);
      first = false;  
    }
    return result.toString();
  }        
  
  private SortedMap coefficients;
}  
