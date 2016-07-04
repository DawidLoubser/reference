package za.co.solms.finance;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;

public interface AccountsManager extends EJBObject
{
  public void openAccount(String id, String name)
           throws RemoteException, AccountExistsException;

  public void closeAccount(String id)
           throws RemoteException, AccountExistsException;

  public void deleteAll() throws RemoteException;

  public Account getAccount(String id)
           throws RemoteException, FinderException;

  public Account[] findAll()
           throws RemoteException, FinderException;

  public Account[] lookupByName(String name)
           throws RemoteException, FinderException;
}
