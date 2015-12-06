
package StackExchangeWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "checkTokenResponse", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkTokenResponse", namespace = "http://StackExchangeWS/")
public class CheckTokenResponse {

    @XmlElement(name = "valid", namespace = "")
    private boolean valid;

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * 
     * @param valid
     *     the value for the valid property
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
