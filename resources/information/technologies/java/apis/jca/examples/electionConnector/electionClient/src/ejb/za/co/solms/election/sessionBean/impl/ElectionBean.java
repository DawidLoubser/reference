package za.co.solms.election.sessionBean.impl;

import java.util.logging.Level;
import javax.ejb.Stateless;

import za.co.solms.election.sessionBean.contract.ElectionRemote;
import za.co.solms.election.sessionBean.contract.ElectionLocal;

import javax.naming.InitialContext;
import za.co.solms.election.connector.ElectionServerConnectionFactory;
import za.co.solms.election.connector.ElectionServerConnection;

/**
 * A session bean publishing the EIS services available via
 * the resource adapter.
 *
 * @author fritz@solms.co.za
 */
@Stateless
public class ElectionBean 
               implements ElectionLocal, ElectionRemote
{
  
  /** Creates a new instance of ElectionBean */
  public ElectionBean() throws javax.naming.NamingException
  {
    logger.setLevel(Level.INFO);
    connection = getConnection();
  }
  
  /**
   * Adds a number of votes to a party.
   */
  public void addVotes(String party, int numVotes) 
  {
    logger.info("Sending addVotes request connector ...");
    connection.addVotes(party, numVotes);
  }
  
  /**
   * Retrieves the number of votes for a party.
   */
  public int getVotes(String party) 
  {
    logger.info("Retrieving votes for " + party);
    return connection.getVotes(party);
  }
  
  /**
   * Establishes a connection to the EIS as provided
   * by the resource adapter.
   */
  private ElectionServerConnection getConnection() 
                  throws javax.naming.NamingException
  {
    logger.info("looking up naming context");
    InitialContext context = new InitialContext();
    logger.info
      ("looking up election server connection factory ...");
    ElectionServerConnectionFactory connectionFactory 
        = (ElectionServerConnectionFactory)context.lookup
            ("java:ElectionConnector");
    logger.info("Getting connection from factory ...");
    connection = connectionFactory.getConnection();
    logger.info("Got connection, returning it...");
    return connection;
  }
  
  private ElectionServerConnection connection;
  
  private final java.util.logging.Logger logger
      = java.util.logging.Logger.getLogger
                   (ElectionBean.class.getName());
}
