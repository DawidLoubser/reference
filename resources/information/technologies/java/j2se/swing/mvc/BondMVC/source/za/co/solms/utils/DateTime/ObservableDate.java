/**
 * 
 */
package za.co.solms.utils.DateTime;

import java.util.Date;

import za.co.solms.beans.Observable;
import za.co.solms.uml.relationships.Composition;

/**
 * An observable date.
 * 
 * Objects which implement this interface encapsulate a java.util.Date 
 * via composition and provides observability for it.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface ObservableDate extends Observable
{
	@Composition
	public Date getDate();
	
	/**
	 * Represents a mutable handle to a date
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public interface Mutable extends ObservableDate
	{
		@Composition
		public void setDate(Date date);
	}
	
	/**
	 * An interface for factories of observable dates.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	@za.co.solms.lang.Factory
	public interface Factory
	{
		public ObservableDate.Mutable create(Date date);
	}
	
	public static final String DATE_PROPERTY = "date";
}
