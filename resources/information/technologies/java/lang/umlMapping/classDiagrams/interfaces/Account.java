public interface Account
{
  public TransactionConfirmation credit(double amount);
  
  public TransactionConfirmation debit(double amount)
      throws InsufficientFundsException;
      
  public double getBalance();    
}												
