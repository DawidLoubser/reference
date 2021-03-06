
package za.co.example.unitconverter.soap;

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
@WebServiceClient(name = "UnitConverterService", targetNamespace = "http://example.co.za/unitconverter/soap/", wsdlLocation = "file:/Users/dawidl/solmsRoot/resources/information/technologies/java/javaEE/xml/webServices/jax-ws/using/server-standAlone-wsdlFirst/project/wsdl/unitconverter-soap.wsdl")
public class UnitConverterService
    extends Service
{

    private final static URL UNITCONVERTERSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(za.co.example.unitconverter.soap.UnitConverterService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = za.co.example.unitconverter.soap.UnitConverterService.class.getResource(".");
            url = new URL(baseUrl, "file:/Users/dawidl/solmsRoot/resources/information/technologies/java/javaEE/xml/webServices/jax-ws/using/server-standAlone-wsdlFirst/project/wsdl/unitconverter-soap.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/Users/dawidl/solmsRoot/resources/information/technologies/java/javaEE/xml/webServices/jax-ws/using/server-standAlone-wsdlFirst/project/wsdl/unitconverter-soap.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        UNITCONVERTERSERVICE_WSDL_LOCATION = url;
    }

    public UnitConverterService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UnitConverterService() {
        super(UNITCONVERTERSERVICE_WSDL_LOCATION, new QName("http://example.co.za/unitconverter/soap/", "UnitConverterService"));
    }

    /**
     * 
     * @return
     *     returns UnitConverter
     */
    @WebEndpoint(name = "UnitConverterSOAP")
    public UnitConverter getUnitConverterSOAP() {
        return super.getPort(new QName("http://example.co.za/unitconverter/soap/", "UnitConverterSOAP"), UnitConverter.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UnitConverter
     */
    @WebEndpoint(name = "UnitConverterSOAP")
    public UnitConverter getUnitConverterSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://example.co.za/unitconverter/soap/", "UnitConverterSOAP"), UnitConverter.class, features);
    }

}
