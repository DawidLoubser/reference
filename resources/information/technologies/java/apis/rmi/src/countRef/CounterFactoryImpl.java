
package rmi.countRef;

public class CounterFactoryImpl extends java.rmi.server.UnicastRemoteObject
                                implements CounterFactory
{
    public CounterFactoryImpl() throws java.rmi.RemoteException {}

    public Counter getCounter() throws java.rmi.RemoteException
    {
        if (counter == null)
          counter = new CounterImpl();

        return counter;
    }
    private static Counter counter;
}

