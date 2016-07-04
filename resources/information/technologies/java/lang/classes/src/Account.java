/** A basic account class */
public class Account
{
  /** A Constructor to create an instance with the
      specified account number */
  public Account(String accountNumber)
  {
    accountNo = accountNumber;
    balance   = 0;
  }

  /** A Constructor to create an instance with the
      specified account number and initial balance */
  public Account(String accountNumber, double initialBalance)
  {
    accountNo = accountNumber;
    balance   = initialBalance;
  }

  /** A service to credit the account by the
      specified amount */
  public void credit(double amount)
  {
    balance += amount;
  }

  /** A service to debit the account by the
      specified amount */
  public void debit(double amount)
  {
    balance -= amount;
  }

  /** A getter service to query the current balance */
  public double getBalance()
  {
    return balance;
  }

  /** A getter service to query the account number */
  public String getAccountNumber()
  {
    return accountNo;
  }

  /** A service to ask an account to represent itself
      as a string (convenient for displaying, etc) */
  public String toString()
  {
    return accountNo + ": " + balance;
  }
  
  // The attributes (state) of an account
  private String accountNo;
  private double balance;
}       
 