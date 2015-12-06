/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gazandic
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
  @NamedQuery(name = "Answer.findById", query = "SELECT a FROM Answer a WHERE a.id = :id"),
  @NamedQuery(name = "Answer.findByDate", query = "SELECT a FROM Answer a WHERE a.date = :date"),
  @NamedQuery(name = "Answer.findByVote", query = "SELECT a FROM Answer a WHERE a.vote = :vote")})
public class Answer implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;
  @Basic(optional = false)
  @NotNull
  @Column(name = "vote")
  private int vote;
  @Basic(optional = false)
  @NotNull
  @Lob
  @Size(min = 1, max = 65535)
  @Column(name = "content")
  private String content;
  @JoinColumn(name = "qid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Question qid;
  @JoinColumn(name = "uid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User uid;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "aid")
  private Collection<VoteAnswer> voteAnswerCollection;

  public Answer() {
  }

  public Answer(Integer id) {
    this.id = id;
  }

  public Answer(Integer id, Date date, int vote, String content) {
    this.id = id;
    this.date = date;
    this.vote = vote;
    this.content = content;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getVote() {
    return vote;
  }

  public void setVote(int vote) {
    this.vote = vote;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Question getQid() {
    return qid;
  }

  public void setQid(Question qid) {
    this.qid = qid;
  }

  public User getUid() {
    return uid;
  }

  public void setUid(User uid) {
    this.uid = uid;
  }

  @XmlTransient
  public Collection<VoteAnswer> getVoteAnswerCollection() {
    return voteAnswerCollection;
  }

  public void setVoteAnswerCollection(Collection<VoteAnswer> voteAnswerCollection) {
    this.voteAnswerCollection = voteAnswerCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Answer)) {
      return false;
    }
    Answer other = (Answer) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "wbd.commentvote.Answer[ id=" + id + " ]";
  }
  
}
