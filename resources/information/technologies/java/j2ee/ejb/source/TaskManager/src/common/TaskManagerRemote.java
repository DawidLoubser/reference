package za.co.solms.pm;

public interface TaskManagerRemote extends javax.ejb.EJBObject
{
  public void addTask(String id, String description, long dueDate,
                      TaskType taskType)
                throws java.rmi.RemoteException;

  public TaskRemote getTask(String id) throws java.rmi.RemoteException;
}
