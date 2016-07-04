package za.co.solms.javaBeans;

/**
 * A simple interface for accounts.
 * 
 * @author fritz@solms.co.za
 * 
 */
public interface Account extends Observable
{
  /**
   * 
   * @return the balance of the account
   */
  public Tender getBalance();

  /**
   * 
   * @return the account number of the acount
   */
  public int getAccNo();

  /**
   * 
   * @return the client for this account.
   */
  public Person getClient();

  /**
   * Credits the account with the provided tender if the currency is supported
   * by that account and throws an UnsupportedCurrencyException otherwise.
   * 
   * @param amount
   *          the tender with which the amount is credited.
   */
  public void credit(Tender amount)
      throws Tender.UnsupportedCurrencyException;

  /**
   * Debits the account with the provided tender if the currency is supported by
   * that account and throws an UnsupportedCurrencyException otherwise.
   * 
   * @param amount
   *          the tender with which the amount is debited.
   */
  public void debit(Tender amount)
      throws Tender.UnsupportedCurrencyException,
      InsufficientFundsException;

  public class InsufficientFundsException extends
      Exception
  {
    private static final long serialVersionUID = 200605051525L;
  }
}
