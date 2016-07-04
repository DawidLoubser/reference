/**
 * 
 */
package za.co.solms.test.webAdapter.businessLogic;

import javax.ejb.Stateless;

/**
 * @author fritz@solms.co.za
 *
 */
@Stateless
public class ServiceXBean implements ServiceX
{

	@Override
	public DoXResult doX(DoXAdapterRequest request)
	{
		ProvideAResult provideAResult = request.getInterface().provideA(new ProvideARequest());
			
		return new DoXResult();
	}
}
