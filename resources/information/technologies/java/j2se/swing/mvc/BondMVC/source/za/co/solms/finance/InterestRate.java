/**
 * 
 */
package za.co.solms.finance;

import java.util.Date;

import za.co.solms.beans.Observable;
import za.co.solms.lang.Cloneable;
import za.co.solms.utils.primitives.ObservableDouble;

/**
 * Encapsulates an interest rate with an associated compounding convention
 * @author fritz@solms.co.za
 *
 */
public interface InterestRate extends Observable, Cloneable
{
	/**
	 * 
	 * @return the annualized rate for the compounding convention used by this
	 * interest rate.
	 */
	public ObservableDouble getRate();
	
	/**
	 * 
	 * @return the compounding convention used by the interest rate.
	 */
	public Compounding getCompounding();
	
	/**
	 * Discounts a payment for a certain amount from a supplied payment to a valuation date.
	 * 
	 * @param amount the amount to be discounted.
	 * @param payDate the date from which the amount should be discounted from.
	 * @param valuationDate the date to which the amount is to be discounted to.
	 * @return the value of the amount on the valuation date.
	 */
	public double discount(double amount, Date payDate, Date valuationDate);
	
	/**
	 * An interface for mutable (read-write) handles for interest rates.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public interface Mutable extends InterestRate
	{
		/**
		 * 
		 * @param newRate changes the annualized rate for this interest rate.
		 */
		public void setRate(double newRate);
		
		/**
		 * 
		 * @param newCompounding changes the compounding convention used by this 
		 * interest rate.
		 */
		public void setCompounding(Compounding newCompounding);
    
    /**
     * 
     * @author fritz@solms.co.za
     *
     */
    public interface Direct extends Mutable
    {
       public ObservableDouble.Mutable mutableGetRate();
       public void directSetRate(ObservableDouble.Mutable rate);
       
       public CompoundingType.Mutable mutableGetCompoundingType();
       public void directSetCompoundingType(CompoundingType.Mutable compudingType);
    }
	}
	
	/**
	 * An interface for factories through which interest rates are obtained.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public interface Factory
	{
		/**
		 * Returns a handle to a mutable interest rate.
		 * 
		 * @param annualizedRate the annualized interest rate.
		 * @param compounding the compounding convention to be used by the interest rate
		 * @return the interest rate created by the factory
		 */
		public InterestRate.Mutable create(double annualizedRate, Compounding compounding);
	}
	
	public static final String COMPOUNDING_PROPERTY = "compounding";
	public static final String RATE_PROPERTY = "rate";
}
