
package StackExchangeWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateQuestion", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateQuestion", namespace = "http://StackExchangeWS/", propOrder = {
    "token",
    "qid",
    "topic",
    "content"
})
public class UpdateQuestion {

    @XmlElement(name = "token", namespace = "")
    private String token;
    @XmlElement(name = "qid", namespace = "")
    private int qid;
    @XmlElement(name = "topic", namespace = "")
    private String topic;
    @XmlElement(name = "content", namespace = "")
    private String content;

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

    /**
     * 
     * @return
     *     returns String
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * 
     * @param topic
     *     the value for the topic property
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 
     * @param content
     *     the value for the content property
     */
    public void setContent(String content) {
        this.content = content;
    }

}
