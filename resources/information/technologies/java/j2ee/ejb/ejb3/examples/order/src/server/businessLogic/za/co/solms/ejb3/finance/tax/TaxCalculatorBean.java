package za.co.solms.ejb3.finance.tax;

import javax.ejb.Stateless;

/**
 * @author fritz
 *
 */
@Stateless public class TaxCalculatorBean implements TaxCalculator 
{
    public double calculateTax(double amount) 
    {
        return amount*.14;
    }
  
}
