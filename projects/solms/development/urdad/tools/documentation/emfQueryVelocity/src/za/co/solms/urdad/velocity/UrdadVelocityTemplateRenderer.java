/**
 * 
 */
package za.co.solms.urdad.velocity;

import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface;

/**
 * Renders an URDAD UML model via velocity templates.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface UrdadVelocityTemplateRenderer
{
		public void renderVelocityTemplate(VelocityTemplateRenderRequest velocityTemplateRenderRequest)
		 throws VelocityException;

		/**
		 * The request class encapsulating a velocity rendering request.
		 * 
		 * @author fritz@solms.co.za
		 *
		 */
		public static class VelocityTemplateRenderRequest
		{
			public VelocityTemplateRenderRequest(
					EmfUrdadModelQueryInterface modelQueryInterface,
					String qualifiedUseCaseName,
					String pathToVelocityTemplate,
					String pathToOutputFile)
			{
				this.modelQueryInterface = modelQueryInterface;
				this.qualifiedUseCaseName = qualifiedUseCaseName;
				this.pathToOutputFile = pathToOutputFile;
				this.pathToVelocityTemplate = pathToVelocityTemplate;
			}
			
			public EmfUrdadModelQueryInterface getModelQueryInterface()
			{
				return modelQueryInterface;
			}
			public String getQualifiedUseCaseName()
			{
				return qualifiedUseCaseName;
			}
			public String getPathToVelocityTemplate()
			{
				return pathToVelocityTemplate;
			}
			public String getPathToOutputFile()
			{
				return pathToOutputFile;
			}

			private EmfUrdadModelQueryInterface modelQueryInterface;
			private String qualifiedUseCaseName;
			private String pathToVelocityTemplate;
			private String pathToOutputFile;
		}
		

		/**
		 * A general wrapper class for exceptions thrown by velocity. It encapsulates the actual exception thrown. 
		 * This was introduced as the velocity rendering framework may potentially throw any exception.
		 * 
		 * @author fritz@solms.co.za
		 *
		 */
		public static class VelocityException extends Exception 
		{
			public VelocityException(Exception exceptionThrownByVelocity)
			{
				this.exceptionThrownByVelocity = exceptionThrownByVelocity;
			}
			
			public Exception getExceptionThrownByVelocity()
			{
				return exceptionThrownByVelocity;
			}

			private Exception exceptionThrownByVelocity;
			
			private static final long serialVersionUID = 200907071146L;
		}

}
