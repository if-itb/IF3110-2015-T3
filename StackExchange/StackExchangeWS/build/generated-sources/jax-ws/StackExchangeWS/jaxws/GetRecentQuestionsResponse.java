
package StackExchangeWS.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import StackExchangeWS.Question;

@XmlRootElement(name = "getRecentQuestionsResponse", namespace = "http://StackExchangeWS/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRecentQuestionsResponse", namespace = "http://StackExchangeWS/")
public class GetRecentQuestionsResponse {

    @XmlElement(name = "Questions", namespace = "")
    private ArrayList<Question> questions;

    /**
     * 
     * @return
     *     returns ArrayList<Question>
     */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    /**
     * 
     * @param questions
     *     the value for the questions property
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

}
