package za.co.solms.urdad.model.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.uml.OCL;

/**
 * A utility class which simplifies the tasks of querying an validating a UML model using OCL queries and constraints.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface ModelInspector
{
	
	/**
	 * Executes an OCL query against a model element.
	 * 
	 * @param request the query request
	 * @return the query result
	 * @throws ParserException if the query expression does not parse against OCL syntax.
	 */
	public ExecuteQueryResult executeQuery(ExecuteQueryRequest request) throws ParserException;
	
	public class ExecuteQueryRequest
	{
		public ExecuteQueryRequest(Resource modelResource, String queryExpression, EObject queryTarget)
		{
			this.modelResource = modelResource;
			this.queryExpression = queryExpression;
			this.queryTarget = queryTarget;
		}
		
		public Resource getModelResource() {return modelResource;}
		
		public String getQueryExpression() {return queryExpression;}
		
		public EObject getQueryTarget() {return queryTarget;}
		
		private Resource modelResource;
		private String queryExpression;
		private EObject queryTarget;
	}
	
	public class ExecuteQueryResult
	{
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	
	/**
	 *  Validates a UML model against a set of OCL constraints, i.e. against a validation suite.
	 */
	public ValidateModelResult validateModel(ValidateModelRequest validateModelRequest);
	
	public static class ValidateModelRequest
	{
		public ValidateModelRequest(Resource modelResource, Set<Constraint> constraints)
		{
			this.modelResource = modelResource;
			this.constraints = constraints;
		}
		
		public Set<Constraint> getConstraints() {return constraints;}
		
		public Resource getModelResource() {return modelResource;}
		
		private Resource modelResource;
		private Set<Constraint> constraints;
	}
	
	/**
	 * Encapsulates a map of maps which, for each constraint has a map of elements which are substitutable for the 
	 * context mapped onto the true of false depending on whether that element satisfies the constraint or not.
	 * @author fritz@solms.co.za
	 *
	 */
	public static class ValidateModelResult 
	{
		public ValidateModelResult(Map<Constraint,Map<EObject,Boolean>> constraintResults)
		{
			this.constraintResults = constraintResults;
		}
		
		public Map<Constraint,Map<EObject,Boolean>> getConstraintResults() {return constraintResults;}
		
		public String toString()
		{
  	      String eol = System.getProperty("line.separator");
			String constraintDelimiter = eol + "--------------------------------------------------------------------------------------" + eol;
			StringBuffer text = new StringBuffer(constraintDelimiter);
			
			for (Constraint constraint: constraintResults.keySet())
			{
				text.append(constraint).append(eol);
				Map<EObject,Boolean> results = constraintResults.get(constraint);
				for (EObject element:results.keySet())
					text.append(element).append(" -> ").append(results.get(element)).append(eol);
			}
			
			return text.toString();
		}
		
		private Map<Constraint,Map<EObject,Boolean>>
			constraintResults = new HashMap<Constraint,Map<EObject,Boolean>>();
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Initializes the OCL meta model environment, setting the context to a meta model element in order
	 * to be able to query the meta model.
	 */
   public InitializeOclMetaModelEnvironmentResult initializeOclEnvironment
   		(InitializeOclMetaModelEnvironmentRequest request);
   
   public class InitializeOclMetaModelEnvironmentRequest
   {
   	public InitializeOclMetaModelEnvironmentRequest(Resource modelResource)
   	{
   		this.modelResource = modelResource;
   	}
   	
   	public Resource getModelResource() {return modelResource;}
   	
   	private Resource modelResource;
   }

   public class InitializeOclMetaModelEnvironmentResult
   {
   	public InitializeOclMetaModelEnvironmentResult(OclQueryingEnvironment oclQueryingEnvironment)
   	{
   		this.oclQueryingEnvironment = oclQueryingEnvironment;
   	}
   	public OclQueryingEnvironment getOclQueryingEnvironment() {return oclQueryingEnvironment;}
   	
   	private OclQueryingEnvironment oclQueryingEnvironment;
   }
   
   /**
    * Encapsulates an OCL querying environment which contains the OCL querying/parsing object as well
    * as a helper to create appropriate queries.
    * 
    * @author fritz@solms.co.za
    *
    */
	public class OclQueryingEnvironment
	{
		public OclQueryingEnvironment(OCL ocl, OCL.Helper oclHelper)
		{
			this.ocl = ocl;
			this.oclHelper = oclHelper;
		}
		
		public OCL getOcl() {return ocl;}
		public OCL.Helper getOclHelper() {return oclHelper;}
		private OCL.Helper oclHelper;
		private OCL ocl;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Applies a constraint to a specified model element.
	 * 
	 * @throws InvalidContextException if the element is not substitutable for the context of the constraint.
	 */
	public ApplyConstraintToModelElementResult applyConstraintToModelElement 
		(ApplyConstraintToModelElementRequest request) throws InvalidContextException, ParserException;
	
	public static class ApplyConstraintToModelElementRequest
	{
		public ApplyConstraintToModelElementRequest
			(OclQueryingEnvironment oclQueryingEnvironment, EObject element, Constraint constraint)
		{
			this.oclQueryingEnvironment = oclQueryingEnvironment;
			this.element = element;
			this.constraint = constraint;
		}
		
		public OclQueryingEnvironment getOclQueryingEnvironment() {return oclQueryingEnvironment;}
		
		/**
		 * 
		 * @return the model element to which the constraint is to be applied.
		 */
		public EObject getModelElement() {return element;}
		
		public Constraint getConstraint() {return constraint;}
		
		private OclQueryingEnvironment oclQueryingEnvironment;
		private EObject element;
		private Constraint constraint;
	}
	
	public static class ApplyConstraintToModelElementResult
	{
		public ApplyConstraintToModelElementResult(Constraint constraint, EObject element, boolean pass)
		{
			this.constraint = constraint;
			this.element = element;
			this.pass = pass;
		}
		
		public Constraint getConstraint() {return constraint;}
		public EObject getElement() {return element;}
		public boolean hasPassed() {return pass;}
		
		public String toString()
		{
			return element.toString() + ": " + constraint + " -> pass = " + pass; 
		}
		
		private Constraint constraint;
		private EObject element;
		private boolean pass;
	}
	

	//----------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Applies a constraint across an entire model by finding all elements which are substitutable for the specified
	 * context of the constraint and applying the constraint to them. 
	 */
	public ApplyConstraintAcrossModelResult applyConstraintAcrossModel (ApplyConstraintAcrossModelRequest request)
			throws ParserException;
	
	public static class ApplyConstraintAcrossModelRequest
	{
		public ApplyConstraintAcrossModelRequest
				(OclQueryingEnvironment oclQueryingEnvironment, Resource modelResource, Constraint constraint)
		{
			this.oclQueryingEnvironment = oclQueryingEnvironment;
			this.modelResource = modelResource;
			this.constraint = constraint;
		}
		
		public OclQueryingEnvironment getOclQueryingEnvironment() {return oclQueryingEnvironment;}
		
		public Resource getModelResource() {return modelResource;}
		
		public Constraint getConstraint() {return constraint;}
		
		private OclQueryingEnvironment oclQueryingEnvironment;
		private Resource modelResource;
		private Constraint constraint;
	}
	
	public static class ApplyConstraintAcrossModelResult
	{
		 public ApplyConstraintAcrossModelResult(Constraint constraint, Map<EObject,Boolean> results)
		 {
			 this.constraint = constraint;
			 this.results = results;
		 }
		 
		 public Constraint getConstraint() {return constraint;}
		 
		 public Map<EObject,Boolean> getResults() {return results;}
		 
		 public String toString()
		 {
			 String eol = System.getProperty("line.separator");
			 StringBuffer text = new StringBuffer("Applying constraint " + constraint + ":" + eol);
			 for (EObject element : results.keySet())
				 text.append(element).append(" -> ").append(results.get(element)).append(eol);

			 return text.toString();
		 }
		 
		 private Constraint constraint;
		 private Map<EObject,Boolean> results = new HashMap<EObject,Boolean>();
	}

	/**
	 * An exception which is thrown if a constraint is applied to an object which does not provide a valid
	 * context for the constraint (e.g. a post-condition applied to a class).
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public class InvalidContextException extends Exception 
	{
		private static final long serialVersionUID = 1L;
	}
	
}
