
package za.co.example.unitconverter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SupportedUnitsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SupportedUnitsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supportdUnits" type="{http://example.co.za/unitconverter/}SupportedUnits" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedUnitsResponse", propOrder = {
    "supportdUnits"
})
public class SupportedUnitsResponse {

    @XmlElement(required = true)
    protected List<SupportedUnits> supportdUnits;

    /**
     * Gets the value of the supportdUnits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportdUnits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportdUnits().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupportedUnits }
     * 
     * 
     */
    public List<SupportedUnits> getSupportdUnits() {
        if (supportdUnits == null) {
            supportdUnits = new ArrayList<SupportedUnits>();
        }
        return this.supportdUnits;
    }

}
