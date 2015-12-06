
package UserWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNameResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNameResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gettingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNameResponse", propOrder = {
    "gettingName"
})
public class GetNameResponse {

    protected String gettingName;

    /**
     * Gets the value of the gettingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGettingName() {
        return gettingName;
    }

    /**
     * Sets the value of the gettingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGettingName(String value) {
        this.gettingName = value;
    }

}
