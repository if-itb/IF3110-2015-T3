package model;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Answer")
public class Answer {
    @XmlElement(name="qid", required=true)
    private int qid;
    @XmlElement(name="aid", required=true)
    private int aid;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name = "content", required = true)
    private String content;
    @XmlElement(name = "votes", required = true)
    private int votes;
    @XmlElement(name = "created_at", required = true)
    private String created_at;
    
    public Answer() {}
        
    public Answer(int qid,int aid, String name, String email, String content, int votes, String created_at ) {
        this.aid = aid;
        this.qid = qid;
        this.name = name;
        this.email = email;
        this.content = content;
        this.votes = votes;
        this.created_at = created_at;        
    }    
}
