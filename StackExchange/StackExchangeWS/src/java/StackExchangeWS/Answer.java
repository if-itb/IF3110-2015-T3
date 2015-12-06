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
@XmlRootElement(name="Answer")
public class Answer {
    @XmlElement(name="id", required=true)
    public int id;
    
    @XmlElement(name="questionId", required=true)
    public int questionId;
    
    @XmlElement(name="userId", required=true)
    public int userId;
    
    @XmlElement(name="content", required=true)
    public String content;
    
    @XmlElement(name="vote", required=true)
    public int vote;
    
    public Answer() {
        id = 0;
        questionId = 0;
        userId = 0;
        vote = 0;
    }
    
    public Answer(int id, int questionId, int userId, String content, int vote) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.vote = vote;
    }
}
