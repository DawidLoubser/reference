/**
 * 
 */
package za.co.solms.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import za.co.solms.finance.Account;

/**
 * @author fritz@solms.co.za
 *
 */
public class TestApp
{

	public static void main(String[] args)
	{
		new TestApp().run();
	}

	public void run()
	{
		Account acc = new Account();
		ExecutorService executor = new ThreadPoolExecutor(20,50,1,TimeUnit.MINUTES,
				new LinkedBlockingQueue<Runnable>());
		for (int i=0; i<5; ++i)
		{
			executor.execute(new AccountTransactor(acc, AccountTransactor.Type.creditor));
			executor.execute(new AccountTransactor(acc, AccountTransactor.Type.debitor));
		}
		executor.execute(new AccountBalanceReporter(acc));
		
		class BatchResultReceiver extends Thread
		{
			public BatchResultReceiver(Future<Double> resultFuture)
			{
				this.resultFuture = resultFuture;
			}
			
			public void run()
			{
				try
				{
					System.out.println("Blance after batch credit: " + resultFuture.get());
				}
				catch (Exception e)
				{
					System.out.println("Batch not processed successfully: " + e);
				}
			}
			
			private Future<Double> resultFuture;
		}
		
		new BatchResultReceiver(executor.submit(new BatchCreditor(acc))).start();
	}
}
