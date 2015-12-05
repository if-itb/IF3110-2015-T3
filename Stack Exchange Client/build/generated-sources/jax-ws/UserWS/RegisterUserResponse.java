
package UserWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerUserResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NewUser" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUserResponse", propOrder = {
    "newUser"
})
public class RegisterUserResponse {

    @XmlElement(name = "NewUser")
    protected int newUser;

    /**
     * Gets the value of the newUser property.
     * 
     */
    public int getNewUser() {
        return newUser;
    }

    /**
     * Sets the value of the newUser property.
     * 
     */
    public void setNewUser(int value) {
        this.newUser = value;
    }

}
