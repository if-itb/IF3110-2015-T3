/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.xml.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Tifani
 */
@XmlRootElement(name="Question")
public class Question {
    @XmlElement(name="q_id", required=true)
    private int qId;
    @XmlElement(name="u_id", required=true)
    private int uId;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="topic", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="answer", required=true)
    private int answer;
    @XmlElement(name="date_created", required=true)
    private String dateCreated;
    @XmlElement(name="date_edited", required=true)
    private String dateEdited;
    
    public Question() {
        
    }
    
    public Question(int qId, int uId, String email, String name, String topic, String content, int vote, int answer, String dateCreated, String dateEdited) {
        this.qId = qId;
        this.uId = uId;
        this.email = email;
        this.name = name;
        this.topic = topic;
        this.content = content;
        this.vote = vote;
        this.answer = answer;
        this.dateCreated = dateCreated;
        this.dateEdited = dateEdited;
    }

    public int getQId() {
        return qId;
    }

    public int getUId() {
        return uId;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public int getVote() {
        return vote;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    
    public String getDateEdited() {
        return dateEdited;
    }

    public int getAnswer() {
        return answer;
    }

}
