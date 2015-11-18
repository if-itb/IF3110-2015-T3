/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModule;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
/**
 *
 * @author LUCKY
 */
@XmlRootElement(name = "Question")
public class Question {
    @XmlElement(name="qid", required=true)
    private int qid;
    @XmlElement(name="uemail", required= true)
    private String uemail;
    @XmlElement(name="qtopic",required=true)
    private String qtopic;
    @XmlElement(name="qcontent", required= true)
    private String qcontent;
    @XmlElement(name="qauthorname", required= true)
    private String qauthorname;
    @XmlElement(name="qvote", required = true)
    private int qvote;
    @XmlElement(name="qtimestamp", required= true)
    private String qtimestamp;

    public Question() {
    }

    public Question(int qid, String uemail, String authorname, String qtopic, String qcontent, int qvote, String qtimestamp) {
        this.qid = qid;
        this.uemail = uemail;
        this.qauthorname = authorname;
        this.qtopic = qtopic;
        this.qcontent = qcontent;
        this.qvote = qvote;
        this.qtimestamp = qtimestamp;
    }

    public int getQid() {
        return qid;
    }

    public String getUemail() {
        return uemail;
    }

    public String getQtopic() {
        return qtopic;
    }

    public String getQcontent() {
        return qcontent;
    }

    public int getQvote() {
        return qvote;
    }

    public String getQtimestamp() {
        return qtimestamp;
    }
    
}


