/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mfikria
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAid", query = "SELECT a FROM Answer a WHERE a.aid = :aid"),
    @NamedQuery(name = "Answer.findByQid", query = "SELECT a FROM Answer a WHERE a.qid = :qid"),
    @NamedQuery(name = "Answer.findByUid", query = "SELECT a FROM Answer a WHERE a.uid = :uid"),
    @NamedQuery(name = "Answer.findByCountvotes", query = "SELECT a FROM Answer a WHERE a.countvotes = :countvotes"),
    @NamedQuery(name = "Answer.findByCreatedtime", query = "SELECT a FROM Answer a WHERE a.createdtime = :createdtime")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "aid")
    private Integer aid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qid")
    private int qid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "countvotes")
    private int countvotes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdtime;

    public Answer() {
    }

    public Answer(Integer aid) {
        this.aid = aid;
    }

    public Answer(Integer aid, int qid, int uid, String content, int countvotes, Date createdtime) {
        this.aid = aid;
        this.qid = qid;
        this.uid = uid;
        this.content = content;
        this.countvotes = countvotes;
        this.createdtime = createdtime;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCountvotes() {
        return countvotes;
    }

    public void setCountvotes(int countvotes) {
        this.countvotes = countvotes;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webservice.Answer[ aid=" + aid + " ]";
    }
    
}
