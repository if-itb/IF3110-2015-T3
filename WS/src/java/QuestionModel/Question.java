/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author yoga
 */
@XmlRootElement(name = "Question")
public class Question {

    @XmlElement(name = "QuestionID", required = true)
    public int QuestionID;
    @XmlElement(name = "Votes", required = true)
    private int Votes;
    @XmlElement(name = "Answers", required = true)
    private int Answers;
    @XmlElement(name = "Topic", required = true)
    private String Topic;
    @XmlElement(name = "Question", required = true)
    private String Question;
    @XmlElement(name = "Name", required = true)
    private String Name;
    @XmlElement(name = "Email", required = true)
    private String Email;
    @XmlElement(name = "Datetime", required = true)
    private String Datetime;

    public Question() {
        QuestionID = 0;
        Votes = 0;

    }

    public Question(int id, int vote, int ans,String topic, String question, String name, String email, String tanggal) {
        QuestionID = id;
        Votes = vote;
        Answers = ans;
        Topic=topic;
        Question=question;
        Name = name;
        Email = email;
        Datetime = tanggal;
    }
    
    public int getID(){
        return QuestionID;
    }
}
