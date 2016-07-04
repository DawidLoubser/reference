/**
 * 
 */
package za.co.solms.test.webAdapter.businessLogic;

import java.io.Serializable;

/**
 * @author fritz@solms.co.za
 *
 */
public interface ServiceX
{
	public DoXResult doX(DoXAdapterRequest request);
	
	public interface ServiceXInterface extends ServiceXInterfaceBusinessLogic,
					ServiceXInterfaceUserInterface
	{
	}
	
	public interface ServiceXInterfaceBusinessLogic
	{
		public ProvideAResult provideA(ProvideARequest request);
	}
	
	public interface ServiceXInterfaceUserInterface
	{
		public ProvideARequest doX(DoXRequest request);
		
		public DoXResult processA(ProvideAResult provideAResult);
	}
	
	public class ProvideARequest implements Serializable
	{
		private static final long serialVersionUID = 1L;
	}
	public class ProvideAResult implements Serializable
	{
		private static final long serialVersionUID = 1L;
	}
	
	public class DoXRequest implements Serializable
	{
		public DoXRequest()
		{
		}
		
		private static final long serialVersionUID = 1L;
	}
	
	public class DoXAdapterRequest extends DoXRequest implements Serializable
	{
		public DoXAdapterRequest(ServiceXInterface serviceXInterface)
		{
			this.serviceXInterface = serviceXInterface;
		}
		
		public ServiceXInterface getInterface()
		{
			return serviceXInterface;
		}

		private ServiceXInterface serviceXInterface;
		
		private static final long serialVersionUID = 1L;
	}
	
	public class DoXResult implements Serializable
	{
		private static final long serialVersionUID = 1L;
	}
}
