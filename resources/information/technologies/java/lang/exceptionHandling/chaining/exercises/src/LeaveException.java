package za.co.solmstcd.chaining;

public class LeaveException extends Exception 
{	
	/**
	 * Constructor
	 * @param msg
	 */

	public LeaveException(String msg) {
		super(msg);
	}

	/**
	 * This is a new Throwable constructor
	 * that contains the cause argument
	 * @param msg
	 * @param cause
	 */
	public LeaveException(String msg, Throwable cause) {
		super(msg, cause);		
	}	
}
