package za.co.solms.assertions;

import za.co.solms.math.functions.DifferentiableFunction1D;
import za.co.solms.math.numeric.solvers.NewtonRaphson1D;
import za.co.solms.math.numeric.NoConvergenceException;

public class AssertionTest
{
  public static void main(String[] args)
  {
    new AssertionTest().run();
  }

  public void run()
  {
    DifferentiableFunction1D f = new DifferentiableFunction1D()
      {
        public double value(double x)
        {
          return 3*Math.pow(x,6) - 4*Math.pow(x,4) +7*x -16 
             + Math.exp(x);
        }

        public double derivative(double x)
        {
          return 18*Math.pow(x,5) - 16*Math.pow(x,3) + 7 
             + Math.exp(x);
        }
      };

    NewtonRaphson1D solver = new NewtonRaphson1D();

    try
    {
      double eps = 1e-6;

      double root = solver.findRoot(f, 5.5, eps, eps, 500);

      assert f.value(root) == 0: "Root solving error.";

      System.out.println("The root is " + root);
      System.out.println("f(root) = " + f.value(root));
    }
    catch (NoConvergenceException e)
    {
      e.printStackTrace();
    }
  }
}