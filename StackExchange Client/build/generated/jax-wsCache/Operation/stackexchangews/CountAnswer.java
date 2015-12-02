
package stackexchangews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for countAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="countAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countAnswer", propOrder = {
    "idq"
})
public class CountAnswer {

    protected String idq;

    /**
     * Gets the value of the idq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdq() {
        return idq;
    }

    /**
     * Sets the value of the idq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdq(String value) {
        this.idq = value;
    }

}
