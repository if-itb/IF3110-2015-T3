
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for devoteAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="devoteAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="u_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "devoteAnswer", propOrder = {
    "aId",
    "uId"
})
public class DevoteAnswer {

    @XmlElement(name = "a_id")
    protected int aId;
    @XmlElement(name = "u_id")
    protected int uId;

    /**
     * Gets the value of the aId property.
     * 
     */
    public int getAId() {
        return aId;
    }

    /**
     * Sets the value of the aId property.
     * 
     */
    public void setAId(int value) {
        this.aId = value;
    }

    /**
     * Gets the value of the uId property.
     * 
     */
    public int getUId() {
        return uId;
    }

    /**
     * Sets the value of the uId property.
     * 
     */
    public void setUId(int value) {
        this.uId = value;
    }

}
