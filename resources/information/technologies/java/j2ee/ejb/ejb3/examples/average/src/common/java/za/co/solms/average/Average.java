package za.co.solms.average;

import java.util.Collection;

/**
 * A interface for java classes calculating the average of 
 * a collection of floating point numbers.
 */
public interface Average
{
  public double average(Collection<Double> data);
}