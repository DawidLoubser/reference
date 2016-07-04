public class Account1
{
   public Account1(String accountNumber)
   {
     this( accountNumber, 0.0 );
   }
 
   public Account1(String accountNumber, double initialBalance)
   {
     accountNo = accountNumber;
     balance   = initialBalance;
   }
 
   public void credit(double amount)
   {
     balance += amount;
   }
 
   public void debit(double amount)
   {
     credit(-amount);   
     // equivalent to this.credit(-amount);
   }
 
   public double getBalance()
   {
     return balance;
   }
 
   public String getAccountNumber()
   {
    return accountNo;
   }
 
   public String toString()
   {
     return accountNo + ": " + balance;
   }
 
   private String accountNo;
   private double balance;
}
