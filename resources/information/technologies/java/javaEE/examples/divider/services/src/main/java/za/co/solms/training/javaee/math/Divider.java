package za.co.solms.training.javaee.math;
/**
 * A simple divider introduced as a minimal example of some business logic.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface Divider
{
	/**
	 * 
	 * @param numerator the thing above the line (the thing you want to divide)
	 * @param denominator the thing below the line (the thing you want to divide with)
	 * @return the result of the division: numerator / denominator
	 * @throws DivideByZeroException if the denominator is zero
	 */
	public double divide(double numerator, double denominator) 
		throws DivideByZeroException;
	
	/**
	 * An exception used to notify users that a service could not be provided
	 * because  the user inputs lead to a divide by zero.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public class DivideByZeroException extends Exception {}
}
