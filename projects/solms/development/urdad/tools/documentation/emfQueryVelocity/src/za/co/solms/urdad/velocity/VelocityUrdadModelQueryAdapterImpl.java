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
 * @author fritz@solms.co.za
 *
 */
public class VelocityUrdadModelQueryAdapterImpl implements
		VelocityUrdadModelQueryAdapter
{
	public VelocityUrdadModelQueryAdapterImpl(EmfUrdadModelQueryInterface modelQueryInterface)
	{
		this.modelQueryInterface = modelQueryInterface;
	}

	public Result<List<String>> getAnnotations(Element element)
	{
		try
		{
			return new Result<List<String>>(modelQueryInterface.getAnnotations(element));
		}
		catch (Exception e)
		{
			return new Result<List<String>>(e);
		}		
	}

	@Override
	public Set<ConditionalFunctionalRequirement> getConditionalFunctionalRequirements(UseCase useCase)
	{
		return modelQueryInterface.getConditionalFunctionalRequirements(useCase);
	}

	@Override
	public Set<UseCase> getFunctionalRequirements(UseCase useCase)
	{
		return modelQueryInterface.getFunctionalRequirements(useCase);
	}

	@Override
	public Set<UseCase> getMandatoryFunctionalRequirements(UseCase useCase)
	{
		return modelQueryInterface.getMandatoryFunctionalRequirements(useCase);
	}
	
	public Set<Interface> getStakeholdersForUseCase(UseCase useCase)
	{
		return modelQueryInterface.getStakeholdersForUseCase(useCase);
	}
	
	public Set<Interface> getStakeholdersForFunctionalRequirement(UseCase functionalRequirement)
	{
		return modelQueryInterface.getStakeholdersForFunctionalRequirement(functionalRequirement);
	}

	/* (non-Javadoc)
	 * @see za.co.solms.urdad.velocity.VelocityUrdadModelQueryAdapter#getUseCase(java.lang.String)
	 */
	@Override
	public Result<UseCase> getUseCase(String qualifiedUseCaseName)
	{
		try
		{
			return new Result<UseCase>(modelQueryInterface.getUseCase(qualifiedUseCaseName));
		}
		catch (Exception e)
		{
			return new Result<UseCase>(e);
		}		
	}

	/* (non-Javadoc)
	 * @see za.co.solms.urdad.velocity.VelocityUrdadModelQueryAdapter#getUserContract(org.eclipse.uml2.uml.UseCase)
	 */
	@Override
	public Result<Interface> getUserContract(UseCase useCase)
	{
		try
		{
			return new Result<Interface>(modelQueryInterface.getUserContract(useCase));
		}
		catch (Exception e)
		{
			return new Result<Interface>(e);
		}		
	}

	private EmfUrdadModelQueryInterface modelQueryInterface;

}
