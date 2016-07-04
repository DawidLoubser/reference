public class Account
{
   public Account(String accountNumber)
   {
     this( accountNumber, 0.0 );
   }
 
   public Account(String accountNumber, double initialBalance)
   {
     ++numInstances;
     accNo = accountNumber;
     balance   = initialBalance;
   }
 
   public void credit(double amount)
   {
     balance += amount;
   }
 
   public void debit(double amount)
   {
     credit(-amount);
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
 
   public void finalize()
   {
     System.out.println(accountNo + " is finalized.");
     --numInstances;
   }

   public static int getNumberOfInstances() 
   {
     return numInstances;
   }
 
   private String accountNo;
   private double balance;

   private static int numInstances = 0;
}
