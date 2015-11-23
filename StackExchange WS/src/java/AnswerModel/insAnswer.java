/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanji
 */
@XmlRootElement (name = "insQuestion")
public class insAnswer {
    @XmlElement(name = "a_id", required = true)
    private int a_id;
    @XmlElement(name = "a_vote", required = true)
    private int a_vote;
    @XmlElement(name = "q_id", required = true)
    private int q_id;
    @XmlElement(name = "u_id", required = true)
    private int u_id;
    @XmlElement(name = "u_name", required = true)
    private String u_name;
    @XmlElement(name = "a_email", required = true)
    private String a_email;
    @XmlElement(name = "a_content", required = true)
    private String a_content;
    @XmlElement(name = "a_date", required = true)
    private String a_date;
    
    public insAnswer(){
        a_id = 0;
        a_vote = 0;
        q_id = 0;
        u_id = 0;
    }
    
    public insAnswer(int id, int vote, int qid, int uid, String uname, String email, String content, String timestamp){
        this.a_id = id;
        this.a_vote = vote;
        this.q_id = qid;
        this.u_id = uid;
        this.u_name = uname;
        this.a_email = email;
        this.a_content = content;
        this.a_date = timestamp;
    }
}
