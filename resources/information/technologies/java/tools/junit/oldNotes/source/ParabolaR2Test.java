
package za.co.solmstraining.math.test;

import za.co.solmstraining.math.*;
import java.util.*;

public class ParabolaR2Test extends PolynomialR2Test
{
  public ParabolaR2Test(String name) {super(name);}
  
  public void testTurningPoint()
  {
    Iterator iter = results.keySet().iterator();
    while (iter.hasNext())
    {
      ParabolaR2 parabola = (ParabolaR2)iter.next();
      ParabolaResults result = (ParabolaResults)results.get(parabola);      
      try
      {
        PointR2 tp = parabola.getTurningPoint();
        assertNotNull(tp); // should have not got here
                           // because should have thrown
                           // exception
        assertEquals(result.turningPoint.getX(), tp.getX(), delta);
        assertEquals(result.turningPoint.getY(), tp.getY(), delta);
      }
      catch (NoTurningPointException e)
      {
        assertNull (result.turningPoint); // if NoTurningPointException
                                         // result shuld not have a
                                         // turningPoint
      }
    }                               
  }
  
  public void testgetRealRoots()
  {
    Iterator iter = results.keySet().iterator();
    while (iter.hasNext())
    {
      ParabolaR2 parabola = (ParabolaR2)iter.next();
      ParabolaResults result = (ParabolaResults)results.get(parabola);      
      
      double[] roots = parabola.getRealRoots();
      assertEquals (result.roots.length, roots.length);
      for (int nRoot=0; nRoot<roots.length; ++nRoot)
        assertEquals(result.roots[nRoot], roots[nRoot], delta);
    }    
  }    
  
  public void testGetCoefficient()
  {
    super.testGetCoefficient();

    Iterator iter = results.keySet().iterator();
    while (iter.hasNext())
    {
      ParabolaR2 parabola = (ParabolaR2)iter.next();
      for (int i=3; i<150; ++i)
        assertEquals(parabola.getCoefficient(i), 0, delta);
    }
  }  
  
  protected void setUp()
  {
    super.setUp();

    results = new HashMap();
    results.put(new ParabolaR2(0,2,3), new ParabolaResults(null, new double[] {-1.5}));
    results.put(new ParabolaR2(1,0,0), new ParabolaResults(new PointR2(0,0), new double[] {0}));
    results.put(new ParabolaR2(-2,3.1,1.5), new ParabolaResults(new PointR2(0.775, 2.70125),
                new double[] {1.937163929917,-0.387163929917}));
    results.put(new ParabolaR2(3.3,-5.2,-2.2), new ParabolaResults( 
                new PointR2(0.78787878787878787878787, -4.2484848484848484848484848),
                new double[] {-0.346766377385,1.922523953142}));
    results.put(new ParabolaR2(1, 2, 5), new ParabolaResults(new PointR2(-1,4),
                new double[0]));
  }
  
  private class ParabolaResults  // results structure
  {
    public ParabolaResults(PointR2 turningPoint, double[] roots)
    {
      this.turningPoint = turningPoint;
      this.roots = roots;
    }  
    public PointR2 turningPoint;
    public double[] roots; 
  }
  
  public static junit.framework.Test suite()
  {
    return new junit.framework.TestSuite(ParabolaR2Test.class);
  }  
  
  public static void main(String[] args)
  {
    junit.textui.TestRunner.run(suite());
  }
  
  private static Map results;
  private static final double delta = 1.0e-10;
} 
