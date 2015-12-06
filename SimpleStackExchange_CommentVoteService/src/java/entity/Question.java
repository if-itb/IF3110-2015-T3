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
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQid", query = "SELECT q FROM Question q WHERE q.qid = :qid"),
    @NamedQuery(name = "Question.findByUid", query = "SELECT q FROM Question q WHERE q.uid = :uid"),
    @NamedQuery(name = "Question.findByTopic", query = "SELECT q FROM Question q WHERE q.topic = :topic"),
    @NamedQuery(name = "Question.findByCreatedtime", query = "SELECT q FROM Question q WHERE q.createdtime = :createdtime"),
    @NamedQuery(name = "Question.findByCountanswers", query = "SELECT q FROM Question q WHERE q.countanswers = :countanswers"),
    @NamedQuery(name = "Question.findByCountvotes", query = "SELECT q FROM Question q WHERE q.countvotes = :countvotes"),
    @NamedQuery(name = "Question.findByCountcomments", query = "SELECT q FROM Question q WHERE q.countcomments = :countcomments")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qid")
    private Integer qid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "topic")
    private String topic;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdtime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "countanswers")
    private int countanswers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "countvotes")
    private int countvotes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "countcomments")
    private int countcomments;

    public Question() {
    }

    public Question(Integer qid) {
        this.qid = qid;
    }

    public Question(Integer qid, int uid, String topic, String content, Date createdtime, int countanswers, int countvotes, int countcomments) {
        this.qid = qid;
        this.uid = uid;
        this.topic = topic;
        this.content = content;
        this.createdtime = createdtime;
        this.countanswers = countanswers;
        this.countvotes = countvotes;
        this.countcomments = countcomments;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public int getCountanswers() {
        return countanswers;
    }

    public void setCountanswers(int countanswers) {
        this.countanswers = countanswers;
    }

    public int getCountvotes() {
        return countvotes;
    }

    public void setCountvotes(int countvotes) {
        this.countvotes = countvotes;
    }

    public int getCountcomments() {
        return countcomments;
    }

    public void setCountcomments(int countcomments) {
        this.countcomments = countcomments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qid != null ? qid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.qid == null && other.qid != null) || (this.qid != null && !this.qid.equals(other.qid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Question[ qid=" + qid + " ]";
    }
    
}
