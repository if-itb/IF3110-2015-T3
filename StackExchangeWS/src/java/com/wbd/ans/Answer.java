/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.ans;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Answer")
/**
 *
 * @author User
 */
public class Answer {
    @XmlElement(name = "IDAns", required = true)
    private int idans;
    @XmlElement(name = "IDQ", required = true)
    private int idq;
    @XmlElement(name = "IDUser", required = true)
    private int iduser;
    @XmlElement(name = "Answer", required = true)
    private String answer;
    @XmlElement(name = "Vote", required = true)
    private int Vote;
    
    public Answer(){
        idans = 0;
        idq = 0;
    }
    
    public Answer(int idA, int qid, int iduser, String isi, int vote){
        idans = idA;
        idq = qid;
        this.iduser = iduser;
        answer = isi;
        Vote = vote;
    }
    
    public int getIDAns(){
        return idans;
    }
    
    public int getIDQ(){
        return idq;
    }
    
    public int getIDUser(){
        return iduser;
    }
    
    public String getAnswer(){
        return answer;
    }
    
    public int getVote(){
        return Vote;
    }
}
