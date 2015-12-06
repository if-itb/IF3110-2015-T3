/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Afrizal
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
  @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
  @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
  @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
  @NamedQuery(name = "User.findByPass", query = "SELECT u FROM User u WHERE u.pass = :pass")})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name")
  private String name;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "email")
  private String email;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "pass")
  private String pass;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "uid")
  private Collection<Answer> answerCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "uid")
  private Collection<Question> questionCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "uid")
  private Collection<VoteAnswer> voteAnswerCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "uid")
  private Collection<VoteQuestion> voteQuestionCollection;

  public User() {
  }

  public User(Integer id) {
    this.id = id;
  }

  public User(Integer id, String name, String email, String pass) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.pass = pass;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  @XmlTransient
  public Collection<Answer> getAnswerCollection() {
    return answerCollection;
  }

  public void setAnswerCollection(Collection<Answer> answerCollection) {
    this.answerCollection = answerCollection;
  }

  @XmlTransient
  public Collection<Question> getQuestionCollection() {
    return questionCollection;
  }

  public void setQuestionCollection(Collection<Question> questionCollection) {
    this.questionCollection = questionCollection;
  }

  @XmlTransient
  public Collection<VoteAnswer> getVoteAnswerCollection() {
    return voteAnswerCollection;
  }

  public void setVoteAnswerCollection(Collection<VoteAnswer> voteAnswerCollection) {
    this.voteAnswerCollection = voteAnswerCollection;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "wbd.commentvote.User[ id=" + id + " ]";
  }
  
}
