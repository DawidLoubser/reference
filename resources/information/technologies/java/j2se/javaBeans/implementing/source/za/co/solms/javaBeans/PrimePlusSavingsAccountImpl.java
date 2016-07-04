package za.co.solms.javaBeans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author fritz@solms.co.za
 * 
 */
public class PrimePlusSavingsAccountImpl extends
    AccountImpl implements Observable,
    PrimePlusSavingsAccount
{
  public PrimePlusSavingsAccountImpl(Person client,
      InterestRate interestRate)
  {
    super(client);

    this.primeRate = interestRate;

    // We have to issue state change events when the 
    // aggregated object's state changes:
    interestRate
        .addObserver(new PropertyChangeListener()
        {

          public void propertyChange(
              PropertyChangeEvent event)
          {
            PrimePlusSavingsAccountImpl.this.propertyChangeSupport
                .firePropertyChange(
                    PRIME_RATE_PROPERTY, null, null);
          }

        });
  }

  public InterestRate getPrimeRate()
  {
    return primeRate;
  }

  public void setPrimeRate(InterestRate newPrimeRate)
  {
    this.primeRate = newPrimeRate;
  }

  public double getSpreadAbovePrime()
  {
    return spreadAbovePrime;
  }

  public void setSpreadAbovePrime(double newSpread)
  {
    double oldSpread = spreadAbovePrime;
    this.spreadAbovePrime = newSpread;
    propertyChangeSupport.firePropertyChange(
        SPREAD_ABOVE_PRIME_PROPERTY, oldSpread,
        spreadAbovePrime);
  }

  protected Object getThisHandle()
  {
    return this;
  }

  private InterestRate primeRate;

  private double spreadAbovePrime;

  public Set<String> getProperties()
  {
    return properties;
  }

  public static final String PRIME_RATE_PROPERTY = "primeRate";

  public static final String SPREAD_ABOVE_PRIME_PROPERTY 
          = "spreadAbovePrime";

  protected static final Set<String> properties;

  static
  {
    // Here we need to add some properties to those of the superclass.

    Set<String> props = new TreeSet<String>(
        AccountImpl.properties);
    props.add(PRIME_RATE_PROPERTY);
    props.add(SPREAD_ABOVE_PRIME_PROPERTY);
    properties = Collections.unmodifiableSet(props);
  }

  private static final long serialVersionUID = 200505051329L;
}
