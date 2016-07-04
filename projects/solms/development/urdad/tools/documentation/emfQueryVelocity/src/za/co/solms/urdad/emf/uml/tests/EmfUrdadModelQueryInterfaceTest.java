package za.co.solms.urdad.emf.uml.tests;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UseCase;
import org.junit.Before;
import org.junit.Test;

import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface;
import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterfaceImpl;
import za.co.solms.urdad.emf.uml.UmlModelLoader;
import za.co.solms.urdad.emf.uml.UmlModelLoaderImpl;
import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface.ConditionalFunctionalRequirement;
import za.co.solms.urdad.emf.uml.EmfUrdadModelQueryInterface.ElementDoesNotExistException;
import static junit.framework.Assert.*;

public class EmfUrdadModelQueryInterfaceTest
{
	  /** Before running any tests in this suite, set up the modelResource */
	  @Before
	  public void loadModel() throws Exception 
	  {
		    final String pathToModel = "models/insurance.xml";
		  	 modelResource 
		 		= new UmlModelLoaderImpl().loadModel(new UmlModelLoader.ModelLoadRequest(pathToModel));
		  	 
		    assertNotNull( modelResource );
		    assertTrue( modelResource.getContents().size() > 0 );
		  	 
	   	 modelQueryInterface = new EmfUrdadModelQueryInterfaceImpl(modelResource);
	  }
	  
	  @Test(expected = ElementDoesNotExistException.class)  
	  public void testGetUseCaseNotExisting() throws Exception
	  {
			  modelQueryInterface.getUseCase("someNonExisitingPackage::someNonExistingUseCase");
		}  
	  
	  @Test
	  public void testGetMandatoryFunctionalRequirements()
	  {
		  try
		  {
			  UseCase useCase = modelQueryInterface.getUseCase(qualifiedUseCaseName);
			  assertEquals(5, modelQueryInterface.getMandatoryFunctionalRequirements(useCase).size());
		  }
		  catch (Exception e)
		  {
			  logger.severe("Exception: " + e.getClass().getName());
			  fail("mandatory functional requirements not obtained");
		  }
	  }
	  
	  @Test
	  public void testGetFunctionalRequirements()
	  {
		  try
		  {
			  UseCase useCase = modelQueryInterface.getUseCase(qualifiedUseCaseName);
			  assertEquals(6, modelQueryInterface.getFunctionalRequirements(useCase).size());
		  }
		  catch (Exception e)
		  {
			  logger.severe("Exception: " + e.getClass().getName());
			  fail("mandatory functional requirements not obtained");
		  }
	  }
	  
	  @Test
	  public void testGetConditionalFunctionalRequirements()
	  {
		  try
		  {
			  UseCase useCase = modelQueryInterface.getUseCase(qualifiedUseCaseName);
			  assertEquals(1, modelQueryInterface.getConditionalFunctionalRequirements(useCase).size());
			  for (ConditionalFunctionalRequirement fr : modelQueryInterface.getConditionalFunctionalRequirements(useCase))
				  logger.info("Conditional functional requirement: " + fr.getFunctionalRequirement().getName());
		  }
		  catch (Exception e)
		  {
			  logger.severe("Exception: " + e.getClass().getName());
			  fail("mandatory functional requirements not obtained");
		  }
	  }

	  @Test
	  public void testGetUseCaseExisting() throws Exception 
	  {
		  try
		  {
			  assertNotNull(modelQueryInterface.getUseCase(qualifiedUseCaseName));
		  }
		  catch (ElementDoesNotExistException e)
		  {
			  assertTrue(false);
		  }
	  }

	  @Test
	  public void getUserContractTest()
	  {
		  try
		  {
			  UseCase useCase = modelQueryInterface.getUseCase(qualifiedUseCaseName);
			  Interface contract = modelQueryInterface.getUserContract(useCase);
			  assertNotNull(contract);
		  }
		  catch (Exception e)
		  {
			  logger.severe("Exception: " + e.getClass().getName());
			  fail("user contract not obtained");
		  }
	  }
	  
	  @Test
	  public void testGetAnnotations()
	  {
		  try
		  {
			  UseCase useCase = modelQueryInterface.getUseCase(qualifiedUseCaseName);
			  List<String> annotations = modelQueryInterface.getAnnotations(useCase);
			  assertEquals(1, annotations.size());
			  assertNotNull(annotations.get(0));
		  }
		  catch (Exception e)
		  {
			  logger.severe("Exception: " + e.getClass().getName());
			  fail("annotations not obtained");
		  }
	  }
	  
	  @Test
	  public void testGetStakeholders()
	  {
		  try
		  {
			  UseCase useCase = modelQueryInterface.getUseCase(qualifiedUseCaseName);
			  Set<Interface> stakeholders = modelQueryInterface.getStakeholdersForUseCase(useCase);
			  assertNotNull(stakeholders);
			  assertEquals(3, stakeholders.size());
			  for (Interface stakeholder:stakeholders)
				  assertNotNull(stakeholder);
		  }
		  catch (Exception e)
		  {
			  logger.severe("Exception: " + e.getClass().getName());
			  fail("use case stakeholders not obtained");
		  }
	  }
	  
	  private Resource modelResource;
	  private EmfUrdadModelQueryInterface modelQueryInterface; 
	  
	  private static final String qualifiedUseCaseName = "insurer::claims::Claims::processClaim";

	  private static Logger logger = Logger.getLogger("logger");
	  static
	  {
		  logger.setLevel(Level.ALL);
	  }
}

