public class AccountWithBalanceLimitTest
{
  public static void main(String[] args)
  {
    AccountWithBalanceLimit accountWithLimit
      = new AccountWithBalanceLimit("A1", 500, 100);

    Account accountWithoutLimit = new Account("A2", 1000);

    double amount = 300;
    try
    {
      accountWithLimit.debit(amount);
      System.out.println("debited by " + amount + ": " + accountWithLimit);
    }
    catch (IllegalArgumentException e)
    {
      e.printStackTrace();
    }

    System.out.println("debiting " + accountWithoutLimit + " by " + amount);
    accountWithoutLimit.debit(amount);
    System.out.println("now: " + accountWithoutLimit);

    amount = 200;
    
    try
    {
      accountWithLimit.debit(amount);
      System.out.println("debited by " + amount + ": " + accountWithLimit);
    }
    catch (IllegalArgumentException e)
    {
      e.printStackTrace();
    }
    
    System.out.println("debiting " + accountWithoutLimit + " by " + amount);
    accountWithoutLimit.debit(amount);
    System.out.println("now: " + accountWithoutLimit);
  }
}
