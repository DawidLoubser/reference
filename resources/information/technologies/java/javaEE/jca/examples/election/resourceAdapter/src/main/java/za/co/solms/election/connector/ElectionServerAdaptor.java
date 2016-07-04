package za.co.solms.election.connector;

import java.util.logging.Level;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.work.WorkManager;
import javax.resource.ResourceException;
import javax.transaction.xa.XAResource;

import java.util.HashMap;
import java.util.logging.Logger;

/** 
 * The implementation of the ElectionServer resource adaptor 
 * poviding life cycle services (i.e. support for bootstrapping 
 * and bringing down the resource adapter) as well as
 * services for message endpoint setup. 
 * 
 * This class must be a JavaBean..
 * 
 * @author fritz@solms.co.za
 */
public class ElectionServerAdaptor implements ResourceAdapter
{
   /**
    * Get the work manager used by the resource adapter to submit 
    * work requests to the application server.
    * 
    * @return the work manager used by the resource adapter.
    */
   public WorkManager getWorkManager()
   {
      return ctx.getWorkManager();
   }

   /**
    * This service is called by the application server to 
    * bootstrap the resource adapter.
    * 
    * The application server provides the bootstrapping context 
    * which contains the work manager made available to the 
    * resource adapter. The resource adapter may use the 
    * work manager to submit work requests to the application server.
    */
   public void start(BootstrapContext ctx)
      throws ResourceAdapterInternalException
   {
      logger.info("Starting resource adapter.");
      this.ctx = ctx;
      
      /*
       * If significant further work needs to be done or 
       * if polling threads need to be used to poll for 
       * incoming messages, then the resource manager 
       * can obtain a work manager provided
       * by the application server from the 
       * bootstrapping context via
       *
       * WorkManager mgr = ctx.getWorkManager();
       *
       * and submit work requests to it.
       */
   }

   /**
    * Disassociates the work manager such that the resource 
    * adapter will no longer receive any CPU resources 
    * from the application server.
    */
   public void stop()
   {
      logger.info("Stopping resource adapter.");
   }

   /**
    * Called by application server to activate any end points 
    * through which the resource adapter receives any incoming 
    * messages. 
    * If a resource adapter has no invoming connections 
    * (as is the case for this election adapter), this service 
    * is not used.
    *
    * @param endPointFactory the factory which can be used by 
    *          the resource adapter to create new end points.
    * @param activationSpec The specification which contains 
    *          the configuration informaton for the messaging 
    *          endpoint.
    */
   public void endpointActivation(MessageEndpointFactory endpointFactory,
      ActivationSpec activationSpec)
      throws ResourceException
   {
      logger.info("endpointDeactivation for spec " + activationSpec);
     /*
      Specify here the code to activate any endpoints from which the 
      resource adapter receives any incomning messages.
      */
   }

   /**
    * Called by application server to deactivate any end points 
    * through which the resource adapter receives any incoming 
    * messages. 
    * If a resource adapter has no invoming connections 
    * (as is the case for this election adapter), this service 
    * is not used.
    *
    * @param endPointFactory the factory which can be used by 
    *        the resource adapter to create new end points.
    * @param activationSpec The specification which contains 
    *        the configuration informaton for the messaging endpoint
    */
   public void endpointDeactivation(MessageEndpointFactory endpointFactory,
      ActivationSpec activationSpec)
   {
      logger.info("endpointDeactivation for spec " + activationSpec);
   }

   /**
    * Provides the application server a handle to the XAResources 
    * (resources enlisted within distributed trandactions).
    * This method is used by application servers on crash recovery to 
    * clean up dangling transactions.
    */
   public XAResource[] getXAResources(ActivationSpec[] specs) 
                           throws ResourceException
   {
      return new XAResource[0];
   }

   private static final Logger logger 
     = Logger.getLogger(ElectionServerAdaptor.class.getName());
   
   private BootstrapContext ctx;
}
