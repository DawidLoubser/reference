package za.co.solms.threadClasses.basicConsumerProducer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * A simple runnable consumer of time stamped data using Lock classes.
 * 
 * @author fritz@solms.co.za
 *
 */
public class Consumer implements Runnable
{
	public Consumer (SortedMap<Date, Double> data, Lock resourceLock, Condition hasData, Condition hasSpace)
	{
		this.data = data;
		this.resourceLock = resourceLock;
		this.hasData = hasData;
		this.hasSpace = hasSpace;
	}
	
	public void run()
	{
		System.out.println("Consumer " + hashCode() + " is running...");
		while (true)
		{
			try
			{
				resourceLock.lock();
				
				while (data.isEmpty())
				{
					hasData.await();
					System.out.println("Consumer " + hashCode() + " signalled.");
				}
				Date dateTime = data.firstKey();
				Double dataValue = data.remove(dateTime);
				hasSpace.signal();
				System.out.println(hashCode() + ": got data " + dataValue + " at " + timeFormatter.format(dateTime));
				
			}
			catch (Exception e) {e.printStackTrace();}
			finally
			{
				resourceLock.unlock();
			}
		}
	}
	
	private Lock resourceLock;
	private SortedMap<Date,Double> data;
	private Condition hasData, hasSpace;
	private static final DateFormat timeFormatter = new SimpleDateFormat("HH:mm:SS:SSS"); 
}
