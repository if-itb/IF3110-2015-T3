
package QuestionWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for numVoteQuestionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="numVoteQuestionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numVoteQuestion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "numVoteQuestionResponse", propOrder = {
    "numVoteQuestion"
})
public class NumVoteQuestionResponse {

    protected int numVoteQuestion;

    /**
     * Gets the value of the numVoteQuestion property.
     * 
     */
    public int getNumVoteQuestion() {
        return numVoteQuestion;
    }

    /**
     * Sets the value of the numVoteQuestion property.
     * 
     */
    public void setNumVoteQuestion(int value) {
        this.numVoteQuestion = value;
    }

}
