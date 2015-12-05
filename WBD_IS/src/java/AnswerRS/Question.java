/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerRS;

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
 * @author Vanji
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQId", query = "SELECT q FROM Question q WHERE q.qId = :qId"),
    @NamedQuery(name = "Question.findByUId", query = "SELECT q FROM Question q WHERE q.uId = :uId"),
    @NamedQuery(name = "Question.findByUName", query = "SELECT q FROM Question q WHERE q.uName = :uName"),
    @NamedQuery(name = "Question.findByQEmail", query = "SELECT q FROM Question q WHERE q.qEmail = :qEmail"),
    @NamedQuery(name = "Question.findByQTopic", query = "SELECT q FROM Question q WHERE q.qTopic = :qTopic"),
    @NamedQuery(name = "Question.findByQVote", query = "SELECT q FROM Question q WHERE q.qVote = :qVote"),
    @NamedQuery(name = "Question.findByQDate", query = "SELECT q FROM Question q WHERE q.qDate = :qDate")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "q_id")
    private Integer qId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "u_id")
    private int uId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "u_name")
    private String uName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "q_email")
    private String qEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "q_topic")
    private String qTopic;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "q_content")
    private String qContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "q_vote")
    private int qVote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "q_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date qDate;

    public Question() {
    }

    public Question(Integer qId) {
        this.qId = qId;
    }

    public Question(Integer qId, int uId, String uName, String qEmail, String qTopic, String qContent, int qVote, Date qDate) {
        this.qId = qId;
        this.uId = uId;
        this.uName = uName;
        this.qEmail = qEmail;
        this.qTopic = qTopic;
        this.qContent = qContent;
        this.qVote = qVote;
        this.qDate = qDate;
    }

    public Integer getQId() {
        return qId;
    }

    public void setQId(Integer qId) {
        this.qId = qId;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getQEmail() {
        return qEmail;
    }

    public void setQEmail(String qEmail) {
        this.qEmail = qEmail;
    }

    public String getQTopic() {
        return qTopic;
    }

    public void setQTopic(String qTopic) {
        this.qTopic = qTopic;
    }

    public String getQContent() {
        return qContent;
    }

    public void setQContent(String qContent) {
        this.qContent = qContent;
    }

    public int getQVote() {
        return qVote;
    }

    public void setQVote(int qVote) {
        this.qVote = qVote;
    }

    public Date getQDate() {
        return qDate;
    }

    public void setQDate(Date qDate) {
        this.qDate = qDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qId != null ? qId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.qId == null && other.qId != null) || (this.qId != null && !this.qId.equals(other.qId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnswerRS.Question[ qId=" + qId + " ]";
    }
    
}
