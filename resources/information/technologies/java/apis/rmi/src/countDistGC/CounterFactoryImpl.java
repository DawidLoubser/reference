
package rmi.countDistGC;

public class CounterFactoryImpl extends java.rmi.server.UnicastRemoteObject
                                implements CounterFactory
{
    public CounterFactoryImpl() throws java.rmi.RemoteException {}

    public Counter getCounter() throws java.rmi.RemoteException
    {
        return new CounterImpl();
    }
}

