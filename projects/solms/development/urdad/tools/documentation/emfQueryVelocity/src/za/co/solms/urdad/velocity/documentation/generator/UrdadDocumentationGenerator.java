package za.co.solms.urdad.velocity.documentation.generator;

import za.co.solms.urdad.emf.uml.UmlModelLoader.CouldNotLoadModelException;
import za.co.solms.urdad.velocity.UrdadVelocityTemplateRenderer.VelocityException;

public interface UrdadDocumentationGenerator
{

	/**
	 * This service
	 * 
	 * @param pathTotemplate the path to the velocity template which should be executed
	 * @param pathTomodel the path to the model containing the use case for which documentation should be generated
	 * @param qualifiedUseCaseName the qualified name of the use case for which the documentation should be generated
	 * @param outputFileName the name of the output file containing the generated documentation
	 * @param options not yet supported
	 * @throws CouldNotLoadModelException if the model loading process resulted in errors.
	 */
	public abstract void generateUrdadDocumentation(
			UrdadDocumentationGenerationRequest documentationGenerationRequest)
					throws CouldNotLoadModelException, VelocityException;

   /**
    * This class encapsulates a request for generating documentation for a URDAD compliant UML model.
    * 
    * @author fritz@solms.co.za
    *
    */
   public static class UrdadDocumentationGenerationRequest
   {
		/**
       * Creates a UrdadDocumentationGenerationRequest.
       * 
       * @param pathTotemplate the path to the velocity template which should be executed
       * @param pathTomodel the path to the model containing the use case for which documentation should be generated
       * @param qualifiedUseCaseName the qualified name of the use case for which the documentation should be generated
       * @param outputFileName the name of the output file containing the generated documentation
       * @param options not yet supported
       */
  	 public UrdadDocumentationGenerationRequest(String pathToTemplate, String pathToModel, String qualifiedUseCaseName, String outputFileName, String[] options)
  	 {
  		 this.pathToTemplate = pathToTemplate;
  		 this.pathToModel = pathToModel;
  		 this.qualifiedUseCaseName = qualifiedUseCaseName;
  		 this.outputFileName = outputFileName;
  		 if (options != null)
  			 this.options = options.clone();
  	 }

      public String getPathToTemplate()
		{
			return pathToTemplate;
		}
		public String getPathToModel()
		{
			return pathToModel;
		}
		public String getQualifiedUseCaseName()
		{
			return qualifiedUseCaseName;
		}
		public String getOutputFileName()
		{
			return outputFileName;
		}
		public String[] getOptions()
		{
			return options.clone();
		}
  	 
  	 
  	 private String pathToTemplate;
  	 private String pathToModel;
  	 private String qualifiedUseCaseName;
  	 private String outputFileName;
  	 private String[] options;
   }
}