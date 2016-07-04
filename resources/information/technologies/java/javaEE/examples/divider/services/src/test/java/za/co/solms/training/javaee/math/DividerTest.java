package za.co.solms.training.javaee.math;

import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import za.co.solms.training.javaee.math.Divider.DivideByZeroException;
import static org.junit.Assert.*;

public class DividerTest
{
	@BeforeClass
	public static void initializeAppServer()
	{
		logger.info("***** Starting embedded EJB container");
		namingContext = EJBContainer.createEJBContainer().getContext();
		logger.info("***** Embedded EJB container successfully started");
		
		lookupDividerBean();
	}
	
	public static void lookupDividerBean()
	{
		try
		{
			divider = (Divider)namingContext.lookup("java:global/classes/DividerImpl");
		}
		catch (NamingException e)
		{
			fail("Could not load divider bean");
		}
	}

	@Test
	public void testFloatingPointDivision()
	{
		try
		{
			double result = divider.divide(0.5, 0.75);
			assertEquals(0.666666666666666666666666666667, result, tolerance);
		}
		catch (DivideByZeroException e)
		{
			fail();
		}
	}
	
	@Test
	public void testDivideByZero()
	{
		try
		{
			divider.divide(1,0);
			fail();
		}
		catch (DivideByZeroException e)
		{
			// expected behaviour
		}
	}
	
	private static Divider divider;
	
	private static double tolerance = 1e-10;
	private static Context namingContext;

	private static Logger logger = Logger.getLogger(DividerTest.class.getName());
}
