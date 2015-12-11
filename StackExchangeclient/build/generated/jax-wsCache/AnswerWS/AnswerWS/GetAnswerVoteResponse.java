
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAnswerVoteResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAnswerVoteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gtVote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAnswerVoteResponse", propOrder = {
    "gtVote"
})
public class GetAnswerVoteResponse {

    protected int gtVote;

    /**
     * Gets the value of the gtVote property.
     * 
     */
    public int getGtVote() {
        return gtVote;
    }

    /**
     * Sets the value of the gtVote property.
     * 
     */
    public void setGtVote(int value) {
        this.gtVote = value;
    }

}
