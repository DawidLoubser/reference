package za.co.solms.urdad.velocity.documentation.generator;

import java.util.logging.Logger;

import org.eclipse.emf.ecore.resource.Resource;

import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface;
import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterfaceImpl;
import za.co.solms.urdad.emf.uml.UmlModelLoader;
import za.co.solms.urdad.emf.uml.UmlModelLoaderImpl;
import za.co.solms.urdad.emf.uml.UmlModelLoader.CouldNotLoadModelException;
import za.co.solms.urdad.velocity.UrdadVelocityTemplateRendererImpl;
import za.co.solms.urdad.velocity.UrdadVelocityTemplateRenderer.VelocityException;
import za.co.solms.urdad.velocity.UrdadVelocityTemplateRenderer.VelocityTemplateRenderRequest;

public class UrdadDocumentationGeneratorImpl implements UrdadDocumentationGenerator
{
    public static void main( String[] args ) 
    {
   	 try
   	 {
	   	 if (args.length < 3)
	   	 {	 
	   		 System.out.println("Insufficient no of command line parameter.");
	   		 System.out.println("Manadatory parameters:   templateURI  modelUri  qualifiedUseCaseName");
	   		 System.out.println("Optional parameters:   outputFileName  options");
	   	 }
	   	 
	   	 String pathToTemplate = args[0];
	   	 String pathToModel = args[1];
	   	 String qualifiedUseCaseName = args[2];
	   	 
	   	 String outputFileName = qualifiedUseCaseName; 
	   	 if (args.length>=4)
	   		 outputFileName = args[3];
	   	 
	   	 logger.info("Output file: " + outputFileName);
	   		 
	   	 String[] options = null;
	   	 
	   	 UrdadDocumentationGenerationRequest documentationGenerationRequest
	   	   = new UrdadDocumentationGenerationRequest(pathToTemplate, pathToModel, qualifiedUseCaseName, outputFileName, options);
	   	 
	   	 new UrdadDocumentationGeneratorImpl().generateUrdadDocumentation(documentationGenerationRequest);
   	 }
   	 catch (Exception e)
   	 {
   		 e.printStackTrace();
   	 }
	   	 
    }
    
    /* (non-Javadoc)
	 * @see za.co.solms.urdad.velocity.documentation.generator.UrdadDocumentationGenerator#generateUrdadDocumentation(za.co.solms.urdad.velocity.documentation.generator.UrdadDocumentationGeneratorImpl.UrdadDocumentationGenerationRequest)
	 */
    public void generateUrdadDocumentation(UrdadDocumentationGenerationRequest documentationGenerationRequest)
    		throws CouldNotLoadModelException,  VelocityException
    {
   	 Resource umlResource 
   	 		= new UmlModelLoaderImpl().loadModel(new UmlModelLoader.ModelLoadRequest(documentationGenerationRequest.getPathToModel()));
   	 
   	 EmfUrdadModelQueryInterface modelQueryInterface 
   	 	= new EmfUrdadModelQueryInterfaceImpl(umlResource);
   	 VelocityTemplateRenderRequest velocityTemplateRenderRequest
   	   = new VelocityTemplateRenderRequest
   	   	(modelQueryInterface,
   	   	 documentationGenerationRequest.getQualifiedUseCaseName(), 
   	   	 documentationGenerationRequest.getPathToTemplate(), 
   	   	 documentationGenerationRequest.getOutputFileName());
   	 
   	 new UrdadVelocityTemplateRendererImpl().renderVelocityTemplate(velocityTemplateRenderRequest);
    }
    
    private static Logger logger = Logger.getLogger("logger");
}    
