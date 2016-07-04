package za.co.solms.examples.jaxws;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.EndpointReference;

/**
 * A JavaBean which is
 * - serializable (to bytes, such as sending over the network to an EJB) 
 * - map-able to XML (such as sending as a web service message via JAX-WS)
 * - storable as an JPA entity (such as storing in a relational database via an ORM) 
 * 
 * containing a WS-Addressing Endpoint Reference.
 * 
 * Endpoint references are sharable, persistent references to web services, and useful
 * to, for example, pass a call-back address from a client to a service over the
 * network, or any other mechanism (such as storing in a file).
 * 
 * @author Dawid Loubser (dawidl@solms.co.za)
 * @version 0.1 (2010 Jun 30)
 */

// Annotate for XML mapping
@XmlType
@XmlRootElement(name="containsEndpointReference", namespace="http://solms.co.za/example")
@XmlAccessorType(XmlAccessType.PROPERTY)

// Annotate for JPA Storage
@Entity
public class ThingWithEndpointReference implements Serializable
{
	public ThingWithEndpointReference(){}
	
	public ThingWithEndpointReference(EndpointReference serviceReference)
	{
		setServiceReference(serviceReference);
	}

	/** Returns a reference to "the service" */
	@XmlElement(name="serviceRef", namespace="http://solms.co.za/example")
	public EndpointReference getServiceReference()
	{
		// Implementation: If this instance was byte-serialized, the original
		// endpoint would have been lost
		if (serviceReference == null && _serviceReference_serialized != null)
		{
			// We Re-construct the endpoint reference from the XML
			serviceReference = EndpointReference.readFrom( 
					new StreamSource( 
							new StringReader(_serviceReference_serialized)));
		}
		return serviceReference;
	}
	
	
	/** Sets the reference to "the service" */
	public void setServiceReference( EndpointReference serviceReference )
	{
		this.serviceReference = serviceReference;
		// Store byte-serializable version (as a String rendering of the XML) 
		// (could also be done during actual serialization, but this method
		// will work well for storing as JPA entity, etc as well)
		if (serviceReference != null)
		{
			StringWriter stringWriter = new StringWriter();
			StreamResult result = new StreamResult(stringWriter);
			serviceReference.writeTo(result);
			_serviceReference_serialized = stringWriter.getBuffer().toString();
		}
		else
		{
			_serviceReference_serialized = null;
		}
	}

	
	/** Returns the system-generated identifier for this thing if it's stored
	 * as a JPA entity */
	public long getId()
	{
		return id;
	}
	
	
	// W3C Emdpoint Reference: Not serializable, but map-able to XML with JAXB
	@Transient // Do not store with JPA
	private transient EndpointReference serviceReference;
	
	// Serializable XML rendering of the endpoint reference, which
	// should not be mapped to/from XML with JAXB
	@Column(name="SERVICE_REF") // Store via JPA
	private String _serviceReference_serialized;

	// Unique identifier for persistence, auto-generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
}