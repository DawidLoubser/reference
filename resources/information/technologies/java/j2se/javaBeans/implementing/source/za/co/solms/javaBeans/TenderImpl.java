package za.co.solms.javaBeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * A simple implementation of an immutable tender.
 * 
 * @author fritz@solms.co.za
 * 
 */
public class TenderImpl extends ObservableBase
    implements Tender.Mutable, Serializable, Cloneable
{
  /**
   * Creates an instance of a tender
   * 
   * @param amount
   *          the amount of the tender
   * @param currency
   *          the currency in which the tender was made.
   */
  public TenderImpl(double amount, String currency)
  {
    this.amount = amount;
    this.currency = currency;
  }

  public double getAmount()
  {
    return amount;
  }

  public String getCurrency()
  {
    return currency;
  }

  public void setAmount(double amount)
  {
    double oldAmount = this.amount;
    this.amount = amount;
    propertyChangeSupport.firePropertyChange(
        AMOUNT_PROPERTY, oldAmount, amount);
  }

  public void setCurrency(String currency)
  {
    String oldCurrency = this.currency;
    this.currency = currency;
    propertyChangeSupport.firePropertyChange(
        CURRENCY_PROPERTY, oldCurrency, currency);
  }

  public void add(Tender amount)
      throws UnsupportedCurrencyException
  {
    if (!amount.getCurrency().equals(currency))
      throw new UnsupportedCurrencyException();

    this.setAmount(this.amount + amount.getAmount());
  }

  public void subtract(Tender amount)
      throws UnsupportedCurrencyException
  {
    if (!amount.getCurrency().equals(currency))
      throw new UnsupportedCurrencyException();

    this.setAmount(this.amount - amount.getAmount());
  }

  public String toString()
  {
    return Double.toString(amount) + " " + currency;
  }

  public boolean equals(Object o)
  {
    try
    {
      TenderImpl arg = (TenderImpl) o;

      return ((arg.amount == amount) && (arg.currency
          .equals(currency)));
    } catch (ClassCastException e)
    {
      return false;
    }
  }

  public int hashCode()
  {
    return new Double(amount).hashCode()
        + currency.hashCode();
  }

  @Override
  protected Object getThisHandle()
  {
    return this;
  }

  @Override
  public Set<String> getProperties()
  {
    return properties;
  }

  private double amount;

  private String currency;

  private static final Set<String> properties;

  public static final String AMOUNT_PROPERTY = "amount";

  public static final String CURRENCY_PROPERTY = "currency";

  static
  {
    Set<String> props = new TreeSet<String>();
    props.add(AMOUNT_PROPERTY);
    props.add(CURRENCY_PROPERTY);
    properties = Collections.unmodifiableSet(props);
  }

  private static final long serialVersionUID = 200605051119L;
}
