package Class;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;


/**
 *
 * @author tama
 */
@XmlRootElement(name="Question")
public class Question {
    @XmlElement(name="id_q",required=true)
    public int id_q;
    @XmlElement(name="title",required=true)
    public String title;
    @XmlElement(name="content",required=true)
    public String content;
    @XmlElement(name="vote",required=true)
    public int vote;
    @XmlElement(name="date",required=true)
    public String date;
    @XmlElement(name="username",required=true)
    public String username; 
    @XmlElement(name="email",required=true)
    public String email;
    @XmlElement(name="tanswer",required=true)
    public int tanswer;
    
    public  Question() {}
    
    public Question(int i,String t,String c,int v,String d,String u,String e,int ta) {
        this.id_q=i;
        this.title=t;
        this.content=c;
        this.vote=v;
        this.date=d;
        this.username=u;
        this.email=e;
        this.tanswer=ta;
               
    }
}
