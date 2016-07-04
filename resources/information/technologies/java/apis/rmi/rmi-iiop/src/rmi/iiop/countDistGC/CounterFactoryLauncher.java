
package rmi.iiop.countDistGC;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CounterFactoryLauncher extends Object
{
    public CounterFactoryLauncher() {}

    public void launch()
                throws java.rmi.RemoteException, java.net.MalformedURLException, NamingException
    {

    	  // Step 1: Instantiate the Hello servant
      	CounterFactoryImpl counterFactoryRef = new CounterFactoryImpl();
      	
        // Step 2: Publish the reference in the Naming Service 
        // using JNDI API
        Context initialNamingContext = new InitialContext();
        initialNamingContext.rebind("CounterFactoryService", counterFactoryRef);
    }

    public static void main(String[] args)
    {    
        try
        {
          new CounterFactoryLauncher().launch();

          System.out.println("CounterFactory launched.");
        }
        catch (Exception e) {e.printStackTrace();}
    }
}

