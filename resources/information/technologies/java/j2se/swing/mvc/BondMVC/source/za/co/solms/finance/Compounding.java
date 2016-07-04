/**
 * 
 */
package za.co.solms.finance;

import java.util.Date;

import za.co.solms.beans.Observable;

/**
 * A common interface which must be implemented by the various compounding conventions.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface Compounding extends Observable
{
	/**
	 * Given the annualized rate in the specific compounding convention, this method
	 * returns the factor by which a payment on payDate should be mutiplied
	 * in order to obtain its value on valuationDate.
	 * 
	 * @param rate the annualized rate for which the discounting factor is requested.
	 * @param payDate the payDate from which the payment will be discounted.
	 * @param valuationDate the valuation date for which the discount factor is required.
	 * @return the discount factor applicable for discounting a payment on payDate to the
	 * valuationDate.
	 */
	public double getDiscountFactor(double rate, Date payDate, Date valuationDate);
	
	/**
	 * Returns an interest rate with this discount factor as calculated from the provided
	 * discount factor for provided pay and valuation dates. 
	 * @param discountFactor the discount factor for the period.
	 * @param payDate the payDate for the discounting factor.
	 * @param valuationDate the valuation date for the supplied discount factor.
	 * @return the interest rate using this compounding convention which yields
	 * 	the supplied discounting factor over the supplied period.
	 */
	public InterestRate getRate(double discountFactor, Date payDate, Date valuationDate);
}
