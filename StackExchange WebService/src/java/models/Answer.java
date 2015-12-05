/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.xml.bind.annotation.*;

/**
 *
 * @author vanyadeasysafrina
 */
@XmlRootElement(name="Answer")
public class Answer {
    @XmlElement(name="a_id", required=true)
    private int aId;
    @XmlElement(name="u_id", required=true)
    private int uId;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="date_created", required=true)
    private String dateCreated;
    @XmlElement(name="q_id", required=true)
    private int qId;
    
    public Answer() {
        
    }

    public Answer(int a_id, int u_id, String email, String content, int vote, String date_created, int q_id) {
        this.aId = a_id;
        this.uId = u_id;
        this.email = email;
        this.content = content;
        this.vote = vote;
        this.dateCreated = date_created;
        this.qId = q_id;
    }

    public int getAId() {
        return aId;
    }

    public int getUId() {
        return uId;
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

    public int getqId() {
        return qId;
    }

    public String getEmail() {
        return email;
    }
    
}
