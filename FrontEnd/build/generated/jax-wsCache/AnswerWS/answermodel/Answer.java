
package answermodel;

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
 *         &lt;element name="AnswerID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="QuestionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Votes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Answer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Datetime" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "answerID",
    "questionID",
    "votes",
    "answer",
    "name",
    "email",
    "datetime"
})
public class Answer {

    @XmlElement(name = "AnswerID")
    protected int answerID;
    @XmlElement(name = "QuestionID")
    protected int questionID;
    @XmlElement(name = "Votes")
    protected int votes;
    @XmlElement(name = "Answer", required = true)
    protected String answer;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "Datetime", required = true)
    protected String datetime;

    /**
     * Gets the value of the answerID property.
     * 
     */
    public int getAnswerID() {
        return answerID;
    }

    /**
     * Sets the value of the answerID property.
     * 
     */
    public void setAnswerID(int value) {
        this.answerID = value;
    }

    /**
     * Gets the value of the questionID property.
     * 
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * Sets the value of the questionID property.
     * 
     */
    public void setQuestionID(int value) {
        this.questionID = value;
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
     * Gets the value of the answer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the value of the answer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswer(String value) {
        this.answer = value;
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
     * Gets the value of the datetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the value of the datetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatetime(String value) {
        this.datetime = value;
    }

}
