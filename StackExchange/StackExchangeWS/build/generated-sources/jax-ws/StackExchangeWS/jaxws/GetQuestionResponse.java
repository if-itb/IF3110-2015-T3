
package StackExchangeWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import StackExchangeWS.Question;

@XmlRootElement(name = "getQuestionResponse", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getQuestionResponse", namespace = "http://StackExchangeWS/")
public class GetQuestionResponse {

    @XmlElement(name = "Question", namespace = "")
    private Question question;

    /**
     * 
     * @return
     *     returns Question
     */
    public Question getQuestion() {
        return this.question;
    }

    /**
     * 
     * @param question
     *     the value for the question property
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

}
