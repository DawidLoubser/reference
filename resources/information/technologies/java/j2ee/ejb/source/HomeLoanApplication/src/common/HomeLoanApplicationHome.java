package za.co.solms.finance.loans;

public interface HomeLoanApplicationHome extends javax.ejb.EJBHome
{
  HomeLoanApplication create() throws java.rmi.RemoteException,
                                      javax.ejb.CreateException;
}
