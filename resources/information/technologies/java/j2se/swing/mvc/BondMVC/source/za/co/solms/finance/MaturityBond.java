/**
 * 
 */
package za.co.solms.finance;

import java.util.Date;

import za.co.solms.beans.Observable;
import za.co.solms.utils.DateTime.ObservableDate;

/**
 * A bond which pays out on maturity the face value together with the
 * interest for the entire period.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface MaturityBond extends Observable
{
	/**
	 * 
	 * @return the face value (loan amount) for the bond
	 */
	public double getFaceValue();
	
	/**
	 * 
	 * @return the interest rate paid on the loan
	 */
	public InterestRate getInterestRate();
	
	/**
	 * 
	 * @return the date on which ownership was obtained.
	 */
	public ObservableDate getEffectiveDate();
	
	/**
	 * 
	 * @return the date on which the loan is repaid.
	 */
	public ObservableDate getMaturityDate();
	
	/**
	 * Provides a mutable (read-write) handle to a bond.
	 * 
	 * @author fritz@1solms.co.za
	 *
	 */
	public interface Mutable extends MaturityBond
	{
		public void setFaceValue(double newFaceValue);
		
		public void setInterestRate(InterestRate newInterestRate);
		
		public void setEffectiveDate(Date newEffectiveDate);
		
		public void setMaturityDate(Date newMaturityDate);
		
		/**
		 * Provides direct access to the bnd components, thereby breaking
		 * encapsulation and the nature of the composition relationship
		 * 
		 * @author fritz@solms.co.za
		 *
		 */
		public interface Direct extends Mutable
		{
			public InterestRate.Mutable.Direct mutableGetInterestRate();
			public void directSetInterestRate(InterestRate.Mutable.Direct newRate);
      
      public ObservableDate.Mutable mutableGetEffectiveDate();
      public void directSetEffectiveDate(ObservableDate.Mutable date);
      
      public ObservableDate.Mutable mutableGetMaturityDate();
      public void directSetMaturityDate(ObservableDate.Mutable date);
		}
	}
	
	/**
	 * An interface for a MaturityBond factories.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public interface Factory
	{
		public MaturityBond.Mutable.Direct create(double faceValue, 
				InterestRate interestRate, Date effectiveDate, Date maturityDate);
	}
	
	public static final String EFFECTIVE_DATE_PROPERTY = "effectiveDate";
	public static final String MATURITY_DATE_PROPERTY = "maturityDate";
	public static final String INTEREST_RATE_PROPERTY = "interestRate";
	public static final String FACE_VALUE_PROPERTY = "faceValue";
}
