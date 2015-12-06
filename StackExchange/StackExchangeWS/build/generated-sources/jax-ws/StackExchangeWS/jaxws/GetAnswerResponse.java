
package StackExchangeWS.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import StackExchangeWS.Answer;

@XmlRootElement(name = "getAnswerResponse", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAnswerResponse", namespace = "http://StackExchangeWS/")
public class GetAnswerResponse {

    @XmlElement(name = "Answer", namespace = "")
    private ArrayList<Answer> answer;

    /**
     * 
     * @return
     *     returns ArrayList<Answer>
     */
    public ArrayList<Answer> getAnswer() {
        return this.answer;
    }

    /**
     * 
     * @param answer
     *     the value for the answer property
     */
    public void setAnswer(ArrayList<Answer> answer) {
        this.answer = answer;
    }

}
