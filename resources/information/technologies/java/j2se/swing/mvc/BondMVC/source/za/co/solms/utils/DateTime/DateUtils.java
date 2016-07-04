/**
 * 
 */
package za.co.solms.utils.DateTime;

import java.util.Date;

/**
 * Some simple date utilities.
 * 
 * @author fritz@solms.co.za
 *
 */
public class DateUtils
{
	private DateUtils() {}
	
	public static double getNumYears(Date from, Date to)
	{
		return getNumDays(from,to)/365.25;
	}
	public static double getNumDays(Date from, Date to)
	{
		return (to.getTime() - from.getTime())/24D*60*60*1000;
	}
}
