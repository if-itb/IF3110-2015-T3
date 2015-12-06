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
@XmlRootElement(name="Answer")
public class Answer {
  @XmlElement(name="id_a",required=true)
    public int id_a;
    @XmlElement(name="q_id",required=true)
    public int q_id;
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
    
    public  Answer() {}
    
    public Answer(int i,int q,String c,int v,String d,String u,String e) {
        this.id_a=i;
        this.q_id=q;
        this.content=c;
        this.vote=v;
        this.date=d;
        this.username=u;
        this.email=e;
    }   
}
