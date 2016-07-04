
package za.co.example.unitconverter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.co.example.unitconverter package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SupportedUnitsRequest_QNAME = new QName("http://example.co.za/unitconverter/", "supportedUnitsRequest");
    private final static QName _UnitConversionRequest_QNAME = new QName("http://example.co.za/unitconverter/", "unitConversionRequest");
    private final static QName _SupportedUnitsResponse_QNAME = new QName("http://example.co.za/unitconverter/", "supportedUnitsResponse");
    private final static QName _UnitConversionResponse_QNAME = new QName("http://example.co.za/unitconverter/", "unitConversionResponse");
    private final static QName _UnsupportedUnit_QNAME = new QName("http://example.co.za/unitconverter/", "unsupportedUnit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.co.example.unitconverter
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SupportedUnits }
     * 
     */
    public SupportedUnits createSupportedUnits() {
        return new SupportedUnits();
    }

    /**
     * Create an instance of {@link SupportedUnitsRequest }
     * 
     */
    public SupportedUnitsRequest createSupportedUnitsRequest() {
        return new SupportedUnitsRequest();
    }

    /**
     * Create an instance of {@link Distance }
     * 
     */
    public Distance createDistance() {
        return new Distance();
    }

    /**
     * Create an instance of {@link Temperature }
     * 
     */
    public Temperature createTemperature() {
        return new Temperature();
    }

    /**
     * Create an instance of {@link UnitConversionRequest }
     * 
     */
    public UnitConversionRequest createUnitConversionRequest() {
        return new UnitConversionRequest();
    }

    /**
     * Create an instance of {@link UnsupportedUnit }
     * 
     */
    public UnsupportedUnit createUnsupportedUnit() {
        return new UnsupportedUnit();
    }

    /**
     * Create an instance of {@link SupportedUnitsResponse }
     * 
     */
    public SupportedUnitsResponse createSupportedUnitsResponse() {
        return new SupportedUnitsResponse();
    }

    /**
     * Create an instance of {@link UnitConversionResponse }
     * 
     */
    public UnitConversionResponse createUnitConversionResponse() {
        return new UnitConversionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupportedUnitsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.co.za/unitconverter/", name = "supportedUnitsRequest")
    public JAXBElement<SupportedUnitsRequest> createSupportedUnitsRequest(SupportedUnitsRequest value) {
        return new JAXBElement<SupportedUnitsRequest>(_SupportedUnitsRequest_QNAME, SupportedUnitsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnitConversionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.co.za/unitconverter/", name = "unitConversionRequest")
    public JAXBElement<UnitConversionRequest> createUnitConversionRequest(UnitConversionRequest value) {
        return new JAXBElement<UnitConversionRequest>(_UnitConversionRequest_QNAME, UnitConversionRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SupportedUnitsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.co.za/unitconverter/", name = "supportedUnitsResponse")
    public JAXBElement<SupportedUnitsResponse> createSupportedUnitsResponse(SupportedUnitsResponse value) {
        return new JAXBElement<SupportedUnitsResponse>(_SupportedUnitsResponse_QNAME, SupportedUnitsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnitConversionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.co.za/unitconverter/", name = "unitConversionResponse")
    public JAXBElement<UnitConversionResponse> createUnitConversionResponse(UnitConversionResponse value) {
        return new JAXBElement<UnitConversionResponse>(_UnitConversionResponse_QNAME, UnitConversionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsupportedUnit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.co.za/unitconverter/", name = "unsupportedUnit")
    public JAXBElement<UnsupportedUnit> createUnsupportedUnit(UnsupportedUnit value) {
        return new JAXBElement<UnsupportedUnit>(_UnsupportedUnit_QNAME, UnsupportedUnit.class, null, value);
    }

}
