package za.co.solms.example;

import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.junit.Test;
import org.xml.sax.SAXException;
import za.co.solms.example.accounts.ChequeAccount;
import za.co.solms.example.accounts.Client;
import za.co.solms.example.accounts.CreditAccount;
import za.co.solms.example.accounts.ObjectFactory;

/**
 * Illustrates XML marshalling
 */
public class JAXBTest 
{
	@Test
	public void testGenerateXML() throws Exception
	{
		// Create some data using the generated classes
		Client client = new Client();
		client.setName("Jack Black");
		
		ChequeAccount a1 = new ChequeAccount();
		a1.setAccountNumber("1007657643");
		a1.setBalance(100.00);
		a1.setChequeFee(0.75);
		client.getAccount().add( a1 );
		
		CreditAccount a2 = new CreditAccount();
		a2.setAccountNumber("1007657642");
		a2.setBalance(-735.18);
		a2.setMinBalance(-10000.00);
		client.getAccount().add( a2 );
		
		// Using JAXB we marshal the client to XML (in this case, just to a 
		// string - we validate the output during marshalling)
		JAXBContext ctx = JAXBContext.newInstance("za.co.solms.example.accounts");
		Marshaller marshaller = ctx.createMarshaller();
		// Pretty-print output
		marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Perform validation on marshalling using our schema
		marshaller.setSchema( getAccountsSchema() );
		// Marshal to string
		StringWriter writer = new StringWriter();
		marshaller.marshal( new ObjectFactory().createClient(client), writer);
		
		// Display output
		System.out.println( writer.getBuffer() );
	}
	
	
	/** Gets the accounts Schema */
	private Schema getAccountsSchema()
	{
		try
		{
			SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
			return sf.newSchema( getClass().getClassLoader().getResource("schemas/accounts.xsd") );
		}
		catch (SAXException e)
		{
			throw new RuntimeException("Failed to read accounts schema", e);
		}
	}
}