
package StackExchangeWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAnswer", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAnswer", namespace = "http://StackExchangeWS/")
public class GetAnswer {

    @XmlElement(name = "qid", namespace = "")
    private int qid;

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
