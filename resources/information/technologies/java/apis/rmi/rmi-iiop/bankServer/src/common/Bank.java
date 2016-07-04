
package rmi.iiop.bank;

import java.util.Collection;

public interface Bank extends java.rmi.Remote
{
  public Account getAccount(String name) throws java.rmi.RemoteException,
                                                NoSuchAccountException;

  public Collection<String> getAccounts() throws java.rmi.RemoteException;

  public Account newAccount(String name) throws java.rmi.RemoteException,
                                                DuplicateAccountException;
}

