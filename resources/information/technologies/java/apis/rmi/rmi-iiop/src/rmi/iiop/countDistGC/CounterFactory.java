
package rmi.iiop.countDistGC;


public interface CounterFactory extends java.rmi.Remote
{
  public Counter getCounter()
    throws java.rmi.RemoteException;
}

