
package stackexchangews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="val" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vote", propOrder = {
    "token",
    "mode",
    "idq",
    "val"
})
public class Vote {

    protected String token;
    protected boolean mode;
    protected String idq;
    protected int val;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
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

    /**
     * Gets the value of the val property.
     * 
     */
    public int getVal() {
        return val;
    }

    /**
     * Sets the value of the val property.
     * 
     */
    public void setVal(int value) {
        this.val = value;
    }

}
