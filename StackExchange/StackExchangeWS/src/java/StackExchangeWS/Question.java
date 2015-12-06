/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StackExchangeWS;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Calvin
 */
@XmlRootElement(name="Question")
public class Question {
    @XmlElement(name="id", required=true)
    public int id;
    
    @XmlElement(name="userId", required=true)
    public int userId;
    
    @XmlElement(name="topic", required=true)
    public String topic;
    
    @XmlElement(name="content", required=true)
    public String content;
    
    @XmlElement(name="vote", required=true)
    public int vote;
    
    public Question() {
        id = 0;
        userId = 0;
        vote = 0;
    }
    
    public Question(int id, int userId, String topic, String content, int vote) {
        this.id = id;
        this.userId = userId;
        this.topic = topic;
        this.content = content;
        this.vote = vote;
    }
}
