
package za.co.solms.example.simple.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "MusicExpertService", targetNamespace = "http://solms.co.za/example/simple/soap", wsdlLocation = "file:/Users/dawidl/solmsRoot/resources/information/technologies/java/javaEE/xml/webServices/jax-ws/examples/first/src/wsdl/musicExpert-soap.wsdl")
public class MusicExpertService
    extends Service
{

    private final static URL MUSICEXPERTSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(za.co.solms.example.simple.soap.MusicExpertService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = za.co.solms.example.simple.soap.MusicExpertService.class.getResource(".");
            url = new URL(baseUrl, "file:/Users/dawidl/solmsRoot/resources/information/technologies/java/javaEE/xml/webServices/jax-ws/examples/first/src/wsdl/musicExpert-soap.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/Users/dawidl/solmsRoot/resources/information/technologies/java/javaEE/xml/webServices/jax-ws/examples/first/src/wsdl/musicExpert-soap.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        MUSICEXPERTSERVICE_WSDL_LOCATION = url;
    }

    public MusicExpertService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MusicExpertService() {
        super(MUSICEXPERTSERVICE_WSDL_LOCATION, new QName("http://solms.co.za/example/simple/soap", "MusicExpertService"));
    }

    /**
     * 
     * @return
     *     returns MusicExpert
     */
    @WebEndpoint(name = "MusicExpertSOAP")
    public MusicExpert getMusicExpertSOAP() {
        return super.getPort(new QName("http://solms.co.za/example/simple/soap", "MusicExpertSOAP"), MusicExpert.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MusicExpert
     */
    @WebEndpoint(name = "MusicExpertSOAP")
    public MusicExpert getMusicExpertSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://solms.co.za/example/simple/soap", "MusicExpertSOAP"), MusicExpert.class, features);
    }

}
