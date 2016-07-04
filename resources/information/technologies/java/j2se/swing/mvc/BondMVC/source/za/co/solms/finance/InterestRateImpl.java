/**
 * 
 */
package za.co.solms.finance;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import za.co.solms.beans.ObservableBase;
import za.co.solms.beans.PropertyModifiedEvent;
import za.co.solms.uml.relationships.Composition;
import za.co.solms.utils.primitives.ObservableDouble;
import za.co.solms.utils.primitives.ObservableDoubleImpl;

/**
 * A simple implementation of an interest rate.
 * 
 * @author fritz@solms.co.za
 *
 */
public class InterestRateImpl extends ObservableBase implements InterestRate.Mutable.Direct
{
	private InterestRateImpl(double annualizedRate, Compounding compounding)
	{
		this.rate = ObservableDoubleImpl.getDefaultFactory().create(annualizedRate);
		this.compoundingType = CompoundingTypeImpl.getDefaultFactory().create(compounding);
    
    addComponentEventPropagators();
	}
  
  private void addComponentEventPropagators()
  {
     rate.addObserver(new PropertyChangeListener()
     {
        public void propertyChange(PropertyChangeEvent evt)
        {
           propertyChangeSupport.firePropertyChange
           (new PropertyModifiedEvent(InterestRateImpl.this,
                 RATE_PROPERTY));
        }
     });
     
     compoundingType.addObserver(new PropertyChangeListener()
     {
        public void propertyChange(PropertyChangeEvent evt)
        {
           propertyChangeSupport.firePropertyChange
           (new PropertyModifiedEvent(InterestRateImpl.this,
                 COMPOUNDING_PROPERTY));
        }
     }); 
  }

	public double discount(double amount, Date payDate, Date valuationDate)
	{
		return amount * compoundingType.getCompounding().getDiscountFactor(rate.getValue(), payDate, valuationDate);
	}

	@Composition
	public Compounding getCompounding()
	{
		return compoundingType.getCompounding();
	}

	@Composition
	public ObservableDouble getRate()
	{
		return rate;
	}

	public void setCompounding(Compounding newCompounding)
	{
		if (!newCompounding.equals(this.compoundingType))
		{
			Compounding orgCompounding = this.compoundingType.getCompounding();
			this.compoundingType = CompoundingTypeImpl.getDefaultFactory().create(newCompounding);
			propertyChangeSupport.firePropertyChange
				(COMPOUNDING_PROPERTY, orgCompounding, this.compoundingType);
		}
	}

	public void setRate(double newRate)
	{
		if (newRate != this.rate.getValue())
		{
			double oldRate = this.rate.getValue();
			this.rate.setValue(newRate);
			propertyChangeSupport.firePropertyChange
				(RATE_PROPERTY, oldRate, this.rate);
		}
	}

	@Override
	public Set<String> getProperties()
	{
		return properties;
	}
	
	static
	{
		Set<String> props = new TreeSet<String>();
		props.add(RATE_PROPERTY);
		props.add(COMPOUNDING_PROPERTY);
		properties = Collections.unmodifiableSet(props);
	}

	@Override
	protected Object getThisHandle()
	{
		return this;
	}
	
	public int hashCode()
	{
		return rate.hashCode() + compoundingType.hashCode();
	}
	
	public boolean equals(Object arg)
	{
		try
		{
			InterestRate argRate = (InterestRate)arg;
			return ((this.rate == argRate.getRate()) && 
					(this.compoundingType.equals(argRate.getCompounding())));
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}

  public void directSetRate(za.co.solms.utils.primitives.ObservableDouble.Mutable rate)
  {
     if (this.rate != rate)
     {
        ObservableDouble.Mutable oldRate = this.rate;
        this.rate = rate;
        propertyChangeSupport.firePropertyChange(RATE_PROPERTY, oldRate, this.rate);
     }
  }

  public za.co.solms.utils.primitives.ObservableDouble.Mutable mutableGetRate()
  {
     return rate;
  }
	
	public Object clone()
	{
     InterestRate.Mutable.Direct copy = null;
     
     copy = (InterestRate.Mutable.Direct)super.clone();
     copy.directSetRate((ObservableDouble.Mutable)rate.clone());

		return copy; 
	}
	
	public static Factory getFactory() {return factory;}
	
	private static class FactoryImpl implements Factory
	{
		public Mutable create(double annualizedRate, Compounding compounding)
		{
			return new InterestRateImpl(annualizedRate, compounding);
		}
	}

  public void directSetCompoundingType
     (za.co.solms.finance.CompoundingType.Mutable compoundingTpe)
  {
     if (this.compoundingType != compoundingTpe)
     {
        Compounding oldCompounding = this.compoundingType.getCompounding();
        this.compoundingType = compoundingTpe;
        propertyChangeSupport.firePropertyChange
           (COMPOUNDING_PROPERTY, oldCompounding, this.compoundingType.getCompounding());
     }
  }

  public za.co.solms.finance.CompoundingType.Mutable mutableGetCompoundingType()
  {
     return compoundingType;
  }
	

	private CompoundingType.Mutable compoundingType;
	private ObservableDouble.Mutable rate;
	
	private static final Set<String> properties;
	
	private static Factory factory = new FactoryImpl();
}
