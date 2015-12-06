
package StackExchangeWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getNameById", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNameById", namespace = "http://StackExchangeWS/")
public class GetNameById {

    @XmlElement(name = "userId", namespace = "")
    private int userId;

    /**
     * 
     * @return
     *     returns int
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * 
     * @param userId
     *     the value for the userId property
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
