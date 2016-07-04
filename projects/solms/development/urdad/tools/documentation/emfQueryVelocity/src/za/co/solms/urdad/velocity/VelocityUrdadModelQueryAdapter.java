/**
 * 
 */
package za.co.solms.urdad.velocity;

import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UseCase;

import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface;
import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface.ConditionalFunctionalRequirement;

/**
 * An adapter to the EmfUrdadModelQueryInterface which is suitable to be used with Apache Velocity or any other
 * frameworks which do not support exception handling. It adapts to the services provided by the EmfUrdadModelQueryInterface,
 * returning a {@link Result}result object which either contains the return value or the exception which was thrown
 * upon refusal of service.
 * 
 * @see {@link EmfUrdadModelQueryInterface}
 * @see {@link Result}
 * 
 * @author fritz@solms.co.za
 *
 */
public interface VelocityUrdadModelQueryAdapter
{
	/**
	 * @see {@link EmfUrdadModelQueryInterface}
	 * @see {@link Result}
	 */
	public Result<List<String>> getAnnotations(Element element);
	
	/**
	 * @see {@link EmfUrdadModelQueryInterface}
	 * @see {@link Result}
	 */
	public Result<Interface> getUserContract(UseCase useCase); 

	/**
	 * @see {@link EmfUrdadModelQueryInterface}
	 * @see {@link Result}
	 */
	public Result<UseCase> getUseCase(String qualifiedUseCaseName);
	
	public Set<Interface> getStakeholdersForUseCase(UseCase useCase);
	
	public Set<Interface> getStakeholdersForFunctionalRequirement(UseCase functionalRequirement);
	
	public Set<UseCase> getMandatoryFunctionalRequirements(UseCase useCase);
	
	public Set<ConditionalFunctionalRequirement> getConditionalFunctionalRequirements(UseCase useCase);
	
	public Set<UseCase> getFunctionalRequirements(UseCase useCase);
	

	/**
	 * This is a result wrapper for velocity based calls which provide either the result or the
	 * exception thrown upon refusal of the service. This assists because there is no support
	 * for exception handling within Apache Velocity.
	 * 
	 * @author fritz@solms.co.za
	 *
	 * @param <T> the return type for the service
	 */
	public static class Result<T>
	{
		/**
		 * Creates a result with the return object - the service has been provided and no exception was thrown.
		 * 
		 * @param result the return value for the requested service
		 */
		public Result(T result) 
		{
			this.result = result;
			serviceProvided = true;
	   }
		
		/**
		 * Creates the result object with the exception which was thrown due to refusal of service.
		 * 
		 * @param exception the exception thrown upon refusal of service.
		 */
		public Result(Exception exception) 
		{
			serviceProvided = false;
			this.exception = exception;
		}
		
		/**
		 * 
		 * @return true if the service was provided and false otherwise.
		 */
		public boolean getServiceProvided()
		{
			return serviceProvided;
		}
		
		/**
		 * 
		 * @return the result for the requested service. This will be null if the service was not provided and an exception was thrown.
		 */
		public T getResult() 
		{
			return result;
	   }
		
		/**
		 * 
		 * @return the exception which was thrown upon refusal of the service or null if the service was provided.
		 */
		public Exception getException() {return exception;}
		
		private T result;
		private Exception exception;
		private boolean serviceProvided;
	}

}
