package za.co.solmstcd.chaining;

public class NegativeDaysException extends IllegalArgumentException
{
	/**
	 * Constructor
	 * @param msg
	 */
    public NegativeDaysException(String msg) {
        super(msg);
    }

    /**
     * This is a new Throwable constructor
     * that contains the cause argument
     * @param msg
     * @param cause
     */
    public NegativeDaysException(String msg, Throwable cause) 
    {
        super(msg, cause);
    }
}
