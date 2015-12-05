/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.qst;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Question")
/**
 *
 * @author User
 */
public class Question {
    @XmlElement(name = "IDQ", required = true)
    private int idq;
    @XmlElement(name = "iduser", required = true)
    private int iduser;
    @XmlElement(name = "QuestionTopic", required = true)
    private String question_topic;
    @XmlElement(name = "Content", required = true)
    private String content;
    @XmlElement(name = "Vote", required = true)
    private int vote;
    
    public Question(){
        idq = 0;
    }
    
    public Question(int qid, int iduser, String q_topic, String konten, int Vote){
        idq = qid;
        this.iduser = iduser;
        question_topic = q_topic;
        content = konten;
        vote = Vote;
    }
    
    public int getIDQ(){
        return idq;
    }
    
    public int getIDUser(){
        return iduser;
    }
    
    public String getQuestionTopic(){
        return question_topic;
    }
    
    public String getContent(){
        return content;
    }
    
    public int getVote(){
        return vote;
    }
}
