/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Vanji
 */
public class Question {
    @XmlElement(name = "q_id", required = true)
    private int q_id;
    @XmlElement(name = "u_id", required = true)
    private int u_id;
    @XmlElement(name = "q_vote", required = true)
    private int q_vote;
    @XmlElement(name = "u_name", required = true)
    private String u_name;
    @XmlElement(name = "q_topic", required = true)
    private String q_topic;
    @XmlElement(name = "q_content", required = true)
    private String q_content;
    @XmlElement(name = "q_date", required = true)
    private String q_date;
    
    public Question(){
        q_id = 0;
        u_id = 0;
    }
    
    public Question(int id, int uid, int qvote, String uname, String topic, String content, String timestamp){
        this.q_id = id;
        u_id = uid;
        q_vote = qvote;
        this.u_name = uname;
        this.q_topic = topic;
        this.q_content = content;
        this.q_date = timestamp;
    }
    
    public int getID(){
        return q_id;
    }
    public int getUID(){
        return u_id;
    }
    public String getUName(){
        return u_name;
    }
    public int getQVote(){
        return q_vote;
    }
    public String getQTopic(){
        return q_topic;
    }
    public String getQContent(){
        return q_content;
    }
    public String getQTime(){
        return q_date;
    }
    
}
