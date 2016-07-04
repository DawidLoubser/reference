package za.co.solms.average;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import za.co.solms.average.AverageRemote;

/**
 * Client for the stateless session bean Average via its 
 * remote interface.
 */
public class Client
{
  public static void main(String args[])
  {
    Random rng = new Random();
    try
    {
      int numDataPoints = 10000;
      Collection<Double> data = new LinkedList<Double>();
      for (int i = 0; i < numDataPoints; ++i)
        data.add(new Double(rng.nextDouble() * 100));

      /*
       * Initialize the reference to the naming service using the
       * naming service specified in a jndi.properties file available 
       * within the class path.
       */  
      InitialContext ctx = new InitialContext();
      
      System.out.println("Got Naming Service");
      
      /*
       * Look up the bean's EJB remote object via JNDI via the default 
       * JNDI name (the name of the remote interface) and cast the
       * received object reference to AverageRemote.
       */
      String jndiName = "AverageBean/remote";
      System.out.println("Now looking up " 
      + jndiName+ " in JNDI tree.");
      AverageRemote avg 
        = (AverageRemote) ctx.lookup(jndiName);
      
      System.out.println("Got remote handle to session bean");

      /* 
       * Here we are calling the remote session bean:
       */
      double average = avg.average(data);

      System.out.println("The average is " + average);
    } 
    catch (NamingException e)
    {
      System.out.println("Could not find bean with specified JNDI name.");
      e.printStackTrace();
    }
  }
}
