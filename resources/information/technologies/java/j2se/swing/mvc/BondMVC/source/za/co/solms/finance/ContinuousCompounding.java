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
 * A compounding convention which compounds interest continuously.
 * 
 * @author fritz@solms.co.za
 *
 */
public class ContinuousCompounding extends ObservableBase 
		implements Compounding
{
	/* (non-Javadoc)
	 * @see za.co.solms.finance.Compounding#getDiscountFactor(java.util.Date, java.util.Date)
	 */
	public double getDiscountFactor(double rate, Date payDate, Date valuationDate)
	{
		return Math.exp(-rate * DateUtils.getNumYears(payDate, valuationDate));
	}

	/* (non-Javadoc)
	 * @see za.co.solms.finance.Compounding#getRate(double, java.util.Date, java.util.Date)
	 */
	public InterestRate getRate(double discountFactor,
			Date payDate, Date valuationDate)
	{
		double rate = -Math.log(discountFactor) / DateUtils.getNumYears(payDate, valuationDate);
		return InterestRateImpl.getFactory().create(rate, this);
	}
	
	public boolean equals(Object o)
	{
		return o instanceof ContinuousCompounding;
	}

	public String toString() {return "Continuous compounding";}

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

	public static ContinuousCompounding getInstance() {return theInstance;}
	
	private static final ContinuousCompounding theInstance 
			= new ContinuousCompounding();
	
	private static Set<String> properties = Collections.unmodifiableSet(new TreeSet<String>());
}
