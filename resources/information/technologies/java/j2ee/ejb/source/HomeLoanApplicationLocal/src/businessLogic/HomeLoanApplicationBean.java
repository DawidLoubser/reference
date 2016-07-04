package za.co.solms.finance.loans;

import za.co.solms.finance.calculators.LoanCalculatorLocal;
import za.co.solms.finance.calculators.LoanCalculatorLocalHome;
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
                  throws InvalidArgumentsException
  {
    try
    {
      double monthlyInstallment =
        getLoanCalculator().calcMonthlyInstallment
                  (loanAmount, getInterestRate(),
                   repaymentPeriodInMonths);

      System.out.println("installment = " + monthlyInstallment);

      if (monthlyInstallment < 0.3*monthlySalary)
        return true;
      else
        return false;
    }
    catch (javax.naming.NamingException e)
    {
      throw new javax.ejb.EJBException("naming exception: " + e.getMessage());
    }
    catch (javax.ejb.CreateException e)
    {
      throw new javax.ejb.EJBException("creation exception: " + e.getMessage());
    }
  }

  private double getInterestRate()
  {
    return 0.125;
  }

  public LoanCalculatorLocal getLoanCalculator()
                          throws javax.naming.NamingException,
                                 javax.ejb.CreateException
  {
    InitialContext jndiContext = new InitialContext();

    System.out.println("getting loan calculator");
    Object beanHomeRef = jndiContext.lookup("java:comp/env/ejb/LoanCalculator");

    System.out.println("got it: " + beanHomeRef);
    LoanCalculatorLocalHome loanCalculatorLocalHome =
      (LoanCalculatorLocalHome)beanHomeRef;
    System.out.println("Casted to local home.");

    LoanCalculatorLocal calc = loanCalculatorLocalHome.create();

    System.out.println("Created calculator");

    return calc;
  }

  public void ejbCreate() {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void setSessionContext(javax.ejb.SessionContext context) {}
}
