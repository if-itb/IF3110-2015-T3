
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAnswerQIDResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAnswerQIDResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gtQID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAnswerQIDResponse", propOrder = {
    "gtQID"
})
public class GetAnswerQIDResponse {

    protected int gtQID;

    /**
     * Gets the value of the gtQID property.
     * 
     */
    public int getGtQID() {
        return gtQID;
    }

    /**
     * Sets the value of the gtQID property.
     * 
     */
    public void setGtQID(int value) {
        this.gtQID = value;
    }

}
