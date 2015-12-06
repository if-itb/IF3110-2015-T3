/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author fauzanrifqy
 */
@XmlRootElement(name="Answer")
public class Answer {
    @XmlElement(name="id", required=true)
    private int id;
    @XmlElement(name="userid", required=true)
    private int userid;
    @XmlElement(name="questionId", required=true)
    private int questionId;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="dateMade", required=true)
    private Timestamp dateMade;
    @XmlElement(name="vote", required=true)
    private int vote;
    
    public Answer(){}
    
    public Answer(int userid, int questionId, String name, String email, String content, Timestamp dateMade, int vote){
        this.userid = userid;
        this.questionId = questionId;
        this.name = name;
        this.email = email;
        this.content = content;
        this.dateMade = dateMade;
        this.vote = vote;
    } 
    
    public Answer(int id, int userid, int questionId, String name, String email, String content, Timestamp dateMade, int vote){
        this.id = id;
        this.userid = userid;
        this.questionId = questionId;
        this.name = name;
        this.email = email;
        this.content = content;
        this.dateMade = dateMade;
        this.vote = vote;
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
     * @return the questionId
     */
    public int getQuestionId() {
        return questionId;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the dateMade
     */
    public Timestamp getDateMade() {
        return dateMade;
    }

    /**
     * @return the vote
     */
    public int getVote() {
        return vote;
    }
}
