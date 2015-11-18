
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for answer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="answer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="a_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="q_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="u_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="a_content" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="a_date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
    "aId",
    "qId",
    "uId",
    "aContent",
    "aDate"
})
public class Answer {

    @XmlElement(name = "a_id")
    protected int aId;
    @XmlElement(name = "q_id")
    protected int qId;
    @XmlElement(name = "u_id")
    protected int uId;
    @XmlElement(name = "a_content", required = true)
    protected String aContent;
    @XmlElement(name = "a_date", required = true)
    protected String aDate;

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
     * Gets the value of the aContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAContent() {
        return aContent;
    }

    /**
     * Sets the value of the aContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAContent(String value) {
        this.aContent = value;
    }

    /**
     * Gets the value of the aDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADate() {
        return aDate;
    }

    /**
     * Sets the value of the aDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADate(String value) {
        this.aDate = value;
    }

}
