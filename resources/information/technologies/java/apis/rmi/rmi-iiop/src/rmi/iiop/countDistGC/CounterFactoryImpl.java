
package rmi.iiop.countDistGC;

import javax.rmi.PortableRemoteObject;
public class CounterFactoryImpl extends PortableRemoteObject
                                implements CounterFactory
{
    public CounterFactoryImpl() throws java.rmi.RemoteException {}

    public Counter getCounter() throws java.rmi.RemoteException
    {
        return new CounterImpl();
    }
}

