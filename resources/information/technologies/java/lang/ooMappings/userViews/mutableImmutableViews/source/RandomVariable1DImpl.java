package za.co.solms.math.statistics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import za.co.solms.beans.ObservableBase;
import za.co.solms.beans.PropertyModifiedEvent;A


/**
 * A simple implementation of a 1-dimensional random variable.
 */
public class RandomVariable1DImpl extends ObservableBase
implements RandomVariable1D.Mutable.Direct
{

    public RandomVariable1DImpl (ProbabilityDistribution1D pD)
    {
        super();
        setProbabilityDistribution( pD );
    }

    public ProbabilityDistribution1D getProbabilityDistribution()
    {
        return probabilityDistribution;
    }

    public double getValue()
    {
        return probabilityDistribution.getSample();
    }

    public ProbabilityDistribution1D.Mutable 
        mutableGetProbabilityDistribution()
    {
        return probabilityDistribution;
    }

    public void directSetProbabilityDistribution
          (ProbabilityDistribution1D.Mutable probabilityDistribution)
    {
        ProbabilityDistribution1D orgP = this.probabilityDistribution;
        this.probabilityDistribution = probabilityDistribution;
        
        // Notify our observers of state change
        propertyChangeSupport.firePropertyChange(
            PROBABILITY_DISTRIBUTION_PROPERTY,
            orgP, this.probabilityDistribution);
        
        // Observe our component (to propogate state changes)
        probabilityDistribution.addObserver(
            new PropertyChangeListener()
                {
                    public void propertyChange(PropertyChangeEvent evt)
                    {
                        propertyChangeSupport.firePropertyChange
                        (new PropertyModifiedEvent(
                            RandomVariable1DImpl.this,
                            PROBABILITY_DISTRIBUTION_PROPERTY));
                    }
            });
    }

    public Object clone()
    {
      RandomVariable1DImpl clone 
        = (RandomVariable1DImpl)super.clone();
      clone.probabilityDistribution 
        = (ProbabilityDistribution1D.Mutable)probabilityDistribution.clone();
      return clone;
    }
    
    public void setProbabilityDistribution(ProbabilityDistribution1D p)
    {
      directSetProbabilityDistribution
        ((ProbabilityDistribution1D.Mutable)p.clone());
    }


    // Support general observability support 
    // (through the 'ObservableBase' class)
    protected static final Set<String> properties;
    protected static Set<String> createProperties()
    {
      Set<String> props = new TreeSet<String>();
      props.add(PROBABILITY_DISTRIBUTION_PROPERTY);
      return props;
    }
    static
    {
      properties = Collections.unmodifiableSet(createProperties());
    }
    public Set<String> getProperties()
    {
      return properties;
    }
    protected Object getThisHandle() 
    {
        return this;
    }


    // Our state
    private ProbabilityDistribution1D.Mutable probabilityDistribution;
}