package za.co.solms.finance.loans;

import za.co.solms.utils.InvalidArgumentsException;

public interface HomeLoanApplication extends javax.ejb.EJBObject
{
  public boolean applyForHomeLoan
          (double loanAmount,
           int repaymentPeriodInMonths,
           double monthlySalary)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException;
}
