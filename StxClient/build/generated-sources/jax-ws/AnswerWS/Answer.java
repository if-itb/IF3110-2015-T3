
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
 *         &lt;element name="Ans_id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Ans_qid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Ans_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Ans_email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Ans_content" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Ans_vote" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "ansId",
    "ansQid",
    "ansName",
    "ansEmail",
    "ansContent",
    "ansVote"
})
public class Answer {

    @XmlElement(name = "Ans_id")
    protected int ansId;
    @XmlElement(name = "Ans_qid")
    protected int ansQid;
    @XmlElement(name = "Ans_name", required = true)
    protected String ansName;
    @XmlElement(name = "Ans_email", required = true)
    protected String ansEmail;
    @XmlElement(name = "Ans_content", required = true)
    protected String ansContent;
    @XmlElement(name = "Ans_vote")
    protected int ansVote;

    /**
     * Gets the value of the ansId property.
     * 
     */
    public int getAnsId() {
        return ansId;
    }

    /**
     * Sets the value of the ansId property.
     * 
     */
    public void setAnsId(int value) {
        this.ansId = value;
    }

    /**
     * Gets the value of the ansQid property.
     * 
     */
    public int getAnsQid() {
        return ansQid;
    }

    /**
     * Sets the value of the ansQid property.
     * 
     */
    public void setAnsQid(int value) {
        this.ansQid = value;
    }

    /**
     * Gets the value of the ansName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnsName() {
        return ansName;
    }

    /**
     * Sets the value of the ansName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnsName(String value) {
        this.ansName = value;
    }

    /**
     * Gets the value of the ansEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnsEmail() {
        return ansEmail;
    }

    /**
     * Sets the value of the ansEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnsEmail(String value) {
        this.ansEmail = value;
    }

    /**
     * Gets the value of the ansContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnsContent() {
        return ansContent;
    }

    /**
     * Sets the value of the ansContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnsContent(String value) {
        this.ansContent = value;
    }

    /**
     * Gets the value of the ansVote property.
     * 
     */
    public int getAnsVote() {
        return ansVote;
    }

    /**
     * Sets the value of the ansVote property.
     * 
     */
    public void setAnsVote(int value) {
        this.ansVote = value;
    }

}
