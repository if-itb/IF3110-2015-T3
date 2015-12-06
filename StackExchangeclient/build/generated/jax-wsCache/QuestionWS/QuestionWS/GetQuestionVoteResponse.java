
package QuestionWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getQuestionVoteResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getQuestionVoteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "getQuestionVoteResponse", propOrder = {
    "questionVote"
})
public class GetQuestionVoteResponse {

    protected int questionVote;

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
