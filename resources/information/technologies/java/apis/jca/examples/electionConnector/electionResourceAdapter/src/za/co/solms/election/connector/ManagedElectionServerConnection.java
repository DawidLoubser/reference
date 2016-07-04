package za.co.solms.election.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.resource.spi.ConnectionEvent;
import javax.resource.ResourceException;
import javax.transaction.xa.XAResource;
import javax.security.auth.Subject;

import java.util.logging.Logger;

/**
 * A ManagedConnection representing a physical connection 
 * to the underlying EIS providing the application server 
 * the means to
 * <ul>
 *   <li>create physical connections,</li>
 *   <li>associate physical connections 
 *       to the managed connection, </li>
 *   <li>receive state events (e.g. close events) 
 *       from physical connections,</li>
 *   <li>
 *     provide access to the  XAResource interface is used 
 *     by the transaction manager to associate and dissociate 
 *     a transaction with the underlying EIS resource manager 
 *     instance and to perform two-phase commit, and to
 *   </li> 
 *   <li>
 *     provide access to the LocalTransaction interface is used 
 *     by the application server to manage local transactions, 
 *     i.e. transactions which only have only enlisted local 
 *     resources.     
 *   </li>
 * </ul>  
 *
 * @author  Fritz Solms
 */
public class ManagedElectionServerConnection 
                implements ManagedConnection
{

   /** 
    * Creates new physical connection to an instance 
    * of an election server.
    */
   public ManagedElectionServerConnection(Subject subject,
      ElectionServerConnectionRequestInfo connectionRequestInfo)
       throws UnknownHostException, IOException
   {
     logger.log(Level.FINEST, 
       "Creating virtual connection for request info " 
         + connectionRequestInfo);
      
      socket = new Socket
          (InetAddress.getByName
            (connectionRequestInfo.getHostName()), 
             connectionRequestInfo.getPort());

      in  = new BufferedReader(new InputStreamReader
                                    (socket.getInputStream()));
      out = new PrintStream(socket.getOutputStream());
   }
   
   /**
    * Creates a new virtual connection for the underlying 
    * physical connection represented 
    * by the ManagedConnection instance.
    */
   public Object getConnection
                  (Subject subject, ConnectionRequestInfo info)
      throws ResourceException
   {
     logger.log(Level.FINEST, ("Getting a connection for subject " 
       + subject + " and info " + info));
      if( virtualConnection == null )
         virtualConnection = new ElectionServerConnectionImpl(this);
      return virtualConnection;
   }

   /**
    * Adding a new connection event listener.
    */
   public void addConnectionEventListener
                 (ConnectionEventListener connectionEventListener)
   {
     logger.log(Level.FINEST, "Adding ConnectionEventListener "
         + connectionEventListener);
      listeners.add(connectionEventListener);
   }
   
   /**
    * Removing a specified connection event listener.
    */
   public void removeConnectionEventListener
                 (ConnectionEventListener connectionEventListener)
   {
     logger.log(Level.FINEST, "Removing ConnectionEventListener " 
                + connectionEventListener);
      listeners.remove(connectionEventListener);
   }

   /**
    * Associates a new virtual connection to this physical connection.
    */
   public void associateConnection(Object obj) throws ResourceException
   {
     logger.log(Level.FINEST, "Associating physical connection " 
       + obj + " to this virtual connection.");
      try
      {    
        virtualConnection = (ElectionServerConnectionImpl) obj;
        virtualConnection.associateManagedConnection(this);
      }
      catch (ClassCastException e)
      {
        throw new ResourceException
          ("Virtual connection not an ElectionServerConnectionImpl");
      }
   }
   
   /**
    * Requests the EIS to add a number of votes for a party.
    *
    * The service marshalls the request onto the EIS protocol,
    * sends the request via the socket connection to the EIS, 
    * waits for a response, and demarshalls it.
    */
   public void addVotes(String party, int numVotes)
   {
     logger.log(Level.FINEST, "Adding " + numVotes + " to " + party);     
     
     String request="addVotes|" + party + "|" + numVotes;
     
     try
     {
       out.println(request);
       String response = in.readLine();
     }
     catch (IOException e)
     {
       notifyOfError(e);
     }
   }
   
   /**
    * Requests the number of votes for a party from the EIS.
    *
    * The service marshalls the request onto the EIS protocol,
    * sends the request via the socket connection to the EIS, 
    * waits for a response, and demarshalls it.
    */
   public int getVotes(String party)
   {
     logger.log(Level.FINEST, "Retrieving votes for " + party);     
     
     String request="getVotes|" + party;
     
     try
     {
       out.println(request);
       String response = in.readLine();
       return Integer.parseInt(response);
     }
     catch (Exception e)
     {
       notifyOfError(e);
       return 0;
     }
   }
   
   private void notifyOfError(Exception e)
   {
     logger.log(Level.ALL, e.toString());
     ConnectionEvent connectionEvent 
       = new ConnectionEvent
              (this, ConnectionEvent.CONNECTION_ERROR_OCCURRED);
     connectionEvent.setConnectionHandle(virtualConnection);
     fireConnectionEvent(connectionEvent);
   }
   
   /**
    * Called by application server to force any cleanup 
    * by this physical connection instance.
    */
   public void cleanup() throws ResourceException
   {
      logger.info("cleanup");
   }
   
   /**
    * Closes the underlying physical connection.
    */
   public void destroy() throws ResourceException
   {
      logger.info("Closing physical connection.");
      try
      {    
        socket.close();
      } catch (Exception e) {}  
   }

   /**
    * Returns the local transaction for this 
    * resource manager instance (if any).
    */
   public LocalTransaction getLocalTransaction() 
            throws ResourceException
   {
      logger.info("getLocalTransaction");
      return null;
   }
   
   /**
    * Returns the meta data for the managed connection.
    */
   public ManagedConnectionMetaData getMetaData() 
             throws ResourceException
   {
      logger.info("getMetaData");
      return new ManagedElectionServerMetaData();
   }
   
   public XAResource getXAResource() 
            throws ResourceException
   {
      logger.info("getXAResource");
      return null;
   }

   public PrintWriter getLogWriter() 
              throws ResourceException
   {
      return null;
   }
   public void setLogWriter(PrintWriter out) 
                   throws ResourceException
   {
   }

   protected void close()
   {
      ConnectionEvent ce 
        = new ConnectionEvent
            (this, ConnectionEvent.CONNECTION_CLOSED);
      ce.setConnectionHandle(virtualConnection);
      fireConnectionEvent(ce);
   }

   /**
    * Distributes connection events to all connection 
    * event listeners.
    */
   protected void fireConnectionEvent(ConnectionEvent evt)
   {
      for(int i=listeners.size()-1; i >= 0; i--)
      {
         ConnectionEventListener listener 
           = (ConnectionEventListener) listeners.get(i);
         if(evt.getId() == ConnectionEvent.CONNECTION_CLOSED)
            listener.connectionClosed(evt);
         else if(evt.getId() == 
             ConnectionEvent.CONNECTION_ERROR_OCCURRED)
                  listener.connectionErrorOccurred(evt);
      }
   }
   
   private Socket socket;
   private BufferedReader in;
   private PrintStream out;
   
   private ArrayList<ConnectionEventListener> listeners 
        = new ArrayList<ConnectionEventListener>();
   private ElectionServerConnectionImpl virtualConnection;
   
   private static Logger logger 
      = Logger.getLogger
           (ManagedElectionServerConnection.class.getName());
}
