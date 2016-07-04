package za.co.solms.training.java.apis.jaxb.helloWorld;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import static org.junit.Assert.*;

import org.xml.sax.SAXException;

import za.co.solms.training.java.apis.jaxb.helloworld.Message;
import za.co.solms.training.java.apis.jaxb.helloworld.ObjectFactory;
import za.co.solms.training.java.apis.jaxb.helloworld.Person;


/**
 * 
 * @author fritz@solms.co.za
 *
 */
public class HelloWorldTest
{
	@org.junit.Test
	public void testGenerateXml()
	{
		Message message = createMessage();
		try
		{
			String xmlString = generateXmlDoc(message);
			
			System.out.println("Generated XML:");
			System.out.println(xmlString);
			
			Message demarshalledMessage = demarshallMessage(xmlString);
			
			System.out.println("Demarshalled message: '" + demarshalledMessage.getMessageText() + "'");
			
			assertEquals(message.getMessageText(), demarshalledMessage.getMessageText());
		}   
		catch (JAXBException e)
		{
			fail(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	private static Message demarshallMessage(String xmlString) throws JAXBException
	{
	   JAXBContext jaxbContext = JAXBContext.newInstance(contextPackage);
	   Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	   Reader xmlSource = new StringReader(xmlString);

	   // Unmarshal the XML document
	   JAXBElement<Message> doc = (JAXBElement<Message>)unmarshaller.unmarshal(xmlSource);
		Message message = doc.getValue();
		return message;
	}
	
	private static String generateXmlDoc(Message message) throws JAXBException
	{
	   JAXBContext jaxbContext = JAXBContext.newInstance(contextPackage);
	   Marshaller marshaller = jaxbContext.createMarshaller();
	   marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
	   // Perform validation on marshalling using our schema
	   marshaller.setSchema( getHelloWorldSchema() );
	   
	   StringWriter writer = new StringWriter();
	   // Marshal to string
	   marshaller.marshal( new ObjectFactory().createMessage(message), writer);
	
	   // Display output
	   return writer.toString();
	}

	private static Message createMessage()
	{
		// Create message
		Message message = new Message();
		message.setMessageText("Hello world.");
		message.setLanguage("en");
		Person sender = new Person();
		sender.setFirstname("Tandi");
		sender.setSurname("Naidoo");
		message.setSender(sender);
		return message;
	}
	
   private static Schema getHelloWorldSchema()
   {
   	Schema schema = null;
		
   	ClassLoader classLoader = HelloWorldTest.class.getClassLoader();
   	try
   	{
   		SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
   		schema = sf.newSchema( classLoader.getResource("schemas/helloWorld.xsd") );
   	}
   	catch (SAXException e)
   	{
   		fail("Failed to read accounts schema" + e);
   	}
   	return schema;
   }
   
   private static final String contextPackage = "za.co.solms.training.java.apis.jaxb.helloworld";
}
