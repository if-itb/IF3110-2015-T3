
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addNewAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addNewAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="u_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="q_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addNewAnswer", propOrder = {
    "uId",
    "content",
    "qId"
})
public class AddNewAnswer {

    @XmlElement(name = "u_id")
    protected int uId;
    protected String content;
    @XmlElement(name = "q_id")
    protected int qId;

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

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the qId property.
     * 
     */
    public int getQId() {
        return qId;
    }

    /**
     * Sets the value of the qId property.
     * 
     */
    public void setQId(int value) {
        this.qId = value;
    }

}
