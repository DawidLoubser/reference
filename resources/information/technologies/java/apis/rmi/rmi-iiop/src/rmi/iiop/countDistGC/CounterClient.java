
package rmi.iiop.countDistGC;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class CounterClient extends Object
{
    public static void main(String[] args) throws Exception
    {
        Context ic;
        Object objref;
        CounterFactory counterFactory;
        Counter counter;

        try 
        {
            ic = new InitialContext();         
    	
//      using JNDI call.
        objref = ic.lookup("CounterFactoryService");
        System.out.println("Client: Obtained a ref. to Counter Factory Launcher.");

    // STEP 2: Narrow the object reference to the concrete type and
    // invoke the method.
        counterFactory = (CounterFactory) PortableRemoteObject.narrow(
            objref, CounterFactory.class);
        counter = counterFactory.getCounter();
        counter.increment();
        System.out.println("count = " + counter.getCount());
        } catch( Exception e ) {
            System.err.println( "Exception " + e + "Caught" );
            e.printStackTrace( );
            return;
        }
    }
}

