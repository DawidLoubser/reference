package za.co.solms.math.numeric.solvers;

import za.co.solms.math.functions.DifferentiableFunction1D;
import za.co.solms.math.numeric.NoConvergenceException;

/**
 * Implements the Newton-Raphson method for finding the root of 
 * a one-dimensional function.
 * @author Fritz Solms
 */
public class NewtonRaphson1D
{
  public double findRoot(DifferentiableFunction1D f,
                         double initialGuess,
                         double epsx, double epsf,
                         int maxIterations)
  throws NoConvergenceException
  {
    double x = initialGuess;
    double y, dx;

    int iter = 0;

    do
    {
      y = f.value(x);
      dx = y / f.derivative(x);
      x -= dx;
      ++iter;
    }
    while (((Math.abs(y) > epsf) || (Math.abs(dx) > epsx))
           && (iter < maxIterations));

    if ((Math.abs(y) > epsf) || (Math.abs(dx) > epsx))
      throw new NoConvergenceException();
    return x;
  }
}