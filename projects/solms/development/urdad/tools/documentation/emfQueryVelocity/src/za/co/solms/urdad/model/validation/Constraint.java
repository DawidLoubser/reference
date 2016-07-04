package za.co.solms.urdad.model.validation;

import org.eclipse.ocl.helper.ConstraintKind;
import org.eclipse.uml2.uml.Element;

/**
 * 
 * @author fritz@solms.co.za
 *
 * Encapsulates the concept of an OCL constraint with a name, a context a constraint type and a constraint expression.
 */
public class Constraint
{
	public Constraint(String name, Class<? extends Element> context, ConstraintKind constraintKind, String constraintExpression,
			String constraintViolationMessage)
	{
		this.name = name;
		this.context = context;
		this.constraintKind = constraintKind;
		this.constraintExpression = constraintExpression;
		this.constraintViolationMessage = constraintViolationMessage;
	}
	
	/**
	 * 
	 * @return the constraint name which should map onto the OCL name for the constraint
	 */
	public String getName() {return name;}

	/**
	 * 
	 * @return the OCL constraint type as defined in {@link ConstraintKind}. 
	 */
	public ConstraintKind getConstraintKind()
	{
		return constraintKind;
	}

	/**
	 * 
	 * @return the context (self) to which the constraint is to apply
	 */
	public Class<? extends Element> getContext()
	{
		return context;
	}

	/**
	 * 
	 * @return the actual boolean evaluation expression which needs to evaluate to true if the constraint is satisfied
	 * and false otherwise.
	 */
	public String getConstraintExpression()
	{
		return constraintExpression;
	}
	
	public String toString()
	{
		return "context " + context.getName() + " " + constraintKind + " " + name + ": " + constraintExpression;
	}
	
	/**
	 * 
	 * @return the message explaining in more detail what was not met.
	 */
	public String getConstraintViolationMessage()
	{
		return constraintViolationMessage;
	}

	private Class<? extends Element> context;
	private String name, constraintViolationMessage;
	private ConstraintKind constraintKind;
	private String constraintExpression;
}
