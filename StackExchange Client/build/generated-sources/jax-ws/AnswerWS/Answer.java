
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
 * &lt;complexType name="answer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="u_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date_created" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "answer", propOrder = {
    "aId",
    "uId",
    "email",
    "content",
    "vote",
    "dateCreated",
    "qId"
})
public class Answer {

    @XmlElement(name = "a_id")
    protected int aId;
    @XmlElement(name = "u_id")
    protected int uId;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String content;
    protected int vote;
    @XmlElement(name = "date_created", required = true)
    protected String dateCreated;
    @XmlElement(name = "q_id")
    protected int qId;

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

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * Gets the value of the vote property.
     * 
     */
    public int getVote() {
        return vote;
    }

    /**
     * Sets the value of the vote property.
     * 
     */
    public void setVote(int value) {
        this.vote = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateCreated(String value) {
        this.dateCreated = value;
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
