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
    @XmlElement(name="uemail", required=true)
    private String uemail;
    @XmlElement(name="aauthorname", required= true)
    private String aauthorname;
    @XmlElement(name="acontent", required=true)
    private String acontent;
    @XmlElement(name="avote",required=true)
    private int avote;
    @XmlElement(name="atimestamp", required=true)
    private String atimestamp;

    public Answer() {
    }

    public Answer(int aid, int qid, String uemail, String aauthorname, String acontent, int avote, String atimestamp) {
        this.aid = aid;
        this.qid = qid;
        this.uemail = uemail;
        this.aauthorname = aauthorname;
        this.acontent = acontent;
        this.avote = avote;
        this.atimestamp = atimestamp;
    }

    public String getAauthorname() {
        return aauthorname;
    }
    
    public int getAid() {
        return aid;
    }

    public int getQid() {
        return qid;
    }

    public String getUemail() {
        return uemail;
    }

    public String getAcontent() {
        return acontent;
    }

    public int getAvote() {
        return avote;
    }

    public String getAtimestamp() {
        return atimestamp;
    }
   
}
