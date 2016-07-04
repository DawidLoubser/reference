package za.co.solmstcd.chaining;

import java.io.*;

/**
 * A generic application exception
 * The MyAppException class demonstrates
 * exception chaining
 */

public class MyAppException extends Exception
{
	/**
	 * Constructor
	 * @param msg
	 */
    public MyAppException(String msg) {
        super(msg);
    }

    /**
     * This is a new Throwable constructor
     * that contains the cause argument
     * @param msg
     * @param cause
     */
    public MyAppException(String msg, Throwable cause) {
        super(msg, cause); 
    } 
}
