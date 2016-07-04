/**
 * 
 */
package za.co.solms.javaBeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author fritz@solms.co.za
 * 
 */
public class AccountImpl extends ObservableBase
    implements Account, Serializable
{
  public AccountImpl(Person client)
  {
    ++numAccounts;
    accNo = numAccounts;
    this.client = client;
  }

  public Tender getBalance()
  {
    return balance;
  }

  public int getAccNo()
  {
    return accNo;
  }

  public Person getClient()
  {
    return client;
  }

  public void credit(Tender amount)
      throws Tender.UnsupportedCurrencyException
  {
    balance.add(amount);
    propertyChangeSupport.firePropertyChange(
        BALANCE_PROPERTY, null, null);
  }

  public void debit(Tender amount)
      throws Tender.UnsupportedCurrencyException
  {
    balance.subtract(amount);
    propertyChangeSupport.firePropertyChange(
        BALANCE_PROPERTY, null, null);
  }

  protected Object getThisHandle()
  {
    return this;
  }

  public Set<String> getProperties()
  {
    return properties;
  }

  protected static final Set<String> properties;

  private TenderImpl balance;

  private int accNo;

  private Person client;

  private static int numAccounts = 0;

  public static final String BALANCE_PROPERTY = "balance";

  public static final String ACC_NO_PROPERTY = "accNo";

  static
  {
    Set<String> props = new TreeSet<String>();
    props.add(BALANCE_PROPERTY);
    props.add(ACC_NO_PROPERTY);
    properties = Collections.unmodifiableSet(props);
  }

  private static final long serialVersionUID = 200605051303L;
}
