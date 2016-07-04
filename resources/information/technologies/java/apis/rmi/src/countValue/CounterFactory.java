
package rmi.countValue;

public interface CounterFactory extends java.rmi.Remote
{
  public Counter getCounter() throws java.rmi.RemoteException;
}

