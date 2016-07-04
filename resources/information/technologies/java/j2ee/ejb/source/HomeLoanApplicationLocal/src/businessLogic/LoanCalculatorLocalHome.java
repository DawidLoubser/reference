package za.co.solms.finance.calculators;

public interface LoanCalculatorLocalHome extends javax.ejb.EJBLocalHome
{
  LoanCalculatorLocal create() throws javax.ejb.CreateException;
}
