package za.co.solms.examples.xml.xmlbeans;
import java.io.IOException;
import java.math.BigInteger;
import my.namespace.*;

/** A small demonstration of creating a document with XMLBeans */
public class XMLBeansCreateTest
{
  public static void main(String[] args) throws IOException
  {
    // Create a client instance
    Client client = Client.Factory.newInstance();
    client.setName("James Dean");
    
    // Add a new account. Account is abstract, so we need to add it, and
    // indicate what specific type we want to use
    Account acc = client.addNewAccount();
    ChequeAccount cheque = (ChequeAccount)acc.changeType( ChequeAccount.type );
    cheque.setAccountNumber( BigInteger.valueOf( 99887766 ) );
    cheque.setChequeFee( 12.55 );

    // Serialize it as a document (to stdout)
    ClientDocument clientDoc = ClientDocument.Factory.newInstance();
    clientDoc.setClient( client );
    clientDoc.save( System.out );
  }
}
