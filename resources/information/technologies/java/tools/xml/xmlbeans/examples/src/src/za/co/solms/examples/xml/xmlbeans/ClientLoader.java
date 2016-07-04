package za.co.solms.examples.xml.xmlbeans;
import java.io.*;
import org.apache.xmlbeans.XmlException;
import my.namespace.*;

/** Small program to parse a clients XML document
 * (in the "http://my/namespace" name space)
 */
public class ClientLoader
{

  public static void main(String[] args) throws IOException, XmlException
  {
    if (args.length < 1)
    {
      System.err.println("Expected: <clientXMLFile>");
      System.exit(1);
    }

    // Create a client document
    ClientDocument document = 
      ClientDocument.Factory.parse( new File( args[0] ));
    
    // Display client details
    Client client = document.getClient();
    System.out.println("Client: " + client.getName());
    for (Account acc : client.getAccountArray())
    {
      System.out.println("Account #" + acc.getAccountNumber() 
          + "\t" + acc.getBalance());
    }
  }
}
