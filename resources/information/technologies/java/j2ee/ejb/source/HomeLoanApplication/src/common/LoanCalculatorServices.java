package za.co.solms.finance.calculators;

import za.co.solms.utils.InvalidArgumentsException;

public interface LoanCalculatorServices
{
  public double calcLoanAmount
          (double monthlyInstallment,
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException;

  public double calcMonthlyInstallment
          (double loanAmount,
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException;

  public int calcLoanPeriodInMonths
          (double loanAmount,
           double monthlyInstallment,
           double annualInterestRate)
                  throws java.rmi.RemoteException,
                         InvalidArgumentsException;
}
