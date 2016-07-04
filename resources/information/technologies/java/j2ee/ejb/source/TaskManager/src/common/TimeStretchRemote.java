package za.co.solms.pm;

import java.rmi.RemoteException;

public interface TimeStretchRemote extends javax.ejb.EJBObject
{
  public String getId() throws RemoteException;
  public long getStart() throws RemoteException;
  public long getEnd() throws RemoteException;
  public String getDescription() throws RemoteException;

  public void setStart(long dateTime) throws RemoteException;
  public void setEnd(long dateTime) throws RemoteException;
  public void setDescription(String description) throws RemoteException;

  public long timeSpentInPeriod(long start, long end) throws RemoteException;
}

