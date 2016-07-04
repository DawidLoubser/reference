package za.co.solms.ejb3.finance.tax;

import javax.ejb.Local;

/**
 * @author fritz
 *
 */
@Local public interface TaxCalculator 
{
  public double calculateTax(double amount);
}
