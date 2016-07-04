package za.co.solms.urdad.model.validation;

import java.util.Set;

/**
 * An interface for sources of sets of OCL constraints. This is typically a OCL validation suite used to
 * validate a UML model.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface ConstraintsSource
{
	/**
	 * 
	 * @return the set of constraints.
	 */
	public Set<Constraint> getConstraintsSet();
}
