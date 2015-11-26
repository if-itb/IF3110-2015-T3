
package answermodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for insertAnswerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="insertAnswerResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="insAnswer" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insertAnswerResponse", propOrder = {
    "insAnswer"
})
public class InsertAnswerResponse {

    protected int insAnswer;

    /**
     * Gets the value of the insAnswer property.
     * 
     */
    public int getInsAnswer() {
        return insAnswer;
    }

    /**
     * Sets the value of the insAnswer property.
     * 
     */
    public void setInsAnswer(int value) {
        this.insAnswer = value;
    }

}
