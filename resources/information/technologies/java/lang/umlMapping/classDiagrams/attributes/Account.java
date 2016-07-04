public class Account
{
   ...
   
   public double getAvailableFunds() {return balance - minimumBalance;}
   
   public boolean isOverdrawn() {return balance < minimumBalance;}
  
   ... 
   private double balance, minimumBalance;
 }  