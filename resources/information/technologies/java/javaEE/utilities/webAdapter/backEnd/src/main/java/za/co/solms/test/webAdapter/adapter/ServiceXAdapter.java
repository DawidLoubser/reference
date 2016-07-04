
/**
 * 
 */
package za.co.solms.test.webAdapter.adapter;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.google.common.util.concurrent.ValueFuture;

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
public class ServiceXAdapter implements ServiceXInterface
{
	public ServiceXAdapter() {}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProvideARequest doX(DoXRequest request)
	{
		DoXAdapterRequest adapterRequest = new DoXAdapterRequest(ServiceXAdapter.this);
		doXResultFuture = serviceXAsync.doXAsync(adapterRequest);
		
		try
		{
			return aRequested.get();
		}
		catch (Exception e)
		{
			logger.severe("FAILED");
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DoXResult processA(ProvideAResult provideAResult)
	{
		aProvided.set(provideAResult);
		
		try
		{
			DoXResult doXResult = doXResultFuture.get();
			
			return doXResult;
		}
		catch (Exception e)
		{
			logger.severe("process execution failed");
			doXResultFuture.cancel(true);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see za.co.solms.test.webAdapter.businessLogic.ServiceX.ServiceXInterface#provideA(za.co.solms.test.webAdapter.businessLogic.ServiceX.ProvideARequest)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProvideAResult provideA(ProvideARequest request)
	{
		aRequested.set(request);
		try
		{
			return aProvided.get();
		}
		catch (Exception e)
		{
			logger.severe("process execution failed");
			doXResultFuture.cancel(true);
			return null;
		}
	}
	
	@EJB
	private ServiceXAsync serviceXAsync;

	private ValueFuture<ProvideARequest> aRequested = ValueFuture.create();
	private ValueFuture<ProvideAResult> aProvided = ValueFuture.create();
	
	private Future<DoXResult> doXResultFuture;
	
	private static Logger logger = Logger.getLogger(ServiceXAdapter.class.getName());
}
