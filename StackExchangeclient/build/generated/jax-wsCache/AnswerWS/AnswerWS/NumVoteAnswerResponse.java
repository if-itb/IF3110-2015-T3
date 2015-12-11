
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for numVoteAnswerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="numVoteAnswerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numVoteAnswer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "numVoteAnswerResponse", propOrder = {
    "numVoteAnswer"
})
public class NumVoteAnswerResponse {

    protected int numVoteAnswer;

    /**
     * Gets the value of the numVoteAnswer property.
     * 
     */
    public int getNumVoteAnswer() {
        return numVoteAnswer;
    }

    /**
     * Sets the value of the numVoteAnswer property.
     * 
     */
    public void setNumVoteAnswer(int value) {
        this.numVoteAnswer = value;
    }

}
