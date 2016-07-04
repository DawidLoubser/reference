package za.co.solms.finance;

public interface Account extends javax.ejb.EJBObject
{
  public String getId()
                  throws java.rmi.RemoteException;
  
  public String getName()
                  throws java.rmi.RemoteException;
                  
  public void setName(String newName)
                  throws java.rmi.RemoteException; 
  
  public double getBalance()
                  throws java.rmi.RemoteException;
  
  public void credit(double amount)
                  throws java.rmi.RemoteException;
  
  public void debit(double amount)
                  throws java.rmi.RemoteException,
                         InsufficientFundsException;
}
