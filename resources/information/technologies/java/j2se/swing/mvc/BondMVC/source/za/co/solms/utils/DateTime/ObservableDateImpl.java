/**
 * 
 */
package za.co.solms.utils.DateTime;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import za.co.solms.beans.ObservableBase;

/**
 * @author fritz@solms.co.za
 *
 */
public class ObservableDateImpl extends ObservableBase
		implements ObservableDate.Mutable
{
	private ObservableDateImpl(Date date)
	{
		this.date = (Date)date.clone();
	}

	/* (non-Javadoc)
	 * @see za.co.solms.beans.ObservableBase#getProperties()
	 */
	@Override
	public Set<String> getProperties()
	{
		return properties;
	}

	/* (non-Javadoc)
	 * @see za.co.solms.beans.ObservableBase#getThisHandle()
	 */
	@Override
	protected Object getThisHandle()
	{
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.solms.utils.DateTime.ObservableDate#getDate()
	 */
	public Date getDate()
	{
		return (Date)date.clone();
	}
	
	public void setDate(Date newDate)
	{
		if (!(this.date.getTime() == newDate.getTime()))
		{
			Date oldDate = this.date;
			this.date.setTime(newDate.getTime());
			propertyChangeSupport.firePropertyChange(DATE_PROPERTY, oldDate, newDate);
		}
	}
	
	static
	{
		Set<String> props = new TreeSet<String>();
		props.add(DATE_PROPERTY);
		properties = Collections.unmodifiableSet(props);
	}
	
	public static Factory getDefaultFactory()
	{
		return factory;
	}
	
	private static class FactoryImpl implements Factory
	{
		public ObservableDate.Mutable create(Date date)
		{
			return new ObservableDateImpl(date);
		}
	}
	
	private Date date;
	private final static Set<String> properties;
	private static final Factory factory = new FactoryImpl();
}
