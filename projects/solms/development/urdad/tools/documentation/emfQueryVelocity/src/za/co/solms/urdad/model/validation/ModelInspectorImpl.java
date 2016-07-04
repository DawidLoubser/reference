package za.co.solms.urdad.model.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.ocl.uml.OCL.Query;
import org.eclipse.ocl.uml.util.OCLUMLUtil;
import org.eclipse.uml2.uml.Classifier;

import za.co.solms.urdad.emf.uml.UmlModelLoader;
import za.co.solms.urdad.emf.uml.UmlModelLoaderImpl;

public class ModelInspectorImpl implements ModelInspector
{
   public static void main( String[] args ) 
   {
  	 try
  	 {
	   	 if (args.length == 0)
	   	 {	 
	   		 System.out.println("Insufficient no of command line parameter.");
	   		 System.out.println("Manadatory parameters:   modelUri");
	   		 System.out.println("Optional parameters:   outputFileName");
	   	 }
	   	 
	   	 String pathToModel = args[0];
	   	 
	   	 String outputFileName = "modelValidation.txt"; 
	   	 if (args.length>=2)
	   		 outputFileName = args[1];
	   	 
	   	 logger.info("Output file: " + outputFileName);

	   	 Resource modelResource 
 	 			= new UmlModelLoaderImpl().loadModel(new UmlModelLoader.ModelLoadRequest(pathToModel));
			
	   	 ModelInspector modelInspector = new ModelInspectorImpl();
	   	 
//	   	 modelValidator.executeQuery(new ExecuteQueryRequest(modelResource, "self.name", 
//	   			 getFirstClassifier(modelResource, org.eclipse.uml2.uml.Interface.class)));
	   	 
	   	 modelInspector.executeQuery(new ExecuteQueryRequest(modelResource, "self.name", 
	   			 getFirstClassifier(modelResource, org.eclipse.uml2.uml.Interface.class)));

	   	 System.out.println(modelInspector.validateModel
	   	 		(new ValidateModelRequest(modelResource, new UrdadValidationSuite().getConstraintsSet())));
	  	 }
	  	 catch (Exception e)
	  	 {
	  		 e.printStackTrace();
	  	 }
   }

   //-----------------------------------------------------------------------------------------------------------
   
   public InitializeOclMetaModelEnvironmentResult initializeOclEnvironment
   		(InitializeOclMetaModelEnvironmentRequest request)
   {
   	  Resource modelResource = request.getModelResource();
		  OCL	  ocl = OCL.newInstance(new UMLEnvironmentFactory(modelResource.getResourceSet()));

		  //Get some classifier from the model to make the context the meta model.
		  Classifier classifier = OCLUMLUtil.getMetaclass(getFirstClassifier(modelResource, org.eclipse.uml2.uml.Interface.class));
		  OCL.Helper helper = ocl.createOCLHelper();
        helper.setContext(classifier);
        
        return new InitializeOclMetaModelEnvironmentResult(new OclQueryingEnvironment(ocl, helper));
   }

   //-------------------------------------------------------------------------------------------------------------
   
	public ValidateModelResult validateModel(ValidateModelRequest validateModelRequest)
	{
		  Resource modelResource = validateModelRequest.getModelResource();

		  OclQueryingEnvironment oclQueryingEnvironment 
		  		= initializeOclEnvironment(new InitializeOclMetaModelEnvironmentRequest(modelResource)).getOclQueryingEnvironment();
		  
		  Map<Constraint,Map<EObject,Boolean>> constraintResults = new HashMap<Constraint,Map<EObject,Boolean>>();
		  
		  for (Constraint constraint : validateModelRequest.getConstraints())
		  {
				  try
				  {
					  	ApplyConstraintAcrossModelResult result 
					  	  = applyConstraintAcrossModel
					  	    (new ApplyConstraintAcrossModelRequest(oclQueryingEnvironment, modelResource, constraint));
					  	
					  	constraintResults.put(result.getConstraint(), result.getResults());
				  }  	
				  catch (ParserException e)
				  {
					  constraintResults.put(constraint, null);
					  e.printStackTrace();
				  }
		  }
		  return new ValidateModelResult(constraintResults);
	}

