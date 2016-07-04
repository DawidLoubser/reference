package za.co.solms.examples.jaxws;

import java.util.Properties;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.EndpointReference;
import org.apache.openejb.api.LocalClient;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

/** Tests that we can store*/
@LocalClient
public class JPAEntityTest
{
	/** Sets up the embedded EJB container before every test that is run */
	@Before
	public void setUpEJBContainer() throws Exception
	{
		// Configure JNDI context (embedded container)
      Properties p = new Properties();
      p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
      
      // Configure embedded database (matches unit name in persistence.xml)
      p.put("db", "new://Resource?type=DataSource");
      p.put("db.JdbcDriver", "org.hsqldb.jdbcDriver");
      p.put("openejb.tempclassloader.skip", "annotations");

      // Create container
      InitialContext initialContext = new InitialContext(p);

      // Bind this test to context in order for dependencies to be injected
      initialContext.bind("inject", this);
	}
	
	
	/** Tests that we can persist and retrieve our entity containing
	 * a WS-Addressing EndpointReference. */
	@Test
	public void testStoreAsEntity() throws Exception
	{
		// Create entity (with endpoint reference from XML file)
		ThingWithEndpointReference cer = new ThingWithEndpointReference();
		cer.setServiceReference( 
				EndpointReference.readFrom( new StreamSource( getClass().getClassLoader()
						.getResourceAsStream("endpointRef.xml"))));
		

		// Store entity
		userTransaction.begin();
		entityManager.persist( cer );
		userTransaction.commit();
		
		// Retrieve from database
		long id = cer.getId(); // Generated during persist()
		ThingWithEndpointReference fromDB = 
			entityManager.find(ThingWithEndpointReference.class, id);
		
		// Assert that entity, and enclosed endpoint refeence, exist
		assertNotNull(fromDB);
		assertNotNull(fromDB.getServiceReference());
	}
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
   private UserTransaction userTransaction;
}