package za.co.solms.examples.jaxws;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import org.junit.Test;

public class XMLBindingTest
{
	/** Tests behaviour of serializing a (non-serializable) endpoint reference - using file,
	 * but this should adequately simulate DB persistence also. */
	@Test
	public void testEndpointReferenceSerialization() throws Exception
	{
		// Create dummy reference (which is not serializable)
		EndpointReference ref = EndpointReference.readFrom( new StreamSource( getClass().getClassLoader().getResourceAsStream("endpointRef.xml")));
		
		// Create an instance containing this reference
		ThingWithEndpointReference ar = new ThingWithEndpointReference();
		ar.setServiceReference(ref);
		
		
		
		// The JAXB context. In addition to our own classes being marshalled, needs 
		// to know about the class of the WS-Addressing endpoint reference.
		JAXBContext context = 
			JAXBContext.newInstance(
					ThingWithEndpointReference.class, 
					ar.getServiceReference().getClass());
		
		// Write to XML
		Marshaller marshaller = context.createMarshaller();
		File tempFile = File.createTempFile("thingContainingEndpointRef", "xml");
		StreamResult streamResult = new StreamResult(tempFile);
		marshaller.marshal(ar, tempFile);
		
		// Read back in
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ThingWithEndpointReference fromXML = unmarshaller.unmarshal( 
				new StreamSource(tempFile), 
				ThingWithEndpointReference.class).getValue();
		
		// Check that we got values back
		assertNotNull(fromXML);
		assertNotNull(fromXML.getServiceReference());

		
//		We could go ahead and create a proxy to the service as follows:
//		MyService service = fromXML.getServiceReference().getPort(MyService.class, null);
	}
}