
package wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for voteUpAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="voteUpAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="access_token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="answerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="questionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "voteUpAnswer", propOrder = {
    "accessToken",
    "answerId",
    "questionId"
})
public class VoteUpAnswer {

    @XmlElement(name = "access_token")
    protected String accessToken;
    protected int answerId;
    protected int questionId;

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
     * Gets the value of the answerId property.
     * 
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * Sets the value of the answerId property.
     * 
     */
    public void setAnswerId(int value) {
        this.answerId = value;
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

}
