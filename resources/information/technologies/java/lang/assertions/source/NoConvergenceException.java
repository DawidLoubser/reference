package za.co.solms.math.numeric;

/**
 * This exception is thrown when a numeric algorithm does not achieve
 * convergence within the assigned boundary conditions.
 *
 * @author Fritz Solms
 * @version 1.0
 */
public class NoConvergenceException extends Exception
{
  /**
   * Creates an instance of the exception.
   */  
  public NoConvergenceException() {}
  
  /**
   * Creates an instance of the exception with a message.
   */  
  public NoConvergenceException(String msg) {super(msg);}
}
