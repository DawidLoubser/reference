package za.co.solms.election.connector;

import java.util.logging.Level;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import java.util.logging.Logger;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;

/**
 * This class provides an implementation of the client's 
 * interface to a connection provided and managed by the 
 * application server. 
 * It delegates any requests for business services 
 * from the EIS to the underlyng physical (managed) 
 * connection.
 * 
 * Instances of this class are instances of a virtual 
 * client connection which can be realized by different 
 * underlying physical connections over time. 
 *
 * It maintains a handle to the physical managed connection 
 * (managed by the application server).
 *
 * When a user closes a connection, the virtual user connection 
 * is disassociated from the physical managed connection 
 * which may be used by another virtual connection.
 * 
 * @author  fritz@solms.co.za
 */
public class ElectionServerConnectionImpl 
               implements ElectionServerConnection
{
   /** 
    * Creates new virtual connection associated to a 
    * physical (managed) connection 
    */
   public ElectionServerConnectionImpl
             (ManagedElectionServerConnection managedConnection)
   {
     logger.info("Constructing new virtual connection:");     
     this.managedConnection = managedConnection;
   }

   /**
    * Associates a new physical (managed) connection with 
    * this virtual connection.
    */
   protected void associateManagedConnection
           (ManagedElectionServerConnection newManagedConnection)
   {
     logger.info("Associating a new managed connection" +
              " with this virtual connection.");     

     managedConnection.removeConnectionEventListener
                         (connectionEventListener);

     managedConnection = newManagedConnection;
     
     managedConnection.addConnectionEventListener
                           (connectionEventListener);
   }

   /**
    * Disassociated this virtual connection from the 
    * physical (managed) connection.
    */
   public void disassociateManagedConnection() 
   {
     logger.info("Disassociating the managed connection " +
              "from this virtual connection.");     
     managedConnection = null;
   }
   
   /**
    * Close this virtual connection, disassociating it from 
    * the physical (managed) connection.
    */
   public void close()
   {
     logger.log(Level.FINEST, "Closing virtual connection.");     
     managedConnection.close();
   }
   
   /**
    * Add a number of votes for a particular party.
    * This virtual user connection forwards the request 
    * to the physical managed connection.
    */
   public void addVotes(String party, int numVotes)
   {
     logger.log(Level.FINEST, "Adding " + numVotes 
                  + " to " + party);     
     managedConnection.addVotes(party, numVotes);     
   }
   
   /**
    * Retrieves the number of votes from the EIS 
    * (the election server) for a particular party.
    * This virtual user connection forwards the request 
    * to the physical managed connection.
    */
   public int getVotes(String party)
   {
     logger.info("Retrieving the number of votes for " 
                 + party);     
     return managedConnection.getVotes(party);     
   }
   
   private ManagedElectionServerConnection managedConnection;
   
   private ConnectionEventListener connectionEventListener
       = new ConnectionEventListener()
     {
       public void connectionClosed
                     (ConnectionEvent connectionEvent)
       {
         logger.info("Transaction closed.");
       }
       public void connectionErrorOccurred
                      (ConnectionEvent connectionEvent)
       {
         logger.info("Transaction error.");
       }
       public void localTransactionCommitted
                      (ConnectionEvent connectionEvent)
       {
         logger.info("Transaction Committed.");
       }
       public void localTransactionRolledback
                      (ConnectionEvent connectionEvent)
       {
         logger.info("Transaction rolled back.");
       }
       public void localTransactionStarted
                      (ConnectionEvent connectionEvent)
       {
         logger.info("Transaction started.");
       }
     };
     
   private static final Logger logger 
     = Logger.getLogger(ElectionServerConnectionImpl.class.getName());
}
