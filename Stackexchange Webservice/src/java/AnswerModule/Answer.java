/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModule;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
/**
 *
 * @author LUCKY
 */

@XmlRootElement(name = "Answer")
public class Answer {
    @XmlElement(name="aid", required=true)
    private int aid;
    @XmlElement(name="qid", required=true)
    private int qid;
    @XmlElement(name="uid", required=true)
    private int uid;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="timestamp", required=true)
    private String timestamp;
    public Answer(){
        aid = 0;
        qid = 0;
        uid = 0;
        content = "";
        timestamp = "";
    }
    
    public Answer(int paramid, int paramqid, int paramuid, String paramcontent, String paramtimestamp){
     aid = paramid;
     qid = paramqid;
     uid = paramuid;
     content = paramcontent;
     timestamp = paramtimestamp;
    }
    
    public int getAid() {
        return aid;
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
