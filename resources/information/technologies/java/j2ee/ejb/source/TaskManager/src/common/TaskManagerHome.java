package za.co.solms.pm;

public interface TaskManagerHome extends javax.ejb.EJBHome
{
  TaskManagerRemote create() throws java.rmi.RemoteException,
                                    javax.ejb.CreateException;
}
