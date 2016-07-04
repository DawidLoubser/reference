package za.co.solms.examples.jaxws;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import org.junit.Test;

public class SerializationTest
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
		
		// Serialize it to file
		File temp = File.createTempFile("thingContainingEndpointRef", "serialized");
		ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(temp));
		oos.writeObject( ar );
		oos.close();
		
		// Read it back in
		ObjectInputStream ois = new ObjectInputStream( new FileInputStream(temp));
		ThingWithEndpointReference ar2 = (ThingWithEndpointReference)ois.readObject();
		ois.close();
		
		// Get endpoint reference, which should automatically be re-constructed from
		// persisted state
		EndpointReference ref2 = ar2.getServiceReference();
		
		// We can't make any abstract assertions regarding EndpointReference, other than 
		// that it's not null, or by inspecting an XML serialization to look at the
		// details
		assertNotNull( ref2 );
		
//		We could go ahead and create a proxy to the service as follows:
//		MyService service = ref2.getPort(MyService.class, null);
	}
}