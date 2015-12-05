
package org.tusiri.ws.question;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for questionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="questionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_question" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_user" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="num_answer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="num_vote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="question_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="topic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "questionItem", propOrder = {
    "content",
    "idQuestion",
    "idUser",
    "numAnswer",
    "numVote",
    "questionDate",
    "status",
    "topic",
    "username"
})
public class QuestionItem {

    protected String content;
    @XmlElement(name = "id_question")
    protected int idQuestion;
    @XmlElement(name = "id_user")
    protected int idUser;
    @XmlElement(name = "num_answer")
    protected int numAnswer;
    @XmlElement(name = "num_vote")
    protected int numVote;
    @XmlElement(name = "question_date")
    protected String questionDate;
    protected int status;
    protected String topic;
    protected String username;

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
     * Gets the value of the idQuestion property.
     * 
     */
    public int getIdQuestion() {
        return idQuestion;
    }

    /**
     * Sets the value of the idQuestion property.
     * 
     */
    public void setIdQuestion(int value) {
        this.idQuestion = value;
    }

    /**
     * Gets the value of the idUser property.
     * 
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Sets the value of the idUser property.
     * 
     */
    public void setIdUser(int value) {
        this.idUser = value;
    }

    /**
     * Gets the value of the numAnswer property.
     * 
     */
    public int getNumAnswer() {
        return numAnswer;
    }

    /**
     * Sets the value of the numAnswer property.
     * 
     */
    public void setNumAnswer(int value) {
        this.numAnswer = value;
    }

    /**
     * Gets the value of the numVote property.
     * 
     */
    public int getNumVote() {
        return numVote;
    }

    /**
     * Sets the value of the numVote property.
     * 
     */
    public void setNumVote(int value) {
        this.numVote = value;
    }

    /**
     * Gets the value of the questionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionDate() {
        return questionDate;
    }

    /**
     * Sets the value of the questionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionDate(String value) {
        this.questionDate = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
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
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
