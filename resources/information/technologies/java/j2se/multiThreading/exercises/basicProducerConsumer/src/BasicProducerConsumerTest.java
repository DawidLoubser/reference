package za.co.solms.threadClasses.basicConsumerProducer;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fritz@solms.co.za
 *
 */
public class BasicProducerConsumerTest 
{

	public static void main(String[] args) 
	{
		SortedMap<Date,Double> data = new TreeMap<Date,Double>();
		Lock resourceLock = new ReentrantLock();
		Condition hasData = resourceLock.newCondition();
		Condition hasSpace = resourceLock.newCondition();
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for (int i=0; i<numProducers; ++i)
			executorService.submit(new Producer(data, resourceLock, hasData, hasSpace));
		
		for (int i=0; i<numConsumers; ++i)
			executorService.submit(new Consumer(data, resourceLock, hasData, hasSpace));
		
		System.out.println("Main ending ...");
	}

	private final static int numProducers = 100;
	private final static int numConsumers = 10;
}
