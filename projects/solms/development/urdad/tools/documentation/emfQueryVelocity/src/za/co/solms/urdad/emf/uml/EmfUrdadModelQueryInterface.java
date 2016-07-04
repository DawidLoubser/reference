/**
 * 
 */
package za.co.solms.urdad.emf.uml;

import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UseCase;

/**
 * A general contract for the information required from a URDAD compliant UML model in order to perform tasks like documentation
 * generation and implementation mapping.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface EmfUrdadModelQueryInterface
{
	/**
	 * Returns the textual annotations (comments) assigned to a model element as a list of strings.
	 * @param element the element for which the annotations are requested
	 * @return a list of all the annotations applied to the provided model element.
	 */
	public List<String> getAnnotations(Element element);
	
	/**
	 * Returns the use case with the specified qualified name.
	 * 
	 * @param qualifiedUseCaseName
	 * @return the use case
	 * @throws ElementDoesNotExistException if a use case with the specified qualified name is not available in the model,
	 */
	public UseCase getUseCase(String qualifiedUseCaseName) throws ElementDoesNotExistException;
	
	/**
	 * Returns the set of stake holders who have an interest in the use case, i.e. who have dependencies on
	 * functional requirements for the use case.
	 * 
	 * @param useCase the use case whose stake holders are to be queried
	 * 
	 * @return the set of stake holders for the use case.
	 */
	public Set<Interface> getStakeholdersForUseCase(UseCase useCase);
	
	/**
	 * Returns the set of stake holders who have a particular functional requirement
	 * 
	 * @param functionalRequirement the functional requirement whose stake holders are to be provided
	 * 
	 * @return the set of stake holders for the use case.
	 */
	public Set<Interface> getStakeholdersForFunctionalRequirement(UseCase functionalRequirement);
	
	/**
	 * Returns the mandatory functional requirements for a use case,
	 * i.e. the use cases which are linked via an includes relationship to the specified use case
	*
	 * @param useCase the use case for which the mandatory functional requirements are requested.
	 * @return the set of mandatory functional requirements for the use case
	 */
	public Set<UseCase> getMandatoryFunctionalRequirements(UseCase useCase);
	
	/**
	 * Returns the conditional functional requirements for a use case,
	 * i.e. the use cases which are linked via an includes relationship to the specified use case
	*
	 * @param useCase the use case for which the mandatory functional requirements are requested.
	 * @return the set of conditional functional requirements for the use case
	 */
	public Set<ConditionalFunctionalRequirement> getConditionalFunctionalRequirements(UseCase useCase);
	
	/**
	 * Obtains the set of both, mandatory and conditional functional requirements for a use case.
	 * @param useCase the use case for which the functional requirements are requested.
	 * @return the set of use cases representing the functional requirements for the provided use case.
	 */
	public Set<UseCase> getFunctionalRequirements(UseCase useCase);
	
	/**
	 * Returns the user contract for a use case.
	 * 
	 * @param useCase the use case for which the user contract is requested
	 * @return the user contract for the specified use case
	 * @throws UserContractNotSpecifiedException if no user contract has been specified for the use case
	 * @throws MultipleUserContractException if multiple user contracts have been specified for the use case
	 */
	public Interface getUserContract(UseCase useCase) throws UserContractNotSpecifiedException, MultipleUserContractException;

	/**
	 * An exception which notifies that a user contract has not been specified for a use case.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public static class UserContractNotSpecifiedException extends Exception 
	{
		private static final long serialVersionUID = 200907151459L;
	}

	/**
	 * An exception which notifies that multiple user contracts have been specified for a use case. 
	 * to an interface.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public static class MultipleUserContractException extends Exception 
	{
		private static final long serialVersionUID = 200907151459L;
	}

	/**
	 * An exception which notifies the user of a service that a requested service could not be provided because a particular element
	 * does not exist in a model.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public static class ElementDoesNotExistException extends Exception 
	{
		private static final long serialVersionUID = 200907071617L;
	}
	
	/**
	 * A wrapper for a conditional functional requirement which contains the use case for the functional
	 * requirement as well as the constraint for the condition under which the conditional functional
	 * requirement needs to be addressed.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public static class ConditionalFunctionalRequirement
	{
		/**
		 * Creates a conditional functional requirement with the use case for the functional requirement as well as the
		 * constraint for the condition under which the functional requirement needs to be addressed.
		 * @param functionalRequirement the use case for the service through which the functional requirement is addressed.
		 * @param condition the constraint specifying the condition under which the functional requirement needs to be addressed.
		 */
		public ConditionalFunctionalRequirement(UseCase functionalRequirement, Constraint condition)
		{
			this.functionalRequirement = functionalRequirement;
			this.condition = condition;
		}
		
		/**
		 * 
		 * @return the use case representing the service for the functional requirement.
		 */
		public UseCase getFunctionalRequirement() {return functionalRequirement;}
		
		/**
		 * 
		 * @return the constraint for the condition under which the conditional functional requirement needs to be addressed.
		 */
		public Constraint getCondition() {return condition;}
		
		private UseCase functionalRequirement;
		private Constraint condition;
	}
}
