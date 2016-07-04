public class SavingsAccountImpl implements Account
{
  public TransactionConfirmation credit(double amount)
  {
    // business process for crediting a savings account here
  }  
  
  public TransactionConfirmation debit(double amount)
      throws InsufficientFundsException
  {
    // business process for debiting a savings account here
  }  
      
  public double getBalance() {return balance;}
  
  private double balance;          
}												
