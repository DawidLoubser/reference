package za.co.solmstcd.chaining;

public class AvailableDaysException extends LeaveException {
	
		/**
		 * Constructor
		 * @param msg
		 */
	    public AvailableDaysException(String msg) {
	        super(msg);
	    }

	    /**
	     * This is a new Throwable constructor
	     * that contains the cause argument
	     * @param msg
	     * @param cause
	     */
	    public AvailableDaysException(String msg, Throwable cause) {
	        super(msg, cause);	        
	    }
}
