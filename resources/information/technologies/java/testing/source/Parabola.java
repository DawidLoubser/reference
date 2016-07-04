
package Maths;

import java.awt.geom.*;

public class Parabola implements FunctionDoubleDouble
{
	public Parabola(double c2, double c1, double c0)
	{
		c[2] = c2;
		c[1] = c1;
		c[0] = c0;
	}
	
	public double value(double x)
	{
		return c[2]*x*x + c[1]*x + c[0];
	}
	
	public void setCoefficients(double[] newCoefficients)
	{
	  for (int i=0; i<3; ++i)
	    c[i] = newCoefficients[i]; 
	}    
	
	public double[] getCoefficients()
	{
	  double[] coefficients = new double[3];
	  for (int i=0; i<3; ++i)
	    coefficients[i] = c[i];
	    
	  return coefficients;  
  }

	public double[] getRealRoots()
	{
   	if (c[2] == 0)
    {
	    if (c[1] == 0)
	    	return new double[0];
	    else
	    {
	    	double[] roots = new double[1];
	    	roots[0] = -c[0]/c[1];
	    	return roots;
	    }	
		}
		
		double d = c[1]*c[1] - 4*c[2]*c[0];
		if (d < 0)
			return new double[0];
		else
		  if (d == 0)
  	    {
	      	double[] roots = new double[1];
	      	roots[0] = -c[1]/(2*c[2]);
	      	return roots;
	      }	
		  else
		    {
		    	double[] roots = new double[2];
		    	roots[0] = (-c[1]-Math.sqrt(d))/(2*c[2]);
		    	roots[1] = (-c[1]+Math.sqrt(d))/(2*c[2]);
		    	return roots;
		    }
	}	    
		  	  
  public Point2D.Double getTurningPoint()
  {
  	if (c[2] == 0)
  	  return null;
  	  
  	double x = -c[1]/(2*c[2]);
  	double y = value(x);
  	return new Point2D.Double(x,y);
  }
  
  public String toString()
  {
  	StringBuffer result = new StringBuffer();
  	result.append(c[2]).append("x^2 + ");
  	result.append(c[1]).append("x + ");
  	result.append(c[0]);
  	return result.toString();
  }	
  
  private double[] c = new double[3];
} 
