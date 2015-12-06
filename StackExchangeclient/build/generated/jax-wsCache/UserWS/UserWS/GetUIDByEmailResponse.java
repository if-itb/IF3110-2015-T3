
package UserWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUIDByEmailResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUIDByEmailResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getUIDByEmail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUIDByEmailResponse", propOrder = {
    "getUIDByEmail"
})
public class GetUIDByEmailResponse {

    protected int getUIDByEmail;

    /**
     * Gets the value of the getUIDByEmail property.
     * 
     */
    public int getGetUIDByEmail() {
        return getUIDByEmail;
    }

    /**
     * Sets the value of the getUIDByEmail property.
     * 
     */
    public void setGetUIDByEmail(int value) {
        this.getUIDByEmail = value;
    }

}
