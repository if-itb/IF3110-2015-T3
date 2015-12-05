/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tama
 */
@XmlRootElement(name="Comment")
public class Comment {
    @XmlElement(name="id_c",required=true)
    public int id_c;
    @XmlElement(name="q_id",required=true)
    public int q_id;
    @XmlElement(name="content",required=true)
    public String content;
    @XmlElement(name="date",required=true)
    public String date;
    @XmlElement(name="username",required=true)
    public String username; 
    
    public Comment() {}
    
    public Comment(int i,int q,String c,String d,String u) {
        this.id_c=i;
        this.q_id=q;
        this.content=c;
        this.date=d;
        this.username=u;
    }   
}
