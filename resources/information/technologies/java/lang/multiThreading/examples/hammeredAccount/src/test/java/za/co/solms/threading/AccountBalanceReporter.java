/**
 * 
 */
package za.co.solms.threading;

import java.util.logging.Logger;

import za.co.solms.finance.Account;

/**
 * @author fritz@solms.co.za
 *
 */
public class AccountBalanceReporter implements Runnable
{
	public AccountBalanceReporter(Account account)
	{
		this.account = account;
	}
	
	public void run()
	{
		while (true)
		{
			logger.finest("Querying balance");
			System.out.println("Balance = " + account.getBalance());
			try {Thread.sleep(5000);} catch (InterruptedException e) {}
			
		}
	}
	
	private Account account;
	
	private static Logger logger = Logger.getLogger(AccountBalanceReporter.class.getName());
}
