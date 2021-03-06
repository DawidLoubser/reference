
package za.co.example.unitconverter.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "unsupportedUnit", targetNamespace = "http://example.co.za/unitconverter/")
public class UnsupportedUnit
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private za.co.example.unitconverter.UnsupportedUnit faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public UnsupportedUnit(String message, za.co.example.unitconverter.UnsupportedUnit faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public UnsupportedUnit(String message, za.co.example.unitconverter.UnsupportedUnit faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: za.co.example.unitconverter.UnsupportedUnit
     */
    public za.co.example.unitconverter.UnsupportedUnit getFaultInfo() {
        return faultInfo;
    }

}
