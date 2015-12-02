
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
 * &lt;complexType name="question">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QuestionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Votes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Answers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Topic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Question" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "question", propOrder = {
    "questionID",
    "votes",
    "answers",
    "topic",
    "question",
    "name",
    "email",
    "datetime"
})
public class Question {

    @XmlElement(name = "QuestionID")
    protected int questionID;
    @XmlElement(name = "Votes")
    protected int votes;
    @XmlElement(name = "Answers")
    protected int answers;
    @XmlElement(name = "Topic", required = true)
    protected String topic;
    @XmlElement(name = "Question", required = true)
    protected String question;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "Datetime", required = true)
    protected String datetime;

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
     * Gets the value of the answers property.
     * 
     */
    public int getAnswers() {
        return answers;
    }

    /**
     * Sets the value of the answers property.
     * 
     */
    public void setAnswers(int value) {
        this.answers = value;
    }

    /**
     * Gets the value of the topic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the value of the topic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopic(String value) {
        this.topic = value;
    }

    /**
     * Gets the value of the question property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the value of the question property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestion(String value) {
        this.question = value;
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
