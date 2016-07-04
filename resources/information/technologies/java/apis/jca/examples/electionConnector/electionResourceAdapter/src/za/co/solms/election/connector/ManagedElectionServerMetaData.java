package za.co.solms.election.connector;

import javax.resource.spi.ManagedConnectionMetaData;
import javax.resource.ResourceException;

/**
 * The ManagedConnectionMetaData interface provides 
 * information about the underlying EIS instance 
 * associated with a ManagedConnection instance. 
 * An application server uses this information to 
 * get runtime information about a connected EIS instance.
 * 
 * @author fritz.solms.co.za
 */
public class ManagedElectionServerMetaData 
                implements ManagedConnectionMetaData
{
    public ManagedElectionServerMetaData()
    {
    }

    public String getEISProductName() 
                      throws ResourceException
    {
       return "Election Server";
    }

    public String getEISProductVersion() 
                       throws ResourceException
    {
       return "Election Server Version 1.0";
    }

    public int getMaxConnections() 
                       throws ResourceException
    {
       return 100;
    }

    public String getUserName() 
                        throws ResourceException
    {
       return "";
    }
}
