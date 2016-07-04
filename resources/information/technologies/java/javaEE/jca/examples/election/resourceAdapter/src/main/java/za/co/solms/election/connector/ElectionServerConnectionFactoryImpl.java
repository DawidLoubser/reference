package za.co.solms.election.connector;

import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.ResourceException;
import javax.naming.NamingException;
import javax.naming.Reference;

import java.util.logging.Logger;

/**
 * An implementation of a factory for virtual 
 * user connections to the EIS.
 *  
 * @author fritz@solms.co.za
 */
public class ElectionServerConnectionFactoryImpl 
              implements ElectionServerConnectionFactory
{
   /**
    * Creates an instance of a connection factory which uses 
    * a provided connection manager and managed (physical) 
    * connection factory in order to create connections compying 
    * with a provided connection request info. 
    *
    * Instances of connection facotories are created by the 
    * application server which also provides the connection 
    * manager.
    */
   ElectionServerConnectionFactoryImpl(ConnectionManager manager,
      ManagedConnectionFactory factory, 
        ElectionServerConnectionRequestInfo requestInfo)
   {
      logger.info
        ("Creating election server connection implementation ...");
      this.manager = manager;
      this.factory = factory;
      this.requestInfo = requestInfo;
      logger.info
        ("Created election server connection factory for request info " 
           + requestInfo);
   }

   /**
    * Returns a virtual user connection to the EIS which 
    * will be linked by the application server
    * to managed physical EIS connections.
    */ 
   public ElectionServerConnection getConnection() 
                 throws NamingException
   {
      logger.info("getConnection");
      ElectionServerConnection connection = null;
      try
      {
         connection = (ElectionServerConnection) 
           manager.allocateConnection(factory, requestInfo);
      }
      catch(ResourceException e)
      {
         logger.info("caught resource exception");
         logger.info(e.toString());
         Object[] elemnts = e.getStackTrace();
         for (Object el:elemnts)
           logger.info(el.toString());
         throw new NamingException("Unable to get Connection: "+e);
      }
      return connection;
   }

   
   public void setReference(Reference reference)
   {
      logger.info("setReference, reference="+reference);
      this.reference = reference;
   }

   public Reference getReference() throws NamingException
   {
      logger.info("getReference");
      return reference;
   }
   
   private static final long serialVersionUID = 100000;
   private transient ConnectionManager manager;
   private transient ManagedConnectionFactory factory;
   private transient ElectionServerConnectionRequestInfo requestInfo;
   private Reference reference;
   
   private static final Logger logger = 
     Logger.getLogger(ElectionServerConnectionFactoryImpl.class.getName());
}
