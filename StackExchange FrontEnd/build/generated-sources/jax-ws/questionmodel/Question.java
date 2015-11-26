
package questionmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for question complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="question"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="q_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="u_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="q_topic" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="q_content" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="q_date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "question", propOrder = {
    "qId",
    "uId",
    "qTopic",
    "qContent",
    "qDate"
})
public class Question {

    @XmlElement(name = "q_id")
    protected int qId;
    @XmlElement(name = "u_id")
    protected int uId;
    @XmlElement(name = "q_topic", required = true)
    protected String qTopic;
    @XmlElement(name = "q_content", required = true)
    protected String qContent;
    @XmlElement(name = "q_date", required = true)
    protected String qDate;

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
     * Gets the value of the qTopic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQTopic() {
        return qTopic;
    }

    /**
     * Sets the value of the qTopic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQTopic(String value) {
        this.qTopic = value;
    }

    /**
     * Gets the value of the qContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQContent() {
        return qContent;
    }

    /**
     * Sets the value of the qContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQContent(String value) {
        this.qContent = value;
    }

    /**
     * Gets the value of the qDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQDate() {
        return qDate;
    }

    /**
     * Sets the value of the qDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQDate(String value) {
        this.qDate = value;
    }

}
