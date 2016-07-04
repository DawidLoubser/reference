
package za.co.solms.example.simple;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                         A request to a music classification service to classify 
 *                         the song indicated by the enclosed artist and song names.
 *                     
 * 
 * <p>Java class for ClassifySongRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClassifySongRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="artistName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="songName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassifySongRequest", propOrder = {
    "artistName",
    "songName"
})
public class ClassifySongRequest {

    @XmlElement(required = true)
    protected String artistName;
    @XmlElement(required = true)
    protected String songName;

    /**
     * Gets the value of the artistName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Sets the value of the artistName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtistName(String value) {
        this.artistName = value;
    }

    /**
     * Gets the value of the songName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Sets the value of the songName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSongName(String value) {
        this.songName = value;
    }

}
