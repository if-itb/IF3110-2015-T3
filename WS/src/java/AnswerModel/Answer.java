/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author yoga
 */
@XmlRootElement(name = "Answer")
public class Answer {

    @XmlElement(name = "AnswerID", required = true)
    private int AnswerID;
    @XmlElement(name = "QuestionID", required = true)
    private int QuestionID;
    @XmlElement(name = "Votes", required = true)
    private int Votes;
    @XmlElement(name = "Answer", required = true)
    private String Answer;
    
    @XmlElement(name = "Name", required = true)
    private String Name;
    @XmlElement(name = "Email", required = true)
    private String Email;
    @XmlElement(name = "Datetime", required = true)
    private String Datetime;

    public Answer() {
        AnswerID = 0;
        QuestionID = 0;
        Votes = 0;
        
    }

    public Answer(int id, int qid, int vote, String ans, String name,String email, String tanggal) {
        AnswerID = id;
        QuestionID = qid;
        Votes = vote;
        Answer = ans;
        Name= name;
        Email=email;
        Datetime = tanggal;
    }
}
