/**
 * 
 */
package za.co.solms.test.webAdapter.adapter;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import za.co.solms.test.webAdapter.businessLogic.ServiceX;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.DoXAdapterRequest;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.DoXRequest;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.DoXResult;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.ProvideARequest;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.ProvideAResult;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.ServiceXInterface;

/**
 * @author fritz@solms.co.za
 *
 */

@Stateful
public class ServiceXAdapter2 implements ServiceXInterface
{

	public ServiceXAdapter2() {}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProvideARequest doX(DoXRequest request)
	{
		executor.execute(new Runnable(){
				public void run() 
				{
					try
					{
						DoXAdapterRequest adapterRequest = new DoXAdapterRequest(ServiceXAdapter2.this);
						lock.lock();
						doXResult = serviceX.doX(adapterRequest);
						doXResultReceived.signal();
					}
					finally
					{
						lock.unlock();
					}	
				}});

		try 
		{
			lock.lock();
			aRequested.await();
			lock.unlock();
		} 
		catch (InterruptedException e) {}
		
		return new ProvideARequest();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProvideAResult provideA(ProvideARequest request)
	{
		
		lock.lock();
		aRequested.signal();
		try 
		{
			aProvided.await();
		} 
		catch (InterruptedException e) {}
		finally
		{
			lock.unlock();
		}
		
		return new ProvideAResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DoXResult processA(ProvideAResult processARequest)
	{
		lock.lock();
		aProvided.signal();
		try 
		{
			doXResultReceived.await();
		} 
		catch (InterruptedException e) {}
		finally 
		{
			lock.unlock();
		}
		
		return doXResult;
	}
	
	private static final Executor executor = new ThreadPoolExecutor(10, 200, 20, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

	@EJB
	private ServiceX serviceX;
	
	private DoXResult doXResult;
	
	private final ReentrantLock lock = new ReentrantLock();
	
	private final Condition aRequested = lock.newCondition(); 
	private final Condition aProvided = lock.newCondition(); 
	private final Condition doXResultReceived = lock.newCondition(); 

}
