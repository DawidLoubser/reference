package za.co.solms.javaee.glassfish.container.embedded;


import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;

/**
 * A little utility class which returns a handle to the embedded EJB container, starting the latter if it is not running. 
 * 
 * @author fritz@solms.co.za
 *
 */
public class EjbContainerUtility
{
	/**
	 * Preventing instantiation of this utility class.
	 */
	private EjbContainerUtility() {}
	
	public static EJBContainer getContainer()
	{
		if (container == null)
		{
			logger.info("***** Starting embedded EJB container");
			container = EJBContainer.createEJBContainer();
		}	
		else
			logger.info("***** Embedded EJB container already running - only returning handle.");

		return container;
	}
	
	private static EJBContainer container;

	private static Logger logger = Logger.getLogger(EjbContainerUtility.class.getName());
}
