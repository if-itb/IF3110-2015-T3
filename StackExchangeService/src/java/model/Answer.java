package model;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Answer")
public class Answer {
    
    @XmlElement(name="aid", required=true)
    private final int qid;
    @XmlElement(name="qid", required=true)
    private final int aid;
    @XmlElement(name="name", required=true)
    private final String name;
    @XmlElement(name="email", required=true)
    private final String email;
    @XmlElement(name = "content", required = true)
    private final String content;
    @XmlElement(name = "votes", required = true)
    private final int votes;
    @XmlElement(name = "created_at", required = true)
    private final String created_at;
        
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
