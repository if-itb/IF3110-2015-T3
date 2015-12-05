
package UserWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUserByTokenResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUserByTokenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userResult" type="{http://user_package.dazzlesquad.com/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserByTokenResponse", propOrder = {
    "userResult"
})
public class GetUserByTokenResponse {

    protected User userResult;

    /**
     * Gets the value of the userResult property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserResult() {
        return userResult;
    }

    /**
     * Sets the value of the userResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserResult(User value) {
        this.userResult = value;
    }

}
