
package rmi.countDistGC;

public class CounterImpl extends java.rmi.server.UnicastRemoteObject
    implements Counter, java.io.Serializable, java.rmi.server.Unreferenced
{
    /** Creates new CounterImpl */
    public CounterImpl() throws java.rmi.RemoteException {}

    public void increment() throws java.rmi.RemoteException {++count;}

    public int getCount() throws java.rmi.RemoteException {return count;}

    public void finalize() throws Throwable
    {
        System.out.println("Counter's finalize() called.");
        super.finalize();
    }

    public void unreferenced()
    {
        System.out.println("Counter's unreferenced() called.");
    }

    private int count = 0;
}

