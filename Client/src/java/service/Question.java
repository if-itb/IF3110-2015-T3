
package service;

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
 * &lt;complexType name="question">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qtopic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qcontent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="votes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="answer_count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="created_at" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "question", propOrder = {
    "qid",
    "name",
    "email",
    "qtopic",
    "qcontent",
    "votes",
    "answerCount",
    "createdAt"
})
public class Question {

    protected int qid;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String qtopic;
    @XmlElement(required = true)
    protected String qcontent;
    protected int votes;
    @XmlElement(name = "answer_count")
    protected int answerCount;
    @XmlElement(name = "created_at", required = true)
    protected String createdAt;

    /**
     * Gets the value of the qid property.
     * 
     */
    public int getQid() {
        return qid;
    }

    /**
     * Sets the value of the qid property.
     * 
     */
    public void setQid(int value) {
        this.qid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the qtopic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQtopic() {
        return qtopic;
    }

    /**
     * Sets the value of the qtopic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQtopic(String value) {
        this.qtopic = value;
    }

    /**
     * Gets the value of the qcontent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQcontent() {
        return qcontent;
    }

    /**
     * Sets the value of the qcontent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQcontent(String value) {
        this.qcontent = value;
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
     * Gets the value of the answerCount property.
     * 
     */
    public int getAnswerCount() {
        return answerCount;
    }

    /**
     * Sets the value of the answerCount property.
     * 
     */
    public void setAnswerCount(int value) {
        this.answerCount = value;
    }

    /**
     * Gets the value of the createdAt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the value of the createdAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedAt(String value) {
        this.createdAt = value;
    }

}
