
package rmi.iiop.bank;

public interface Account extends java.rmi.Remote, java.io.Serializable
{
  public String getName ()             throws java.rmi.RemoteException;
  public double getBalance ()          throws java.rmi.RemoteException;
  public void   credit (double amount) throws java.rmi.RemoteException;
  public void   debit  (double amount) throws java.rmi.RemoteException,
                                              InsufficientFundsException;
}

