
package za.co.SolmsTraining.math.numeric.solvers;

import za.co.SolmsTraining.math.functions.*;

public class RootSolvers
{
  private RootSolvers() {}

  public static double biSection (FunctionDoubleDouble function,
                                  double lowerBound, double upperBound,
                                  double epsx, int maximumNoOfIterations)
  {
    double fLowerBound = function.value(lowerBound);
    double fUpperBound = function.value(upperBound);
    
    if (fLowerBound * fUpperBound > 0)
      throw new IllegalArgumentException
                  ("Function does not change sign over supplied interval.");
        
    double fx, eps, x;
    
    int iterationNo = 0;              
    do
    {
      eps = (upperBound - lowerBound) / 2;
      x   = lowerBound + eps;
      fx  = function.value(x);

      if (fLowerBound * fx > 0)                  
        {
          lowerBound  = x;
          fLowerBound = fx;
        }
      else
        {
          upperBound  = x;
          fUpperBound = fx;
        }
      ++iterationNo;
      if (iterationNo > maximumNoOfIterations)
        throw new NoConvergenceException();
    }
    while ((fx != 0) && (Math.abs(eps) > epsx)); 
    
    return x;
  }
}                       

class NoConvergenceException extends RuntimeException
{
  public NoConvergenceException() {}
  
  public NoConvergenceException(String msg) {super(msg);}
}  

