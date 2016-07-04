import java.io.*;
import java.util.Date;

/** A test program to illustrate monitors and mutexes */
public class AccountUsers
{
  public static void main(String[] args)  throws IOException
  {
    out = new PrintWriter(new FileOutputStream("AccountUsers.out"));
    account = new Account(out);
    
    System.out.println("Initial account Balance = " 
                       + account.getBalance());
    
    start = new Date();
    for (int i=0; i<numUsers; ++i)
    {
      new Thread(new AccountUser(account)).start();
      ++existingUsers;
    }  
  }
  
  public static void completed()
  {
    -- existingUsers;
    if (existingUsers == 0)
    {
      System.out.println("\nFinal account Balance = " 
                         + account.getBalance());
      Date end = new Date();
      double timeTaken 
        = ((double)(end.getTime() - start.getTime()))/1000;
      System.out.println("\nTime taken = " + timeTaken);
    }  
  }    
    
  private static final int numUsers = 100;  
  private static int existingUsers = 0;
  private static PrintWriter out;
  private static Account account;
  private static Date start;
}      

/** Represents an Account (a shared resource) */
class Account
{
  public Account(PrintWriter out) {this.out = out;}
  
  public synchronized void credit(double amount) 
  {
    double balance = getBalance();
    out.print("current balance = " + balance);
    balance += amount;
    out.println(", crediting with" + amount);
    setBalance(balance);
  }   
  
  public synchronized void debit(double amount) 
  {
    double balance = getBalance();
    out.print("current balance = " + balance);
    balance -= amount;
    out.println(", debiting with" + amount);
    setBalance(balance);
  }  
  
  public double getBalance() {return balance;}
  private void  setBalance(double balance) {this.balance = balance;}
  
  private double balance = 0;
  private PrintWriter out;
}

/** Represents a user of an account */
class AccountUser implements Runnable
{
  public AccountUser(Account account) {this.account = account;}
  
  public void run()
  {
    for (int i=0; i<1000; ++i) 
    {
      account.credit(100);  Thread.yield();
      account.debit (100);  Thread.yield();
    }  
    AccountUsers.completed();  
  }
  private Account account;
}  

  
