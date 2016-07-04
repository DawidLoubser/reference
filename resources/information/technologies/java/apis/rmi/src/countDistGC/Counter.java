
package rmi.countDistGC;

public interface Counter extends java.rmi.Remote
{
  public void increment() throws java.rmi.RemoteException;

  public int getCount() throws java.rmi.RemoteException;
}

