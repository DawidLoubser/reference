
package za.co.example.unitconverter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SupportedUnits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SupportedUnits">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valueType" type="{http://example.co.za/unitconverter/}UnitValue"/>
 *         &lt;element name="unit" type="{http://example.co.za/unitconverter/}UnitReference" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedUnits", propOrder = {
    "valueType",
    "unit"
})
public class SupportedUnits {

    @XmlElement(required = true)
    protected UnitValue valueType;
    @XmlElement(required = true)
    protected List<String> unit;

    /**
     * Gets the value of the valueType property.
     * 
     * @return
     *     possible object is
     *     {@link UnitValue }
     *     
     */
    public UnitValue getValueType() {
        return valueType;
    }

    /**
     * Sets the value of the valueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitValue }
     *     
     */
    public void setValueType(UnitValue value) {
        this.valueType = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUnit() {
        if (unit == null) {
            unit = new ArrayList<String>();
        }
        return this.unit;
    }

}
