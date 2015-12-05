
package answermodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InsertAnswerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InsertAnswerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Answer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertAnswerResponse", propOrder = {
    "answer"
})
public class InsertAnswerResponse {

    @XmlElement(name = "Answer")
    protected int answer;

    /**
     * Gets the value of the answer property.
     * 
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * Sets the value of the answer property.
     * 
     */
    public void setAnswer(int value) {
        this.answer = value;
    }

}
