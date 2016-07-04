 public class Account1
 {
   public Account1(String accountNumber)
   {
     accountNo = accountNumber;
   }
 
   public Account1(String accountNumber, double initialBalance)
   {
     this(accountNumber);
     balance = initialBalance;
   }
 
   public Account1(String accountNumber, double initialBalance,
                                 double minimumBalance)
   {
     this(accountNumber, initialBalance);
     this.minimumBalance = minimumBalance;
   }
 
   public void credit(double amount) throws IllegalArgumentException
   {
     if (amount < 0)
       throw new IllegalArgumentException("credit amount may not be negative");
     balance += amount;
   }
 
   public void debit(double amount)  throws IllegalArgumentException
   {
     if (amount > 0)
       throw new IllegalArgumentException("debit amount may not be negative");
 
     if (balance - amount > minimumBalance)
       throw new IllegalArgumentException("Available funds = " 
         + (balance - minimumBalance));
 
     balance -= amount;
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
   private double balance=0, minimumBalance=0;
 }
