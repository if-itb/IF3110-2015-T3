
package QuestionWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getQuestionUIDResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getQuestionUIDResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getQuestionUID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getQuestionUIDResponse", propOrder = {
    "getQuestionUID"
})
public class GetQuestionUIDResponse {

    protected int getQuestionUID;

    /**
     * Gets the value of the getQuestionUID property.
     * 
     */
    public int getGetQuestionUID() {
        return getQuestionUID;
    }

    /**
     * Sets the value of the getQuestionUID property.
     * 
     */
    public void setGetQuestionUID(int value) {
        this.getQuestionUID = value;
    }

}
