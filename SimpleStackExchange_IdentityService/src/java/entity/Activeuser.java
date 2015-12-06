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
 * @author mfikria
 */
@Entity
@Table(name = "activeuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activeuser.findAll", query = "SELECT a FROM Activeuser a"),
    @NamedQuery(name = "Activeuser.findByToken", query = "SELECT a FROM Activeuser a WHERE a.token = :token"),
    @NamedQuery(name = "Activeuser.findByUid", query = "SELECT a FROM Activeuser a WHERE a.uid = :uid"),
    @NamedQuery(name = "Activeuser.findByCreatedtime", query = "SELECT a FROM Activeuser a WHERE a.createdtime = :createdtime")})
public class Activeuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdtime;

    public Activeuser() {
    }

    public Activeuser(String token) {
        this.token = token;
    }

    public Activeuser(String token, int uid, Date createdtime) {
        this.token = token;
        this.uid = uid;
        this.createdtime = createdtime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activeuser)) {
            return false;
        }
        Activeuser other = (Activeuser) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Activeuser[ token=" + token + " ]";
    }
    
}
