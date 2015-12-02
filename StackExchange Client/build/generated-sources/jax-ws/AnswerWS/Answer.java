
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
 *         &lt;element name="AID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Votes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="QID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "aid",
    "userID",
    "content",
    "votes",
    "qid",
    "dateTime"
})
public class Answer {

    @XmlElement(name = "AID")
    protected int aid;
    @XmlElement(name = "UserID")
    protected int userID;
    @XmlElement(name = "Content", required = true)
    protected String content;
    @XmlElement(name = "Votes")
    protected int votes;
    @XmlElement(name = "QID")
    protected int qid;
    @XmlElement(name = "DateTime", required = true)
    protected String dateTime;

    /**
     * Gets the value of the aid property.
     * 
     */
    public int getAID() {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     */
    public void setAID(int value) {
        this.aid = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     */
    public void setUserID(int value) {
        this.userID = value;
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
     * Gets the value of the votes property.
     * 
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Sets the value of the votes property.
     * 
     */
    public void setVotes(int value) {
        this.votes = value;
    }

    /**
     * Gets the value of the qid property.
     * 
     */
    public int getQID() {
        return qid;
    }

    /**
     * Sets the value of the qid property.
     * 
     */
    public void setQID(int value) {
        this.qid = value;
    }

    /**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTime(String value) {
        this.dateTime = value;
    }

}
