package za.co.solms.finance.loans;

import za.co.solms.finance.calculators.LoanCalculator;
import za.co.solms.finance.calculators.LoanCalculatorHome;
import za.co.solms.utils.InvalidArgumentsException;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class HomeLoanApplicationBean
          implements javax.ejb.SessionBean
{
  public boolean applyForHomeLoan
          (double loanAmount,
           int repaymentPeriodInMonths,
           double monthlySalary)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException
  {
    try
    {
      double monthlyInstallment =
        getLoanCalculator().calcMonthlyInstallment
                  (loanAmount, getInterestRate(), 
                   repaymentPeriodInMonths);
                   
      if (monthlyInstallment <= 0.3*monthlySalary)
        return true;
      else
        return false;               
    }
    catch (javax.naming.NamingException e)
    {
      throw new java.rmi.RemoteException();
    }      
    catch (javax.ejb.CreateException e)
    {
      throw new java.rmi.RemoteException();
    }      
  }
  
  private double getInterestRate()
  {
    return 0.125;
  }  
  
    
  public LoanCalculator getLoanCalculator() 
                          throws javax.naming.NamingException,
                                 java.rmi.RemoteException,
                                 javax.ejb.CreateException
  {
    System.out.println("getting loan caluclator");
    InitialContext jndiContext = new InitialContext();
    
    Object beanHomeRef
      = jndiContext.lookup("java:comp/env/ejb/finance/LoanCalculator");
    LoanCalculatorHome loanCalculatorHome =
      (LoanCalculatorHome)PortableRemoteObject.narrow
        (beanHomeRef, LoanCalculatorHome.class);
    
    return loanCalculatorHome.create();
  }
    
  public void ejbCreate() {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void setSessionContext(javax.ejb.SessionContext context) {}
}
