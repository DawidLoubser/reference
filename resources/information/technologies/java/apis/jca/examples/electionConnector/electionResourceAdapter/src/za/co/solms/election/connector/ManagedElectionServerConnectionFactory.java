package za.co.solms.election.connector;

import java.io.Serializable;
import java.io.File;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Iterator;
import java.security.acl.Group;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.ResourceException;
import javax.security.auth.Subject;

import java.util.logging.Logger;

/**
 * Instances of this class are used by the application server as a
 * factory of both ManagedConnection (i.e. the physical connections
 * to the EIS) and of connection factory instances.
 * The class supports connection pooling by defining methods for
 * matching and creating connections.
 * 
 * This class is implemented as a JavaBeans component enabling
 * the application server to populate bean attributes from 
 * information obtained from, for example, the deploymnet
 * descriptor.
 * 
 * @author fritz@solms.co.za
 */
public class ManagedElectionServerConnectionFactory
   implements javax.resource.spi.ManagedConnectionFactory, 
                      Serializable
{

   public ManagedElectionServerConnectionFactory()
   {
   }

   /**
    * Returns a connection factory providing virtual user connections 
    * for a provided subject
    * and provided connection request information.
    */
   public Object createConnectionFactory() throws ResourceException
   {
      logger.info("createConnectionFactory");
      throw new UnsupportedOperationException
                    ("Cannot be used in unmanaged env");
   }
   
   /**
    * Returns a connection factory providing virtual user connections.
    */
   public Object createConnectionFactory(ConnectionManager cm) 
                   throws ResourceException
   {
      logger.info
        ("creating ConnectionFactory for connection manager " + cm);
      ElectionServerConnectionRequestInfo connectionRequestInfo=null;
      try
      {
        connectionRequestInfo 
          = new ElectionServerConnectionRequestInfo(hostName, port);
      }
      catch (Exception e)
      {
        logger.severe("Unable to construct URL.");
      }
      return new ElectionServerConnectionFactoryImpl
                                                                                                                                                                                                                                                                                                                                                                                                                                   (cm, this, connectionRequestInfo);
   }
   
   /**
    * Returns a connection factory providing managed 
    * physical connections for a provided subject
    * and provided connection request information.
    */
   public ManagedConnection createManagedConnection
            (Subject subject, ConnectionRequestInfo info)
      throws ResourceException
   {
      logger.info("Creating ManagedConnection, subject="
        + subject + ", info="+info);
      ElectionServerConnectionRequestInfo requestInfo 
          = (ElectionServerConnectionRequestInfo) info;
      if( roles != null && roles.size() > 0 )
      {
         validateRoles(subject);
      }
      try
      { 
        logger.info
          ("Now creating managed election server implementation ...");   
        return new ManagedElectionServerConnection(subject, requestInfo);
      }
      catch (Exception e)
      {
        logger.info("Caught exception " + e);
        logger.info("Throwing resource exception");
        throw new ResourceException(e);
      }
   }

   /**
    * Returns a matching managed physical connection for 
    * a provided subject and provided connection request 
    * information from the provided set of physical 
    * connections.
    */
   public ManagedConnection matchManagedConnections
             (Set connectionSet, Subject subject,
              ConnectionRequestInfo info)
                    throws ResourceException
   {
     // All instances will match
      logger.info("matchManagedConnections, connectionSet="
        + connectionSet + ", subject=" + subject + ", info=" + info);
      return (ManagedConnection) connectionSet.iterator().next();
   }
   
   
   public PrintWriter getLogWriter() throws ResourceException
   {
      return null;
   }
   
   public void setLogWriter(PrintWriter out) throws ResourceException
   {
   }
   
   public boolean equals(Object other)
   {
      return super.equals(other);
   }
   
   public int hashCode()
   {
      return super.hashCode();
   }

   /**
    * Returns the name of the device hosting the EIS.
    */
   public String getHostName()
   {
      return hostName;
   }
   
   /**
    * Setting host name of the EIS this connector is connecting to. 
    * This method is used to set the host information from the 
    * deployment descriptor.
    */
   public void setHostName(String hostName)
   {
      this.hostName = hostName;
   }

   /**
    * Returns the port through which the EIS is accessed.
    */
   public int getPort()
   {
      return port;
   }
   
   /**
    * Setting port number of the EIS this connector is connecting to. 
    * This method is used to set the host information from the 
    * deployment descriptor.
    */
   public void setPort(int port)
   {
      this.port = port;
   }
   
   public void setPort(String port)
   {
     this.port = Integer.parseInt(port);
   }

   public String getRoles()
   {
      return roles.toString();
   }
   
   public void setRoles(String roles)
   {
      this.roles = new HashSet<String>();
      StringTokenizer st = new StringTokenizer(roles, ",");
      while( st.hasMoreTokens() )
      {
         String role = st.nextToken();
         this.roles.add(role);
      }
   }   

   private void validateRoles(Subject theSubject)
      throws ResourceException
   {
     /*
      Set subjectGroups = theSubject.getPrincipals(Group.class);
      Iterator iter = subjectGroups.iterator();
      Group roleGrp = null;
      while (iter.hasNext())
      {
         Group grp = (Group) iter.next();
         String name = grp.getName();
         if (name.equals("Roles"))
            roleGrp = grp;
      }
      if( roleGrp == null )
         throw new ResourceException("Subject has not Roles");

      boolean isValid = false;
      iter = roles.iterator();
      while( iter.hasNext() && isValid == false )
      {
         String name = (String) iter.next();
         SimplePrincipal role = new SimplePrincipal(name);
         isValid = roleGrp.isMember(role);
      }
      if( isValid == false )
      {
         String msg = "Authorization failure, subjectRoles="+roleGrp
            + ", requiredRoles="+roles;
         throw new ResourceException(msg);
      }*/
   }
   
   private static final long serialVersionUID = 100000;
   private static Logger logger = Logger.getLogger
      (ManagedElectionServerConnectionFactory.class.getName());

   private String hostName; // values initialized from deployment descriptor
   private int port;
   private Set<String> roles;
   private transient File rootDir;
}
