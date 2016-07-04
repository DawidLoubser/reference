public interface Account
{
  ...
  
  public TransactionConfirmation debit(double amount)
            throws InsufficientFunds;
  ...
            
  public class InsufficientFunds extends Exception {...}
}
