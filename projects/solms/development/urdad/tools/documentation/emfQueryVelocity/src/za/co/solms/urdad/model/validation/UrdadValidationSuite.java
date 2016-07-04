package za.co.solms.urdad.model.validation;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ocl.helper.ConstraintKind;
import org.eclipse.uml2.uml.UseCase;

/**
 * Provides the set of URDAD model constraints which can be used whether a UML model
 * is a valid and complete URDAD model.
 * 
 * @author fritz@solms.co.za
 *
 */
public class UrdadValidationSuite implements ConstraintsSource
{

	@Override
	public Set<Constraint> getConstraintsSet()
	{
		Set<Constraint> constraints = new HashSet<Constraint>();
		  
		  String constraintExpr 
		    = "(supplierDependency->size()>0) and  (supplierDependency->exists(d | d.oclIsKindOf(Realization) and " +
		  "d.source->exists(s | s.oclIsKindOf(Operation) and s.oclAsType(Operation).name=self.name and " +
		  "s.oclAsType(Operation).interface.oclIsKindOf(Interface))))";
		  
		  constraints.add(new Constraint("useCaseRealizedByContractServiceOfSameName", 
				  UseCase.class, 
				  ConstraintKind.INVARIANT,
				  constraintExpr,
				  "There must be a realization relationship from the use case representing the functional/service requirement to the service " +
				  "satisfying that functional requirement. The service must be in an interface representing the services contract for " +
				  "that use case. The use case and the service in the services contract should have the same name."));
		  
		  return constraints;
	}

}
