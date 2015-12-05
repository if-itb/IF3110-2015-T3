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
@Table(name = "token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t"),
    @NamedQuery(name = "Token.findByTId", query = "SELECT t FROM Token t WHERE t.tId = :tId"),
    @NamedQuery(name = "Token.findByTToken", query = "SELECT t FROM Token t WHERE t.tToken = :tToken"),
    @NamedQuery(name = "Token.findByTTime", query = "SELECT t FROM Token t WHERE t.tTime = :tTime"),
    @NamedQuery(name = "Token.findByUId", query = "SELECT t FROM Token t WHERE t.uId = :uId")})
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "t_id")
    private Integer tId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "t_token")
    private String tToken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "t_time")
    @Temporal(TemporalType.DATE)
    private Date tTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "u_id")
    private int uId;

    public Token() {
    }

    public Token(Integer tId) {
        this.tId = tId;
    }

    public Token(Integer tId, String tToken, Date tTime, int uId) {
        this.tId = tId;
        this.tToken = tToken;
        this.tTime = tTime;
        this.uId = uId;
    }

    public Integer getTId() {
        return tId;
    }

    public void setTId(Integer tId) {
        this.tId = tId;
    }

    public String getTToken() {
        return tToken;
    }

    public void setTToken(String tToken) {
        this.tToken = tToken;
    }

    public Date getTTime() {
        return tTime;
    }

    public void setTTime(Date tTime) {
        this.tTime = tTime;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tId != null ? tId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.tId == null && other.tId != null) || (this.tId != null && !this.tId.equals(other.tId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnswerRS.Token[ tId=" + tId + " ]";
    }
    
}
