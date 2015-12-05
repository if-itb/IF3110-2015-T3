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
 * @author Afrizal
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
  @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id"),
  @NamedQuery(name = "Question.findByDate", query = "SELECT q FROM Question q WHERE q.date = :date"),
  @NamedQuery(name = "Question.findByVote", query = "SELECT q FROM Question q WHERE q.vote = :vote")})
public class Question implements Serializable {

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
  @Column(name = "topic")
  private String topic;
  @Basic(optional = false)
  @NotNull
  @Lob
  @Size(min = 1, max = 65535)
  @Column(name = "content")
  private String content;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "qid")
  private Collection<Answer> answerCollection;
  @JoinColumn(name = "uid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User uid;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "qid")
  private Collection<VoteQuestion> voteQuestionCollection;

  public Question() {
  }

  public Question(Integer id) {
    this.id = id;
  }

  public Question(Integer id, Date date, int vote, String topic, String content) {
    this.id = id;
    this.date = date;
    this.vote = vote;
    this.topic = topic;
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

  @XmlTransient
  public Collection<Answer> getAnswerCollection() {
    return answerCollection;
  }

  public void setAnswerCollection(Collection<Answer> answerCollection) {
    this.answerCollection = answerCollection;
  }

  public User getUid() {
    return uid;
  }

  public void setUid(User uid) {
    this.uid = uid;
  }

  @XmlTransient
  public Collection<VoteQuestion> getVoteQuestionCollection() {
    return voteQuestionCollection;
  }

  public void setVoteQuestionCollection(Collection<VoteQuestion> voteQuestionCollection) {
    this.voteQuestionCollection = voteQuestionCollection;
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
    if (!(object instanceof Question)) {
      return false;
    }
    Question other = (Question) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "wbd.commentvote.Question[ id=" + id + " ]";
  }
  
}
