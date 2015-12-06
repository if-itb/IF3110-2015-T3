/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Afrizal
 */
@Entity
@Table(name = "vote_question")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "VoteQuestion.findAll", query = "SELECT v FROM VoteQuestion v"),
  @NamedQuery(name = "VoteQuestion.findById", query = "SELECT v FROM VoteQuestion v WHERE v.id = :id"),
  @NamedQuery(name = "VoteQuestion.findByType", query = "SELECT v FROM VoteQuestion v WHERE v.type = :type")})
public class VoteQuestion implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "type")
  private int type;
  @JoinColumn(name = "uid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User uid;
  @JoinColumn(name = "qid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Question qid;

  public VoteQuestion() {
  }

  public VoteQuestion(Integer id) {
    this.id = id;
  }

  public VoteQuestion(Integer id, int type) {
    this.id = id;
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public User getUid() {
    return uid;
  }

  public void setUid(User uid) {
    this.uid = uid;
  }

  public Question getQid() {
    return qid;
  }

  public void setQid(Question qid) {
    this.qid = qid;
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
    if (!(object instanceof VoteQuestion)) {
      return false;
    }
    VoteQuestion other = (VoteQuestion) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "wbd.commentvote.VoteQuestion[ id=" + id + " ]";
  }
  
}
