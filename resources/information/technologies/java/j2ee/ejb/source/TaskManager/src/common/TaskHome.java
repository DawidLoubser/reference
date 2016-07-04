package za.co.solms.pm;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

public interface TaskHome extends javax.ejb.EJBHome
{
  public TaskRemote create(String id, String description, long dueDate,
                           TaskType taskType)
                     throws CreateException, RemoteException;

  public TaskRemote findByPrimaryKey(String id)
                                 throws FinderException,
                                        RemoteException;

  public Collection findAll() throws RemoteException, FinderException;

  public Collection findDueBefore(long dateTime) throws FinderException,
                                                        RemoteException;
}

