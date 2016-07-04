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
import za.co.solms.utils.DateTime.ObservableDate;
import za.co.solms.utils.DateTime.ObservableDateImpl;

/**
 * A simple implementation of a {@link MaturityBond}.
 * 
 * @author fritz@solms.co.za
 *
 */
public class MaturityBondImpl extends ObservableBase
		implements MaturityBond.Mutable.Direct
{
	private MaturityBondImpl(double faceValue, 
			InterestRate interest, Date effectiveDate, Date maturityDate)
	{
		this.faceValue = faceValue;
		this.effectiveDate 
		   = ObservableDateImpl.getDefaultFactory().create(effectiveDate);
		this.maturityDate 
		   = ObservableDateImpl.getDefaultFactory().create(maturityDate);
		this.interestRate = (InterestRate.Mutable.Direct)interest.clone();
    
    addComponentEventPropagators();
	}
  
  private void addComponentEventPropagators()
  {
    interestRate.addObserver(new PropertyChangeListener() 
    {
      public void propertyChange(PropertyChangeEvent ev)
      {
        propertyChangeSupport.firePropertyChange
          (new PropertyModifiedEvent(MaturityBondImpl.this,
                INTEREST_RATE_PROPERTY));
      }
    });
    
    effectiveDate.addObserver(new PropertyChangeListener() 
    {
      public void propertyChange(PropertyChangeEvent ev)
      {
        propertyChangeSupport.firePropertyChange
          (new PropertyModifiedEvent(MaturityBondImpl.this,
                EFFECTIVE_DATE_PROPERTY));
      }
    });

    maturityDate.addObserver(new PropertyChangeListener() 
    {
      public void propertyChange(PropertyChangeEvent ev)
      {
        propertyChangeSupport.firePropertyChange
          (new PropertyModifiedEvent(MaturityBondImpl.this,
                MATURITY_DATE_PROPERTY));
      }
    });
  }

	/* (non-Javadoc)
	 * @see za.co.solms.finance.MaturityBond#getEffectiveDate()
	 */
	public ObservableDate getEffectiveDate()
	{
		return effectiveDate;
	}

	/* (non-Javadoc)
	 * @see za.co.solms.finance.MaturityBond#getFaceValue()
	 */
	public double getFaceValue()
	{
		return faceValue;
	}

	/* (non-Javadoc)
	 * @see za.co.solms.finance.MaturityBond#getInterestRate()
	 */
	public InterestRate getInterestRate()
	{
		return interestRate;
	}

	/* (non-Javadoc)
	 * @see za.co.solms.finance.MaturityBond#getMaturityDate()
	 */
	public ObservableDate getMaturityDate()
	{
		return maturityDate;
	}

	public void setEffectiveDate(Date newEffectiveDate)
	{
		if (!this.effectiveDate.equals(newEffectiveDate))
		{
			ObservableDate oldEffectiveDate = this.effectiveDate;
			this.effectiveDate 
			   = ObservableDateImpl.getDefaultFactory().create(newEffectiveDate);
			propertyChangeSupport.firePropertyChange
				(EFFECTIVE_DATE_PROPERTY, oldEffectiveDate, this.effectiveDate);
		}
	}

	public void setFaceValue(double newFaceValue)
	{
		if (this.faceValue != newFaceValue)
		{
			double oldFaceValue = this.faceValue;
			this.faceValue = newFaceValue;
			propertyChangeSupport.firePropertyChange
				(FACE_VALUE_PROPERTY, oldFaceValue, this.faceValue);
		}
	}

	public void setInterestRate(InterestRate newInterestRate)
	{
		if (!this.interestRate.equals(newInterestRate))
		{
			this.interestRate = (InterestRate.Mutable.Direct)newInterestRate.clone();
			interestRate.addObserver(new PropertyChangeListener()
			{
				public void propertyChange(PropertyChangeEvent evt)
				{
					propertyChangeSupport.firePropertyChange
						(new PropertyModifiedEvent
                  (MaturityBondImpl.this,INTEREST_RATE_PROPERTY));
				}
			});
		}
	}
  
  public void directSetEffectiveDate(ObservableDate.Mutable date)
  {
     if (date != effectiveDate) 
        // on object reference because we are replacing the component
     {
        ObservableDate oldEffectiveDate = effectiveDate;
        this.effectiveDate = date;
        propertyChangeSupport.firePropertyChange
           (EFFECTIVE_DATE_PROPERTY,oldEffectiveDate, this.effectiveDate);
     }
  }

  public void directSetInterestRate
        (za.co.solms.finance.InterestRate.Mutable.Direct newRate)
  {
     if (this.interestRate != newRate)
     {
        InterestRate oldRate = this.interestRate;
        this.interestRate = newRate;
        propertyChangeSupport.firePropertyChange
           (INTEREST_RATE_PROPERTY, oldRate, this.interestRate);
     }
  }

  public void directSetMaturityDate
        (za.co.solms.utils.DateTime.ObservableDate.Mutable date)
  {
     if (date != maturityDate)  
        // on object reference because we are replacing the component
     {
        ObservableDate oldMaturityDate = maturityDate;
        this.maturityDate = date;
        propertyChangeSupport.firePropertyChange
           (MATURITY_DATE_PROPERTY,oldMaturityDate, this.maturityDate);
     }
  }

  public ObservableDate.Mutable mutableGetEffectiveDate()
  {
     return effectiveDate;
  }

  public InterestRate.Mutable.Direct mutableGetInterestRate()
  {
     return interestRate;
  }

  public ObservableDate.Mutable mutableGetMaturityDate()
  {
     return maturityDate;
  }

	public void setMaturityDate(Date newMaturityDate)
	{
		Date oldMaturityDate = this.maturityDate.getDate();
		this.maturityDate 
		   = ObservableDateImpl.getDefaultFactory().create(newMaturityDate);
		propertyChangeSupport.firePropertyChange
			(MATURITY_DATE_PROPERTY, oldMaturityDate, this.maturityDate);
	}

	@Override
	public Set<String> getProperties()
	{
		return properties;
	}
	
	static
	{
		Set<String> props = new TreeSet<String>();
		props.add(FACE_VALUE_PROPERTY);
		props.add(MATURITY_DATE_PROPERTY);
		props.add(EFFECTIVE_DATE_PROPERTY);
		props.add(INTEREST_RATE_PROPERTY);
		
		properties = Collections.unmodifiableSet(props);
	}

	@Override
	protected Object getThisHandle()
	{
		return this;
	}
	
	private static class FactoryImpl implements Factory
	{
		public MaturityBond.Mutable.Direct create
		   (double faceValue, InterestRate interestRate, Date effectiveDate, Date maturityDate)
		{
			return new MaturityBondImpl(faceValue, interestRate, effectiveDate, maturityDate);
		}
	}
	
	public static Factory getDefaultFactory() {return factory;}

	private ObservableDate.Mutable effectiveDate, maturityDate;
	private double faceValue;
	private InterestRate.Mutable.Direct interestRate;
	
	private static final Set<String> properties;
	private static Factory factory = new FactoryImpl();
}
