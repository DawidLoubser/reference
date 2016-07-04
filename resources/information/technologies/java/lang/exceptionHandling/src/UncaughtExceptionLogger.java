package za.co.solms.exceptions;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * An uncaught excepion handler which logs the exception to a logging stream.
 *
 * @author fritz@solms.co.za
 */
public class UncaughtExceptionLogger 
            implements Thread.UncaughtExceptionHandler
{
  public UncaughtExceptionLogger() {}

  public UncaughtExceptionLogger(String name) {this.name = name;}
  
  public void uncaughtException(Thread thread, Throwable throwable)
  {
    logger.log(Level.SEVERE, name + " " + throwable.toString() 
     + " thrown in thread " + thread.getName() 
     + " from file " + throwable.getStackTrace()[0].getFileName()
     + " on line " + throwable.getStackTrace()[0].getLineNumber());
  }

  private static final Logger logger 
    = Logger.getLogger(UncaughtExceptionLogger.class.getName());
  private String name="";
}
