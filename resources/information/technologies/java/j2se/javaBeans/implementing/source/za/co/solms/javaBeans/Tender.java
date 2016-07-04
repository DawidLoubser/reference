package za.co.solms.javaBeans;

/**
 * A tender is an amount in a currency. 
 * The Tender interface provides a read only view for tenders.
 * 
 * Tenders are observable.
 * 
 * The static nested Mutable subinterface provides write access 
 * to tenders.
 * 
 * @author fritz@solms.co.za
 * 
 */
public interface Tender extends Observable
{
  /**
   * 
   * @return the amount of the tender.
   */
  public double getAmount();

  /**
   * 
   * @return the currency of the tender.
   */
  public String getCurrency();

  /**
   * An interface providing write access to tenders.
   * 
   * @author fritz@solms.co.za
   * 
   */
  public interface Mutable extends Tender
  {
    /**
     * Modifies the amount of this tender.
     */
    public void setAmount(double amount);

    /**
     * Modifies the currency of this tender.
     */
    public void setCurrency(String currency);

    /**
     * Adds the supplied tender to this tender.
     * 
     * @param amount
     *          the amount to add to this tender.
     * 
     * @throws UnsupportedCurrencyException
     *           if the supplied tender has a different currency 
     *           to this tender.
     */
    public void add(Tender amount)
        throws UnsupportedCurrencyException;

    /**
     * Subttracts the supplied tender from this tender.
     * 
     * @param amount
     *          the amount to sunbtract from this tender.
     * 
     * @throws UnsupportedCurrencyException
     *           if the supplied tender has a different currency 
     *           to this tender.
     */
    public void subtract(Tender amount)
        throws UnsupportedCurrencyException;
  }

  /**
   * Thrown if the used currency is not supported by 
   * a particular operation.
   * 
   * @author fritz@solms.co.za
   * 
   */
  public class UnsupportedCurrencyException extends
      Exception
  {
    private static final long serialVersionUID = 200605051237L;
  };

}
