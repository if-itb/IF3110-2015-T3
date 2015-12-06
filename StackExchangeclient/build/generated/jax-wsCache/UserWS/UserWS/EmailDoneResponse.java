
package UserWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for emailDoneResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="emailDoneResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emailDone" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emailDoneResponse", propOrder = {
    "emailDone"
})
public class EmailDoneResponse {

    protected boolean emailDone;

    /**
     * Gets the value of the emailDone property.
     * 
     */
    public boolean isEmailDone() {
        return emailDone;
    }

    /**
     * Sets the value of the emailDone property.
     * 
     */
    public void setEmailDone(boolean value) {
        this.emailDone = value;
    }

}
