package model;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Question")
public class Question {
    
    @XmlElement(name="qid", required=true)
    private int qid;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name = "qtopic", required = true)
    private String qtopic;
    @XmlElement(name = "qcontent", required = true)
    private String qcontent;
    @XmlElement(name = "votes", required = true)
    private int votes;
    @XmlElement(name = "answer_count", required = true)
    private int answer_count;
    @XmlElement(name = "created_at", required = true)
    private String created_at;
    
    public Question(){}
    
    public Question(int qid, String name, String email, String qtopic, String qcontent, int votes, int answer_count, String created_at ) {
        this.qid = qid;
        this.name = name;
        this.email = email;
        this.qtopic = qtopic;
        this.qcontent = qcontent;
        this.votes = votes;
        this.answer_count = answer_count;
        this.created_at = created_at;        
    }
    
}
