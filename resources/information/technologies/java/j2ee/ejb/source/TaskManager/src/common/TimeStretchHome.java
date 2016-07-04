package za.co.solms.pm;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

public interface TimeStretchHome extends javax.ejb.EJBHome
{
  public TimeStretchRemote create(String id, long start, long end,
                                  String description)
                     throws CreateException, RemoteException;

  public TimeStretchRemote findByPrimaryKey(String id)
                                 throws FinderException,
                                        RemoteException;

  public Collection findAll() throws RemoteException, FinderException;
}
