/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.answer_package;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Answer")
/**
 *
 * @author zulvafachrina
 */
public class Answer {
    @XmlElement(name="id", required=true)
    private int id;
    
    @XmlElement(name="question_id", required=true)
    private int questionId;
    
    @XmlElement(name="user_id", required=true)
    private int userId;
    
    @XmlElement(name="content", required=true)
    private String content;
    
    @XmlElement(name="vote", required=true)
    private int vote;
    
    @XmlElement(name="date", required=true)
    private String date;
    
    @XmlElement(name="username", required=true)
    private String username;
    
    public Answer() {
        id=0;
        questionId=0;
        userId = 0;
    }
    
    public Answer(int id, int qid, int uid, String content, int vote, String date, String username){
        this.id=id;
        questionId = qid;
        userId = uid;
        this.content = content;
        this.vote=vote;
        this.date = date;
        this.username = username;
        
    }
    
    public int getId() {
        return id;
    }
    
    public int getQid() {
        return questionId;
    }
    
    public int getUid() {
        return userId;
    }
    
    public String getContent() {
        return content;
    }
    
    public int getVote() {
        return vote;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getAnswerUsername(){
        return username;
    }
    
    public void setAnswerQuestionId(int qid) {
        this.questionId = qid;
    }
    
    public void setAnswerUserId(int uid) {
        this.userId = uid;
    }
    
    public void setAnswerContent(String content) {
        this.content = content;
    }
    
    public void setAnswerVote(int vote) {
        this.vote = vote;
    }
    
    public void setAnswerDate(String date) {
        this.date = date;
    }
    
     public void setAnswerUsername(String username) {
        this.username = username;
    }
}
