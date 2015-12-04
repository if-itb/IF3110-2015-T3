/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.answer;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Venny
 */
public class Answer {
    private int answer_id;
    private int question_id;
    private int user_id;
    private String content;
    private int vote;
    private String create_time;
    
    public Answer(){
        answer_id=0;
        question_id=0;
        user_id=0;
        vote=0;
    }
    
    public Answer(int aid, int qid, int uid, String content, int vote, String create_time) {
           answer_id = aid;
           question_id = qid;
           user_id = uid; 
           this.vote = vote;
           this.content = content;
           this.create_time = create_time; 
    }
    
    
    @XmlElement(name="answer_id", required=true)
    public int getAnswerID() {
        return answer_id;
    }
    
    
    @XmlElement(name="question_id", required=true)
    public int getQuestionID() {
        return question_id;
    }
    
    
    @XmlElement(name="user_id", required=true)
    public int getUserID() {
        return user_id;
    }
        
    @XmlElement(name="content", required=true)
    public String getContent() {
        return content;
    }
    
    
    @XmlElement(name="vote", required=false)
    public int getVote(){
        return vote;
    }
    
    
    @XmlElement(name="create_time", required=true)
    public String getCreateTime() {
        return create_time;
    }
    
    public void setAnswerID (int aid) {
        answer_id = aid;
    }
    public void setQuestionID(int qid) {
        question_id = qid;
    }
    
    public void setUserID(int uid) {
        user_id = uid;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setVote(int vote) {
        this.vote = vote;
    }
}
