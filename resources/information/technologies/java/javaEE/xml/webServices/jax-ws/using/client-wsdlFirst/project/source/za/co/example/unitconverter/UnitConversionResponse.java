
package za.co.example.unitconverter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitConversionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnitConversionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://example.co.za/unitconverter/}UnitValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitConversionResponse", propOrder = {
    "result"
})
public class UnitConversionResponse {

    @XmlElement(required = true)
    protected UnitValue result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link UnitValue }
     *     
     */
    public UnitValue getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitValue }
     *     
     */
    public void setResult(UnitValue value) {
        this.result = value;
    }

}
