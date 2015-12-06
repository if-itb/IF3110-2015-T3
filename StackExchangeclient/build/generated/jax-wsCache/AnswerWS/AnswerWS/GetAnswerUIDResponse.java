
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAnswerUIDResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAnswerUIDResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getAnswerUID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAnswerUIDResponse", propOrder = {
    "getAnswerUID"
})
public class GetAnswerUIDResponse {

    protected int getAnswerUID;

    /**
     * Gets the value of the getAnswerUID property.
     * 
     */
    public int getGetAnswerUID() {
        return getAnswerUID;
    }

    /**
     * Sets the value of the getAnswerUID property.
     * 
     */
    public void setGetAnswerUID(int value) {
        this.getAnswerUID = value;
    }

}
