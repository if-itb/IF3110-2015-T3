
package stackexchangews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for canVote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="canVote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idqa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "canVote", propOrder = {
    "idus",
    "mode",
    "idqa"
})
public class CanVote {

    protected String idus;
    protected boolean mode;
    protected String idqa;

    /**
     * Gets the value of the idus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdus() {
        return idus;
    }

    /**
     * Sets the value of the idus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdus(String value) {
        this.idus = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     */
    public boolean isMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     */
    public void setMode(boolean value) {
        this.mode = value;
    }

    /**
     * Gets the value of the idqa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdqa() {
        return idqa;
    }

    /**
     * Sets the value of the idqa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdqa(String value) {
        this.idqa = value;
    }

}
