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
 * @author gazandic
 */
@Entity
@Table(name = "vote_answer")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "VoteAnswer.findAll", query = "SELECT v FROM VoteAnswer v"),
  @NamedQuery(name = "VoteAnswer.findById", query = "SELECT v FROM VoteAnswer v WHERE v.id = :id"),
  @NamedQuery(name = "VoteAnswer.findByType", query = "SELECT v FROM VoteAnswer v WHERE v.type = :type")})
public class VoteAnswer implements Serializable {
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
  @JoinColumn(name = "aid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Answer aid;

  public VoteAnswer() {
  }

  public VoteAnswer(Integer id) {
    this.id = id;
  }

  public VoteAnswer(Integer id, int type) {
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

  public Answer getAid() {
    return aid;
  }

  public void setAid(Answer aid) {
    this.aid = aid;
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
    if (!(object instanceof VoteAnswer)) {
      return false;
    }
    VoteAnswer other = (VoteAnswer) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "wbd.commentvote.VoteAnswer[ id=" + id + " ]";
  }
  
}
