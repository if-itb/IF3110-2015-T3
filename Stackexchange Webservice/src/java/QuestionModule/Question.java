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
    @XmlElement(name="uid", required= true)
    private int uid;
    @XmlElement(name="content", required= true)
    private String content;
    @XmlElement(name="timestamp", required= true)
    private String timestamp;

    public Question() {
        this.qid = 0;
        this.uid = 0;
        this.content = "";
        this.timestamp = "";
    }

    
    public Question(int qid, int uid, String content, String timestamp) {
        this.qid = qid;
        this.uid = uid;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getQid() {
        return qid;
    }

    public int getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

}


