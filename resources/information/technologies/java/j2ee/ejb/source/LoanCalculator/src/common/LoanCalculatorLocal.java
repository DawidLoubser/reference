package za.co.solms.finance.calculators;

import za.co.solms.utils.InvalidArgumentsException;

public interface LoanCalculatorLocal extends javax.ejb.EJBLocalObject
{
  public double calcLoanAmount
          (double monthlyInstallment,
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws InvalidArgumentsException;

  public double calcMonthlyInstallment
          (double loanAmount,
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws InvalidArgumentsException;

  public int calcLoanPeriodInMonths
          (double loanAmount,
           double monthlyInstallment,
           double annualInterestRate)
                  throws InvalidArgumentsException;
}
