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
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAId", query = "SELECT a FROM Answer a WHERE a.aId = :aId"),
    @NamedQuery(name = "Answer.findByAVote", query = "SELECT a FROM Answer a WHERE a.aVote = :aVote"),
    @NamedQuery(name = "Answer.findByQId", query = "SELECT a FROM Answer a WHERE a.qId = :qId"),
    @NamedQuery(name = "Answer.findByUId", query = "SELECT a FROM Answer a WHERE a.uId = :uId"),
    @NamedQuery(name = "Answer.findByUName", query = "SELECT a FROM Answer a WHERE a.uName = :uName"),
    @NamedQuery(name = "Answer.findByAEmail", query = "SELECT a FROM Answer a WHERE a.aEmail = :aEmail"),
    @NamedQuery(name = "Answer.findByAContent", query = "SELECT a FROM Answer a WHERE a.aContent = :aContent"),
    @NamedQuery(name = "Answer.findByADate", query = "SELECT a FROM Answer a WHERE a.aDate = :aDate")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "a_id")
    private Integer aId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_vote")
    private int aVote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "q_id")
    private int qId;
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
    @Size(min = 1, max = 50)
    @Column(name = "a_email")
    private String aEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "a_content")
    private String aContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aDate;

    public Answer() {
    }

    public Answer(Integer aId) {
        this.aId = aId;
    }

    public Answer(Integer aId, int aVote, int qId, int uId, String uName, String aEmail, String aContent, Date aDate) {
        this.aId = aId;
        this.aVote = aVote;
        this.qId = qId;
        this.uId = uId;
        this.uName = uName;
        this.aEmail = aEmail;
        this.aContent = aContent;
        this.aDate = aDate;
    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public int getAVote() {
        return aVote;
    }

    public void setAVote(int aVote) {
        this.aVote = aVote;
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
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

    public String getAEmail() {
        return aEmail;
    }

    public void setAEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getAContent() {
        return aContent;
    }

    public void setAContent(String aContent) {
        this.aContent = aContent;
    }

    public Date getADate() {
        return aDate;
    }

    public void setADate(Date aDate) {
        this.aDate = aDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aId != null ? aId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.aId == null && other.aId != null) || (this.aId != null && !this.aId.equals(other.aId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnswerRS.Answer[ aId=" + aId + " ]";
    }
    
}
