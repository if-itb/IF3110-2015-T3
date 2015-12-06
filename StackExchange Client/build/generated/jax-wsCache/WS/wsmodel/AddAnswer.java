
package wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="access_token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="question_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="answerContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addAnswer", propOrder = {
    "accessToken",
    "questionId",
    "answerContent"
})
public class AddAnswer {

    @XmlElement(name = "access_token")
    protected String accessToken;
    @XmlElement(name = "question_id")
    protected int questionId;
    protected String answerContent;

    /**
     * Gets the value of the accessToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the value of the accessToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessToken(String value) {
        this.accessToken = value;
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
     * Gets the value of the answerContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * Sets the value of the answerContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswerContent(String value) {
        this.answerContent = value;
    }

}
