
package za.co.solmstraining.math.test;

import za.co.solmstraining.math.*;
import java.util.*;

public class PolynomialR2Test extends junit.framework.TestCase
{
  public PolynomialR2Test(String name) {super(name);}

  public void testValue()
  {
    System.out.println("Polynomial::testValue()");
    Iterator polynomialsIter = fixture.keySet().iterator();
    while (polynomialsIter.hasNext())
    {
      PolynomialR2 polynomial = (PolynomialR2)polynomialsIter.next();
      List functionValues = ((PolynomialResults)fixture.get(polynomial)).functionValues;
      Iterator pointsIter = functionValues.iterator();
      while (pointsIter.hasNext())
      {
        PointR2 result = (PointR2)pointsIter.next();
        assertEquals(result.getY(), polynomial.value(result.getX()), delta);
      }  
    }
  }
  
  public void testGetCoefficient()
  {
    Iterator polynomialsIter = fixture.keySet().iterator();
    while (polynomialsIter.hasNext())
    {
      PolynomialR2 polynomial = (PolynomialR2)polynomialsIter.next();
      SortedMap coefficients = ((PolynomialResults)fixture.get(polynomial)).coefficients;
      Iterator iter = coefficients.keySet().iterator();
      while (iter.hasNext())
      {
        Integer power = (Integer)iter.next();
        assertEquals(((Double)coefficients.get(power)).doubleValue(),
                      polynomial.getCoefficient(power.intValue()), delta);
      }
    }                  
  }    
  
  protected void setUp()
  {
    fixture = new HashMap();
    
    SortedMap coefficients = new TreeMap();
    PolynomialR2 polynomial = new PolynomialR2(coefficients);
    
    List functionValues = new LinkedList();
    functionValues.add(new PointR2(0,0));
    functionValues.add(new PointR2(1,0));
    functionValues.add(new PointR2(7.1,0));
    functionValues.add(new PointR2(-1.5,0));
    
    fixture.put(polynomial, new PolynomialResults(coefficients, functionValues));

    coefficients = new TreeMap();
    coefficients.put(new Integer(0), new Double(1.1));
    coefficients.put(new Integer(1), new Double(2.2));
    polynomial = new PolynomialR2(coefficients);
    
    functionValues = new LinkedList();
    functionValues.add(new PointR2(0,1.1));
    functionValues.add(new PointR2(1,3.3));
    functionValues.add(new PointR2(7.1,16.72));
    functionValues.add(new PointR2(-1.5,-2.2));
    
    fixture.put(polynomial, new PolynomialResults(coefficients, functionValues));

    coefficients = new TreeMap();
    coefficients.put(new Integer(0), new Double(1.1));
    coefficients.put(new Integer(1), new Double(2.2));
    coefficients.put(new Integer(2), new Double(4.4));
    polynomial = new PolynomialR2(coefficients);

    functionValues = new LinkedList();
    functionValues.add(new PointR2(0,1.1));
    functionValues.add(new PointR2(1,7.7));
    functionValues.add(new PointR2(7.1,238.524));
    functionValues.add(new PointR2(-1.5,7.7));
    
    fixture.put(polynomial, new PolynomialResults(coefficients, functionValues));

    coefficients = new TreeMap();
    coefficients.put(new Integer(0), new Double(9));
    coefficients.put(new Integer(1), new Double(17));
    coefficients.put(new Integer(3), new Double(-6.1));
    coefficients.put(new Integer(5), new Double(0.2));
    coefficients.put(new Integer(6), new Double(-4.5));
    coefficients.put(new Integer(8), new Double(-12.3));
    polynomial = new PolynomialR2(coefficients);

    functionValues = new LinkedList();
    functionValues.add(new PointR2(0,9));
    functionValues.add(new PointR2(1,3.3));
    functionValues.add(new PointR2(7.1,-80002580.71927108));
    functionValues.add(new PointR2(-1.5,-363.924609375));
    
    fixture.put(polynomial, new PolynomialResults(coefficients, functionValues));
  }
  
  private class PolynomialResults
  {
    public PolynomialResults(SortedMap coefficients, List functionValues)
    {
      this.coefficients = coefficients;
      this.functionValues = functionValues;
    }
    
    public SortedMap coefficients;
    public List functionValues;
  }    
  
  public static junit.framework.Test suite()
  {
    return new junit.framework.TestSuite(PolynomialR2Test.class);
  }  
  
  public static void main(String[] args)
  {
    junit.textui.TestRunner.run(suite());
  }
  
  private Map fixture;
  private static final double delta = 1.0e-10;
} 
