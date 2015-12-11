/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Afrizal
 */
@Entity
@Table(name = "token")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t"),
  @NamedQuery(name = "Token.findByVal", query = "SELECT t FROM Token t WHERE t.val = :val"),
  @NamedQuery(name = "Token.findByExpires", query = "SELECT t FROM Token t WHERE t.expires = :expires")})
public class Token implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 256)
  @Column(name = "val")
  private String val;
  @Basic(optional = false)
  @NotNull
  @Column(name = "expires")
  private int expires;
  @JoinColumn(name = "uid", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User uid;

  public Token() {
  }

  public Token(String val) {
    this.val = val;
  }

  public Token(String val, int expires) {
    this.val = val;
    this.expires = expires;
  }

  public String getVal() {
    return val;
  }

  public void setVal(String val) {
    this.val = val;
  }

  public void appendVal(String val) {
    String s = val+this.val;
    this.val =s;
  }

  public int getExpires() {
    return expires;
  }

  public void setExpires(int expires) {
    this.expires = expires;
  }

  public User getUid() {
    return uid;
  }

  public void setUid(User uid) {
    this.uid = uid;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (val != null ? val.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Token)) {
      return false;
    }
    Token other = (Token) object;
    return !((this.val == null && other.val != null) || (this.val != null && !this.val.equals(other.val)));
  }

  @Override
  public String toString() {
    return "wbd.identity.Token[ val=" + val + " ]";
  }
  
}
