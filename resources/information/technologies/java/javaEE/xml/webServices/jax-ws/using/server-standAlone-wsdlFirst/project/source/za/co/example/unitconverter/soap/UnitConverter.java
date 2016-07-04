package za.co.example.unitconverter.soap;

import javax.jws.*;
import javax.jws.soap.*;
import javax.xml.bind.annotation.*;
import za.co.example.unitconverter.*;

@WebService(name = "UnitConverter", targetNamespace = "http://example.co.za/unitconverter/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UnitConverter 
{


    /**
     * Converts the given Scalar (value with a unit and magnitude) to the desired unit 
     * @throws UnsupportedUnit If an unsupported unit is referenced
     */
    @WebMethod
    @WebResult(name = "unitConversionResponse", targetNamespace = "http://example.co.za/unitconverter/", partName = "body")
    public UnitConversionResponse convert(
        @WebParam(name = "unitConversionRequest", targetNamespace = "http://example.co.za/unitconverter/", partName = "body")
        UnitConversionRequest body)
        throws UnsupportedUnit
    ;

    /**
     * Queries the units supported by the implementation
     */
    @WebMethod
    @WebResult(name = "supportedUnitsResponse", targetNamespace = "http://example.co.za/unitconverter/", partName = "body")
    public SupportedUnitsResponse getSupportedUnits(
        @WebParam(name = "supportedUnitsRequest", targetNamespace = "http://example.co.za/unitconverter/", partName = "body")
        SupportedUnitsRequest body);

}
