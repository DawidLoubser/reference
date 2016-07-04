package za.co.solms.finance.calculators;

import za.co.solms.utils.InvalidArgumentsException;

public class LoanCalculatorBean
          implements javax.ejb.SessionBean,
                     LoanCalculatorServices
{
  public double calcLoanAmount 
          (double monthlyInstallment, 
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException
  {
    if (monthlyInstallment < 0)
      throw new InvalidArgumentsException
        ("Non-positive monthly installment.");  
    if (annualInterestRate <= 0)
      throw new InvalidArgumentsException
        ("Non-positive interest rate.");  
    if (loanPeriodInMonths <= 0)
      throw new InvalidArgumentsException
        ("Non-positive loan period.");  

    double monthlyRate = annualInterestRate/12;    
        
    return (monthlyInstallment / monthlyRate)
      * (1 - Math.pow(1+monthlyRate,-loanPeriodInMonths));   
  }                         

  public double calcMonthlyInstallment 
          (double loanAmount, 
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException
  {
    if (loanAmount < 0)
      throw new InvalidArgumentsException
        ("Negative loan amount.");  
    if (annualInterestRate <= 0)
      throw new InvalidArgumentsException
        ("Non-positive interest rate.");  
    if (loanPeriodInMonths <= 0)
      throw new InvalidArgumentsException
        ("Non-positive loan period.");  

    double monthlyRate = annualInterestRate/12;    
        
    return (loanAmount * monthlyRate)
      / (1 - Math.pow(1+monthlyRate,-loanPeriodInMonths));   
  }                             
                  
  public int calcLoanPeriodInMonths
          (double loanAmount,
           double monthlyInstallment,
           double annualInterestRate)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException
  {
    if (loanAmount < 0)
      throw new InvalidArgumentsException
        ("Negative loan amount.");
    if (monthlyInstallment < 0)
      throw new InvalidArgumentsException
        ("Non-positive monthly installment.");
    if (annualInterestRate <= 0)
      throw new InvalidArgumentsException
        ("Non-positive interest rate.");

    double monthlyRate = annualInterestRate/12;

    return (int)Math.round(-Math.log
      (1-loanAmount*monthlyRate/monthlyInstallment)
      / Math.log(1+monthlyRate) + 0.5);
  }

  public void ejbCreate() throws javax.ejb.CreateException {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void setSessionContext(javax.ejb.SessionContext context) {}
}
