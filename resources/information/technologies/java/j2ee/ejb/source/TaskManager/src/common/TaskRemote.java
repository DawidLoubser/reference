package za.co.solms.pm;

import java.util.Set;
import java.util.Date;
import java.rmi.RemoteException;

public interface TaskRemote extends javax.ejb.EJBObject
{
  public String getId() throws RemoteException;
  public long getDueDate() throws RemoteException;
  public String getDescription() throws RemoteException;
  public TaskType getTaskType() throws RemoteException;
  public Set getTimeStretches() throws RemoteException;

  public void setDueDate(long dueDate) throws RemoteException;
  public void setDescription(String description) throws RemoteException;

  public long timeSpentInPeriod(long start, long end) throws RemoteException;

  public void addTimeStretch(long start, long end, String description)
                               throws RemoteException;
}
