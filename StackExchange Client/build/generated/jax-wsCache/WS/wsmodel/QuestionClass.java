
package wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for questionClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="questionClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="questionContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="questionTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="questionVote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "questionClass", propOrder = {
    "questionContent",
    "questionDate",
    "questionId",
    "questionTitle",
    "questionUserId",
    "questionVote"
})
public class QuestionClass {

    protected String questionContent;
    protected String questionDate;
    protected int questionId;
    protected String questionTitle;
    protected int questionUserId;
    protected int questionVote;

    /**
     * Gets the value of the questionContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * Sets the value of the questionContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionContent(String value) {
        this.questionContent = value;
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
     * Gets the value of the questionId property.
     * 
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Sets the value of the questionId property.
     * 
     */
    public void setQuestionId(int value) {
        this.questionId = value;
    }

    /**
     * Gets the value of the questionTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * Sets the value of the questionTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionTitle(String value) {
        this.questionTitle = value;
    }

    /**
     * Gets the value of the questionUserId property.
     * 
     */
    public int getQuestionUserId() {
        return questionUserId;
    }

    /**
     * Sets the value of the questionUserId property.
     * 
     */
    public void setQuestionUserId(int value) {
        this.questionUserId = value;
    }

    /**
     * Gets the value of the questionVote property.
     * 
     */
    public int getQuestionVote() {
        return questionVote;
    }

    /**
     * Sets the value of the questionVote property.
     * 
     */
    public void setQuestionVote(int value) {
        this.questionVote = value;
    }

}
