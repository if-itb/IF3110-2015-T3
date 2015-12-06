
package StackExchangeWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteQuestion", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteQuestion", namespace = "http://StackExchangeWS/", propOrder = {
    "token",
    "qid"
})
public class DeleteQuestion {

    @XmlElement(name = "token", namespace = "")
    private String token;
    @XmlElement(name = "qid", namespace = "")
    private int qid;

    /**
     * 
     * @return
     *     returns String
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 
     * @param token
     *     the value for the token property
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getQid() {
        return this.qid;
    }

    /**
     * 
     * @param qid
     *     the value for the qid property
     */
    public void setQid(int qid) {
        this.qid = qid;
    }

}
