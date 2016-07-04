public class DeadLock
{
  public static void main(String[] args)
  {
    Account acc1 = new Account();
    Account acc2 = new Account();
    
    new TransferFunds(acc1, acc2, 0, 100.0).start();
    new TransferFunds(acc2, acc1, 1, 100.0).start();
  }
}

class Account
{
  public void credit(double amount) {balance += amount;}
  public void debit (double amount) {balance -= amount;}
  
  private double balance = 0;
}  


class TransferFunds extends Thread
{
  public TransferFunds(Account source, Account destination, int no, double amount)
  {
    this.source = source;
    this.destination = destination;
    this.no = no;
    this.amount = amount;
  }
  
  public void run()
  {
    int i = 0;
    while(true)
    {
      ++i;
      synchronized(source)      
      {
        source.debit(amount);
        System.out.println(i + " #" + no + ": transferring " + amount);
        synchronized(destination) 
          {destination.credit(amount);}
      }
    }      
  }
  
  private Account source, destination;
  private int no;
  private double amount;  
}  