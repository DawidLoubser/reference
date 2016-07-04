package za.co.solms.finance;

public interface AccountsManagerHome extends javax.ejb.EJBHome
{
  AccountsManager create() throws java.rmi.RemoteException,
                                  javax.ejb.CreateException;
}
