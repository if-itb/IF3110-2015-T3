
package wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateQuestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateQuestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="access_token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="questionTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateQuestion", propOrder = {
    "accessToken",
    "questionId",
    "questionTitle",
    "questionContent"
})
public class UpdateQuestion {

    @XmlElement(name = "access_token")
    protected String accessToken;
    protected int questionId;
    protected String questionTitle;
    protected String questionContent;

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

}
