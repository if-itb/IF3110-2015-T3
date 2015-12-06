/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.model;

import java.sql.Timestamp;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author fauzanrifqy
 */
@XmlRootElement(name="Question")
public class Question {
    @XmlElement(name="id", required=true)
    private int id;
    @XmlElement(name="userid", required=true)
    private int userid;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="topic", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="dateMade", required=true)
    private Timestamp dateMade;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="answer", required=true)
    private int answer;

    public Question(){}
    
    public Question(int userid, String name, String email, String topic, String content, java.sql.Timestamp dateMade, int vote, int answer){
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.topic = topic;
        this.content = content;
        this.dateMade = dateMade;
        this.vote = vote;
        this.answer = answer;
    }
    
    public Question(int id, int userid, String name, String email, String topic, String content, java.sql.Timestamp dateMade, int vote, int answer){
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.topic = topic;
        this.content = content;
        this.dateMade = dateMade;
        this.vote = vote;
        this.answer = answer;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the dateMade
     */
    public java.sql.Timestamp getDateMade() {
        return dateMade;
    }

    /**
     * @return the vote
     */
    public int getVote() {
        return vote;
    }

    /**
     * @return the answer
     */
    public int getAnswer() {
        return answer;
    }
    
    
    
}
