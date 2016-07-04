import java.util.*;

public class DeadLockResolved
{
  public static void main(String[] args)
  {
    Account acc1 = new Account();
    Account acc2 = new Account();
    
    new Client(acc1,acc2,0).start();
    new Client(acc2,acc1,1).start();
  }
}

class Account
{
  public void credit(double amount) {balance += amount;}
  public void debit (double amount) {balance -= amount;}
  
  private double balance = 0;
}  

class Client extends Thread
{
  public Client(Account source, Account destination, int no)
  {
    resources.put(new Integer(source.hashCode()),source);
    resources.put(new Integer(destination.hashCode()),destination);
    this.source = source;
    this.destination = destination;
    this.no = no;
  }
  
  public void run()
  {
    int i = 0;
    while(true)
    {
      ++i;
      Iterator iter = resources.keySet().iterator();
      synchronized(resources.get(iter.next()))      
      {
        synchronized(resources.get(iter.next()))
        {  
          source.debit(amount);
          System.out.println(i + " #" + no + ": transferring " + amount);
          destination.credit(amount);
        }  
      }
    }      
  }
  
  private Account source, destination;
  private int no;
  private double amount = 100;  
  private TreeMap resources = new TreeMap();
}  
