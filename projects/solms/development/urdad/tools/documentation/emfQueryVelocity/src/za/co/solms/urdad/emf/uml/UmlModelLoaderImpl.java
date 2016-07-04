package za.co.solms.urdad.emf.uml;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class UmlModelLoaderImpl implements UmlModelLoader
{

	@Override
	public Resource loadModel(ModelLoadRequest modelLoadRequest) throws CouldNotLoadModelException
	{
		File file = new File(modelLoadRequest.getPatToModel()); 
	    // Get my test UML modelResource from the class loader
	    java.net.URI fileUri = file.toURI();
	    // Construct a URI using EMF's own URI framework
	    URI testModelURI = URI.createURI(fileUri.toString());

	    ResourceSet resourceSet = new ResourceSetImpl();

	    // Plug in UML modelResource loader (package and file extension recognition)
	    resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
	    // Manually add other 'compatible' UML package versions
	    resourceSet.getPackageRegistry().put( "http://schema.omg.org/spec/UML/2.1.2" , UMLPackage.eINSTANCE);
	    resourceSet.getPackageRegistry().put( "http://schema.omg.org/spec/UML/2.2" , UMLPackage.eINSTANCE);
	    
	    // Register both '.uml' as well as '.xml' for UML loading 
	    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "xml", UMLResource.Factory.INSTANCE);
	    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	    
	    
	    // Plug in path maps as per Eclipse wiki stand-alone instructions
	    Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
	    URI uri = URI.createURI("jar:file:/home/fritz/workspace/testModelQuerying/resources/runtime/uml2-3.0.0-rc5/org.eclipse.uml2.uml.resources_3.0.0.v200906011111.jar!/");
	    uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
	    uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodelResources").appendSegment(""));
	    uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));

	    // Load modelResource
	    Resource modelResource = resourceSet.getResource(testModelURI, true);
	    
	    if (modelResource.getErrors().size() > 0)
	    {
	   	 logger.severe("Failed to load model.");
	   	 throw new CouldNotLoadModelException(modelResource.getErrors().toString());
	    }
	    else
	    {
	   	 logger.info("Model loaded successfully");
	   	 return modelResource;
	    }
	}

	private Logger logger = Logger.getLogger("logger");
}
