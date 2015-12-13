
package questionmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetQuestionByIDResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetQuestionByIDResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QuestionByID" type="{http://QuestionModel/}question" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetQuestionByIDResponse", propOrder = {
    "questionByID"
})
public class GetQuestionByIDResponse {

    @XmlElement(name = "QuestionByID")
    protected Question questionByID;

    /**
     * Gets the value of the questionByID property.
     * 
     * @return
     *     possible object is
     *     {@link Question }
     *     
     */
    public Question getQuestionByID() {
        return questionByID;
    }

    /**
     * Sets the value of the questionByID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Question }
     *     
     */
    public void setQuestionByID(Question value) {
        this.questionByID = value;
    }

}
