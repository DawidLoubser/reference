package za.co.solms.threadClasses.basicConsumerProducer;

import java.util.Date;
import java.util.Random;
import java.util.SortedMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * A simple runnable producer of time stamped random data using Lock classes.
 * 
 * @author fritz@solms.co.za
 *
 */
public class Producer implements Runnable 
{
	public Producer(SortedMap<Date, Double> data, Lock resourceLock, Condition hasData, Condition hasSpace)
	{
		this.data = data;
		this.resourceLock = resourceLock;
		this.hasData = hasData;
		this.hasSpace = hasSpace;
	}
	
	public void run()
	{
		System.out.println("Producer " + hashCode() + " is running...");
		while (true)
		{
			Date dateTime = new Date();
			resourceLock.lock();
			try
			{
				while (data.size() >= maxSize)
				{	
					hasSpace.await();
					System.out.println("Producer " + hashCode() + " signalled.");
				}	
				data.put(dateTime, new Double(rng.nextDouble()));
				hasData.signal();
			}
			catch (Exception e) {e.printStackTrace();}
			finally
			{
				resourceLock.unlock();
			}
		}
	}
	
	private Lock resourceLock;
	private Random rng = new Random();
	private Condition hasData, hasSpace;
	private SortedMap<Date, Double> data;
	private static final int maxSize = 100;
}
