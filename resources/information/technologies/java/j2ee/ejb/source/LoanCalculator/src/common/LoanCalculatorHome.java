package za.co.solms.finance.calculators;

public interface LoanCalculatorHome extends javax.ejb.EJBHome
{
  LoanCalculator create() throws java.rmi.RemoteException,
                                 javax.ejb.CreateException;
}

