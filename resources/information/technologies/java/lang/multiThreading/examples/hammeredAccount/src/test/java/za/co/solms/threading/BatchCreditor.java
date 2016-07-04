/**
 * 
 */
package za.co.solms.threading;

import java.util.Random;
import java.util.concurrent.Callable;

import za.co.solms.finance.Account;

/**
 * @author fritz@solms.co.za
 *
 */
public class BatchCreditor implements Callable<Double>
{
	public BatchCreditor(Account account)
	{
		this.account = account;
	}
	
	public Double call() throws Exception
	{
		account.credit(rng.nextDouble()*100);
		return account.getBalance();
	}
	
	
	private Account account;
	private static final Random rng = new Random();
}
