/**
 * 
 */
package za.co.solms.test.webAdapter.adapter;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import za.co.solms.test.webAdapter.businessLogic.ServiceX;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.DoXAdapterRequest;
import za.co.solms.test.webAdapter.businessLogic.ServiceX.DoXResult;

/**
 * @author fritz@solms.co.za
 *
 */
@Asynchronous
@Stateless
public class ServiceXAsync
{
	public ServiceXAsync() {}
	
	@Asynchronous
	public Future<DoXResult> doXAsync(DoXAdapterRequest request)
	{
		DoXResult result = serviceX.doX(request);
		return new AsyncResult(result);
	}

	@EJB
	private ServiceX serviceX;
}
