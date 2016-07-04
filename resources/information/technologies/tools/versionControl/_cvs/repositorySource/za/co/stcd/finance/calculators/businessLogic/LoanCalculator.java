package za.co.stcd.finance.calculators.businessLogic;

public class LoanCalculator
{
  public double calcLoanAmount
          (double monthlyInstallment,
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws IllegalArgumentException
  {
    if (monthlyInstallment < 0)
      throw new IllegalArgumentException
        ("Non-positive monthly installment.");
    if (annualInterestRate <= 0)
      throw new IllegalArgumentException
        ("Non-positive interest rate.");
    if (loanPeriodInMonths <= 0)
      throw new IllegalArgumentException
        ("Non-positive loan period.");

    double monthlyRate = annualInterestRate/12;

    return (monthlyInstallment / monthlyRate)
      * (1 - Math.pow(1+monthlyRate,-loanPeriodInMonths));
  }

  public double calcMonthlyInstallment
          (double loanAmount,
           double annualInterestRate,
           int loanPeriodInMonths)
                  throws IllegalArgumentException
  {
    if (loanAmount < 0)
      throw new IllegalArgumentException
        ("Negative loan amount.");
    if (annualInterestRate <= 0)
      throw new IllegalArgumentException
        ("Non-positive interest rate.");
    if (loanPeriodInMonths <= 0)
      throw new IllegalArgumentException
        ("Non-positive loan period.");

    double monthlyRate = annualInterestRate/12;

    return (loanAmount * monthlyRate)
      / (1 - Math.pow(1+monthlyRate,-loanPeriodInMonths));
  }

  public int calcLoanPeriodInMonths
          (double loanAmount,
           double monthlyInstallment,
           double annualInterestRate)
                  throws IllegalArgumentException
  {
    if (loanAmount < 0)
      throw new IllegalArgumentException
        ("Negative loan amount.");
    if (monthlyInstallment < 0)
      throw new IllegalArgumentException
        ("Non-positive monthly installment.");
    if (annualInterestRate <= 0)
      throw new IllegalArgumentException
        ("Non-positive interest rate.");

    double monthlyRate = annualInterestRate/12;

    return (int)Math.round(-Math.log
      (1-loanAmount*monthlyRate/monthlyInstallment)
      / Math.log(1+monthlyRate) + 0.5);
  }
}

