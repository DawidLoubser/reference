package za.co.solms.math.functions;

 /**
  * Interface for one-dimensional functions which can be queried for the
  * value of the derivative.
  *
  * @author  Fritz Solms
  * @version 1.0
  */
public interface DifferentiableFunction1D extends Function1D
{
  /**
   * @return the derivative of the function at the point <code>x</code>.
   */
  public double derivative(double x);
}
