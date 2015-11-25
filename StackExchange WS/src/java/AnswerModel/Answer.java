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
 * @author Vanji
 */
@XmlRootElement(name = "Answer")
public class Answer {
    @XmlElement(name = "a_id", required = true)
    private int a_id;
    @XmlElement(name = "q_id", required = true)
    private int q_id;
    @XmlElement(name = "a_vote", required = true)
    private int a_vote;
    @XmlElement(name = "u_id", required = true)
    private int u_id;
    @XmlElement(name = "a_content", required = true)
    private String a_content;
    @XmlElement(name = "a_date", required = true)
    private String a_date;
    @XmlElement (name = "u_name", required = true)
    private String u_name;
    
    public Answer(){
        a_id = 0;
        q_id = 0;
        u_id = 0;
    }
    
    public Answer(int id, int qid, int uid, int vote, String content, String timestamp, String uname){
        this.a_id = id;
        q_id = qid;
        u_id = uid;
        u_name = uname;
        a_vote = vote;
        this.a_content = content;
        this.a_date = timestamp;
    }
    
    /* Getter */
    public int getAID(){
        return a_id;
    }
    
    public int getQID(){
        return q_id;
    }
    
    public int getAVote(){
        return a_vote;
    }
    
    public int getUID(){
        return u_id;
    }
    
    public String getUName(){
        return u_name;
    }
    
    public String getAContent(){
        return a_content;
    }
}
