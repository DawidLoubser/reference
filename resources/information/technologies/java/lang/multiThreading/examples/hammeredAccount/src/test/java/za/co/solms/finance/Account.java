/**
 * 
 */
package za.co.solms.finance;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fritz@solms.co.za
 *
 */
public class Account
{
	public Account() 
	{

		class Moaner implements Runnable
		{
			public void run()
			{
				while (true)
				{
					balanceLock.lock();
					try
					{
						overdrawn.await();
						System.out.println("Moan moan moan ... balance = " + balance);
					}
					catch (InterruptedException e) {}					
					finally
					{
						balanceLock.unlock();
					}
				}
				
			}
		}
		new Thread(new Moaner()).start();
	}
	
	public void credit(double amount)
	{
		balanceLock.lock();
		try
		{
			this.balance += amount;
		}
		finally
		{
			balanceLock.unlock();
		}	
	}
	
	public void debit(double amount)
	{
		balanceLock.lock();
		try
		{
			this.balance -= amount;
			if (balance < 0)
				overdrawn.signal();
		}
		finally
		{
			balanceLock.unlock();
		}	
	}
	
	public double getBalance() {return balance;}
	
	private double balance;
	
	private ReentrantLock balanceLock = new ReentrantLock();
	private Condition overdrawn = balanceLock.newCondition();
}
