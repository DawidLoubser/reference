
package Maths.Test;

import Maths.*;
import TestUtils.*;

import java.awt.geom.*;

public class ParabolaTest extends Testable
{
	public ParabolaTest() {}
	
	public static void main(String[] args) 
	{
    while(true);	 
	  //new ParabolaTest().runUserIOTest();
	}
	
	public void runTest() throws TestFailedException
	{
		runTest(50, 2e10);
	}
	
	public void runTest(int numTests, double range) throws TestFailedException
	{
		java.util.Random randomNoGenerator = new java.util.Random();
		
		for (int i=0; i<numTests; ++i)
		{
		  double c2 = randomNoGenerator.nextGaussian()*range - range/2;			  
		  double c1 = randomNoGenerator.nextGaussian()*range - range/2;	
		  double c0 = randomNoGenerator.nextGaussian()*range - range/2;
      
      test(c2, c1, c0);
		} 
  }
  
  private void test(double c2, double c1, double c0)
  {
    Maths.Parabola parabola = new Maths.Parabola(c2, c1, c0);
      
    double[] roots = parabola.getRealRoots();
    Point2D.Double turningPoint = parabola.getTurningPoint();
      
    if (detailedOutput)
    {
      out.println(parabola + ":");
      out.print("roots: ");
      if (roots != null)
        for (int j=0; j<roots.length; ++j)
          out.print(roots[j] + "  ");
      out.println();    
      out.println("turning point = " + turningPoint);
    }
    
    checkRoots(parabola, c2, c1, c0, roots);
    
    checkTurningPoint(c2, c1, c0, turningPoint);
  }
  
  public void checkRoots(Parabola parabola, double c2, double c1, double c0,
                         double[] roots)
  {
    for (int i=0; i<roots.length; ++i)
    {
      double x = roots[i];
      double valueLeft  = parabola.value(x - 1e-12);
      double valueRight = parabola.value(x + 1e-12);
      if (valueLeft * valueRight >= 0)
        throw new TestFailedException("Reported root incorrect.", getClass());
    }
    
    double d = c1*c1 - 4*c2*c0;
    
    if (d == 0)
      if (roots.length != 1)
        throw new TestFailedException("Should only be 1 root", getClass());
    else  
      if (d < 0)
        if (roots.length != 0)
          throw new TestFailedException("Reported real roots but none exist.", getClass());
  }  
  
  private void checkTurningPoint(double c2, double c1, double c0, Point2D.Double pt)
  {
    if ((c2 == 0) && (pt != null))
      throw new TestFailedException
        ("Turning point reported buty none exists.", getClass());
    else
      {
        double x = -c1 / (2*c2);
        if (!isEqual(x, pt.getX()))
          throw new TestFailedException("x-coordinate of turning point wrong.",
                                        getClass());
        
        double y = c2*x*x + c1*x + c0;
        if (!isEqual(y, pt.getY()))
          throw new TestFailedException("y-coordinate of turning point wrong.",
                                        getClass());
      }
  }   
}  
