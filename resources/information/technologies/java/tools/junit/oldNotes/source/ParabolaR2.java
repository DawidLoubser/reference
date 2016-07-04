
package za.co.solmstraining.math;

import java.util.*;

public class ParabolaR2 extends PolynomialR2
{
  public ParabolaR2(double c2, double c1, double c0)
  {
    super(createCoefficientsMap(c2, c1, c0));
  }
  
  private static SortedMap createCoefficientsMap(double c2, double c1, double c0)
  {
    SortedMap coefficients = new TreeMap();
    coefficients.put(new Integer(0), new Double(c0));
    coefficients.put(new Integer(1), new Double(c1));
    coefficients.put(new Integer(2), new Double(c2));
    return coefficients;
  }  

  public PointR2 getTurningPoint() throws NoTurningPointException
  {
    if (getCoefficient(2) == 0)
      throw new NoTurningPointException("Parabola is a straight line.");
      
    double x = -getCoefficient(1) / (2*getCoefficient(2));
    double y = value(x);
    return new PointR2(x,y);
  }

  public double[] getRealRoots()
  {
    double c0 = getCoefficient(0);
    double c1 = getCoefficient(1);
    double c2 = getCoefficient(2);
    
    if (c2 == 0)
    {
      double[] roots = new double[1];
      roots[0] = -c0/c1;
      return roots;
    }  

    double determinant = c1*c1 - 4*c2*c0;
    
    if (determinant < 0)
      return new double[0];
    else 
      if (determinant == 0)
        {
          double[] result = new double[1];
          result[0] = -c1/(2*c2);
          return result;
        }  
      else
        {
          double t = Math.sqrt(determinant);
          double[] result = new double[2];
          result[0] = (-c1-t)/(2*c2);
          result[1] = (-c1+t)/(2*c2);
          return result;
        }  
  }
}  
