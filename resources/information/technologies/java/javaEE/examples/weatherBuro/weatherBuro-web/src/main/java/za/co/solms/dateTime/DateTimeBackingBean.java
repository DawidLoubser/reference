/**
 * 
 */
package za.co.solms.dateTime;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean 
@RequestScoped
public class DateTimeBackingBean implements Serializable
{
	public DateTimeBackingBean(){}
	
	public Date getDateTime()
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		
		return cal.getTime();
	}
	
	public void setDateTime(Date dateTime)
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dateTime);
		hour=cal.get(Calendar.HOUR);
		minute = cal.get(Calendar.MINUTE);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();
	}
	
	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		this.minute = minute;
	}

	public int getHour()
	{
		return hour;
	}

	public void setHour(int hour)
	{
		this.hour = hour;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	private int minute, hour;
	private Date date = new Date();
	
	private static final long serialVersionUID = 1L;
}