	//-------------------------------------------------------------------------------------------------------------

	private static org.eclipse.uml2.uml.Classifier getFirstClassifier(Resource modelResource, 
			  	Class<? extends Classifier> classifierType)
	  {
		  Iterator<EObject> modelContentIterator = modelResource.getAllContents();
		  while (modelContentIterator.hasNext())
		  {
			  EObject obj = modelContentIterator.next();
			  if (classifierType.isAssignableFrom(obj.getClass()))
				  return (org.eclipse.uml2.uml.Classifier)obj;
		  }
		  return null;
	  }

	//-------------------------------------------------------------------------------------------------------------

	public ApplyConstraintToModelElementResult applyConstraintToModelElement
			(ApplyConstraintToModelElementRequest request) throws InvalidContextException, ParserException
	{
		OclQueryingEnvironment oclQueryingEnvironment = request.getOclQueryingEnvironment();
		Constraint constraint = request.getConstraint();
		EObject element = request.getModelElement();

		if (!constraint.getContext().isAssignableFrom(element.getClass()))
			throw new InvalidContextException();
			  
       boolean result = oclQueryingEnvironment.getOcl().check(element,
      		oclQueryingEnvironment.getOclHelper().createConstraint
      				(constraint.getConstraintKind(), constraint.getConstraintExpression()));

   	 logger.info("Validation result for " + element.toString() + " = " + result);

		return new ApplyConstraintToModelElementResult(constraint, element, result);
	}

	//-------------------------------------------------------------------------------------------------------------

	public ApplyConstraintAcrossModelResult applyConstraintAcrossModel
			(ApplyConstraintAcrossModelRequest request) throws ParserException
	{
		Resource modelResource = request.getModelResource();
		Constraint constraint = request.getConstraint();	  

		Map<EObject, Boolean> results = new HashMap<EObject, Boolean>();
		
     TreeIterator<EObject> modelElements = modelResource.getAllContents();
	  while (modelElements.hasNext())
	  {
	      EObject o = modelElements.next();
	      
	      try
	      {
			      if (constraint.getContext().isAssignableFrom(o.getClass()))
			      {
			      	ApplyConstraintToModelElementResult result 
			      	    = applyConstraintToModelElement(new ApplyConstraintToModelElementRequest
			      		      (request.getOclQueryingEnvironment(), o, constraint));
			      	results.put(result.getElement(), new Boolean(result.hasPassed()));
			      } 	
	      }
	      catch (InvalidContextException e)
	      {
	      	throw new RuntimeException("Coding bug.");
	      }
	  }
	  return new ApplyConstraintAcrossModelResult(constraint, results);
	}

	public ExecuteQueryResult executeQuery(ExecuteQueryRequest request)
			throws ParserException
	{
		  Resource modelResource = request.getModelResource();

		  OclQueryingEnvironment oclQueryingEnvironment 
		  		= initializeOclEnvironment(new InitializeOclMetaModelEnvironmentRequest(modelResource)).getOclQueryingEnvironment();
		  OCL ocl = oclQueryingEnvironment.getOcl();
		  OCL.Helper helper = oclQueryingEnvironment.getOclHelper();

		  OCLExpression queryExpression = helper.createQuery(request.getQueryExpression()); //("self.name");

		  Query query = ocl.createQuery(queryExpression);
        
        System.out.println("OCL query: " + query.getExpression());

      	Object queryResult = query.evaluate(request.getQueryTarget());
      	 System.out.println("Query result: " + queryResult);

		  return null;
	}
			      
    private static Logger logger = Logger.getLogger("logger");
}
