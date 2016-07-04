/**
 * 
 */
package za.co.solms.finance;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import za.co.solms.beans.ObservableBase;
import za.co.solms.utils.DateTime.DateUtils;

/**
 * A compounding convention which compounds interest once a year.
 * 
 * @author fritz@solms.co.za
 *
 */
public class AnnualCompounding extends ObservableBase
		implements Compounding
{
	/* (non-Javadoc)
	 * @see za.co.solms.finance.Compounding#getDiscountFactor(java.util.Date, java.util.Date)
	 * 
	 * Note: This method has not yet been tested
	 */
	public double getDiscountFactor(double rate, Date payDate, Date valuationDate)
	{
		// d = (1+r)^n
		double numYears 
			= DateUtils.getNumYears(valuationDate, payDate);
		
		return Math.pow(1+rate, numYears);
	}

	/* (non-Javadoc)
	 * @see za.co.solms.finance.Compounding#getRate(double, java.util.Date, java.util.Date)
	 * 
	 * Note: This method has not yet been tested
	 */
	public InterestRate getRate(double discountFactor,
			Date payDate, Date valuationDate)
	{
		double numYears 
			= DateUtils.getNumYears(valuationDate, payDate);
		
		double rate = Math.pow(10, Math.log10(discountFactor)/numYears) - 1;
		
		return InterestRateImpl.getFactory().create(rate, this);
	}
	
	public boolean equals(Object o)
	{
		return o instanceof ContinuousCompounding;
	}

	public String toString() {return "Annual compounding";}
	
	@Override
	public Set<String> getProperties()
	{
		return properties;
	}

	@Override
	protected Object getThisHandle()
	{
		return this;
	}

	public static AnnualCompounding getInstance() {return theInstance;}
	
	private static final AnnualCompounding theInstance 
			= new AnnualCompounding();
	
	private static Set<String> properties = Collections.unmodifiableSet(new TreeSet<String>());
}
