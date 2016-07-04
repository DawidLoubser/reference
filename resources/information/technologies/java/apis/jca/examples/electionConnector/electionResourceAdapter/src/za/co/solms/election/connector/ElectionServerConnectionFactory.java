package za.co.solms.election.connector;

import java.io.Serializable;
import javax.resource.Referenceable;
import javax.naming.NamingException;

/**
 * Interface through which clients obtain virtual connections 
 which the application server links to
 * physical managed connections. It is this factory which clients
 * will look up via JNDI when requiring a connection.
 *  
 * @author fritz@solms.co.za
 */
public interface ElectionServerConnectionFactory 
             extends Referenceable, Serializable
{               
  /**
   * 
   * @return a virtual user connection whose coupling to the 
   * underlying managed physical conenctions
   * is managed by the application server.
   *
   * @throws NamingException
   */
   public ElectionServerConnection getConnection() 
                   throws NamingException;
}

