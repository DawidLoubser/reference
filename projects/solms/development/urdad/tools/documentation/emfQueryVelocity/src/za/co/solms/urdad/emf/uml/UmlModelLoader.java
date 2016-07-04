package za.co.solms.urdad.emf.uml;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Convenience class to load a UML model with its meta model stack.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface UmlModelLoader
{
		/**
		 * Loads the model from a file path to the model.
		 * 
		 * @param pathToModel the file path to the model
		 * 
		 * @throws CouldNotLoadModelException if the model loading process resulted in errors.
		 * 
		 * @return a resource set containing the UML model and the supporting meta models. 
		 */
		public Resource loadModel(ModelLoadRequest modelLoadRequest)  throws CouldNotLoadModelException;
		
		/**
		 * The request object for loading a model.
		 * 
		 * @author fritz@solms.co.za
		 *
		 */
		public static class ModelLoadRequest
		{
			/**
			 * Creates a model load request.
			 * 
			 * @param pathToModel the file path to the model
			 */
			public ModelLoadRequest(String pathToModel)
			{
				this.pathToModel = pathToModel;
			}
			
			public String getPatToModel() {return pathToModel;}
			
			private String pathToModel;
		}
		
		/**
		 * A generic exception class thrown when the model could not be loaded. The message contains the
		 * errors reported by the EMF model loader.
		 * 
		 * @author fritz@solms.co.za
		 *
		 */
		public class CouldNotLoadModelException extends Exception
		{

			public CouldNotLoadModelException () {}
			
			public CouldNotLoadModelException(String errorMessage) {super(errorMessage);}

			private static final long serialVersionUID = 200907071014L;
		}
		
}
