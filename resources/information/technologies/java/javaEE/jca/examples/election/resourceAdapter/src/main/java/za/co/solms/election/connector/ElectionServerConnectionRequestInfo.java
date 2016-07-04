package za.co.solms.election.connector;

import javax.resource.spi.ConnectionRequestInfo;

/**
 * Encapsulates the information required to establish a 
 * connection to an election server instance
 *
 * @author fritz@solms.co.za
 */
public class ElectionServerConnectionRequestInfo 
                implements ConnectionRequestInfo
{
  /**
   * Creates an instance encapsulating the host name 
   * and port used to access the EIS.
   */
   public ElectionServerConnectionRequestInfo
              (String hostName, int port)
   {
      this.hostName = hostName;
      this.port = port;
   }
   
   /**
    * Returns the host name of the device hosting 
    * the election server.
    */
   public String getHostName() {return hostName;}
   
   /**
    * Returns the port number through which the 
    * election server is accessed.
    */
   public int getPort() {return port;}

   /**
    * Returns true if the argument object is 
    * another ElectionServerConnectionRequestInfo
    * for the same URL and the same port.
    */
   public boolean equals(Object arg)
   {
      ElectionServerConnectionRequestInfo argRequest 
        = (ElectionServerConnectionRequestInfo) arg;
        
      return (hostName.equals(argRequest.hostName) 
                  && (port == argRequest.port));
   }

   public int hashCode()
   {
      return hostName.hashCode() + port;
   }
   
   public String toString()
   {
      return "RequestInfo: hostName=" + hostName 
                + ", port=" + port;
   }
   
   private String hostName;
   private int port;
}
