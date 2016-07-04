<![CDATA[
package za.co.solmstraining.j2me.finance;

/* Because J2ME has no support for floating point arithmetic. 
   We thus provide our own. */
import za.co.solmstraining.j2me.utils.Decimal;

public class LoanCalculator
{
  public Decimal calcLoanAmount
          (Decimal monthlyInstallment,
           Decimal annualInterestRate,
           int loanPeriodInMonths)
                  throws IllegalArgumentException
  {
    Decimal monthlyRate = annualInterestRate.divide(twelve);

    Decimal mrp1 = one.add(monthlyRate);

    return (monthlyInstallment.divide(monthlyRate).multiply
         (one.subtract(mrp1.pow(-loanPeriodInMonths))));
  }

  public Decimal calcMonthlyInstallment
          (Decimal loanAmount,
           Decimal annualInterestRate,
           int loanPeriodInMonths)
                  throws IllegalArgumentException
  {
    Decimal monthlyRate = annualInterestRate.divide(twelve);
    Decimal mrp1 = one.add(monthlyRate);
    
    return loanAmount.multiply(monthlyRate).divide
      (one.subtract(mrp1.pow(-loanPeriodInMonths)));
  }

  private static final Decimal one = new Decimal(1,0);
  private static final Decimal twelve = new Decimal(12,1);
}
]]>
