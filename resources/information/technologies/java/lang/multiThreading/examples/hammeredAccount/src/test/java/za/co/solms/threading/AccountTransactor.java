/**
 * 
 */
package za.co.solms.threading;

import java.util.Random;
import java.util.logging.Logger;

import za.co.solms.finance.Account;

/**
 * @author fritz@solms.co.za
 *
 */
public class AccountTransactor implements Runnable
{
	public AccountTransactor(Account account, Type type)
	{
		this.account = account;
		this.type = type;
	}
	
	public void run()
	{
		while (true)
		{
			double amount = rng.nextDouble()* 1000;
			logger.finest(type.toString() + ": " + amount);
			if (type.equals(Type.debitor))
				account.debit(amount);
			else
				account.credit(amount);
			
			try {Thread.sleep(500);} catch (InterruptedException e) {}
		}
	}
	
	public enum Type {creditor, debitor};
	
	private Account account;
	private Type type;
	
	private static final Random rng = new Random();
	
	private Logger logger = Logger.getLogger(AccountTransactor.class.getName()); 
}
