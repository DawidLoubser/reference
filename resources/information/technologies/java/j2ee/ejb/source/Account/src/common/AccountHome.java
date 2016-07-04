package za.co.solms.finance;

import java.util.Collection;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

public interface AccountHome extends javax.ejb.EJBHome
{
  public Account create(String id)
                   throws RemoteException,
                          CreateException;

  public Account findByPrimaryKey(String id)
                   throws RemoteException,
                          FinderException;
/*
  public Collection findByName(String accountName)
                   throws RemoteException,
                          FinderException;
*/
  public Collection findAll()
           throws java.rmi.RemoteException,
                  javax.ejb.FinderException;

}
