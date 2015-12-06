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
 * @author mfikria
 */
@Entity
@Table(name = "questionvoter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questionvoter.findAll", query = "SELECT q FROM Questionvoter q"),
    @NamedQuery(name = "Questionvoter.findById", query = "SELECT q FROM Questionvoter q WHERE q.id = :id"),
    @NamedQuery(name = "Questionvoter.findByQid", query = "SELECT q FROM Questionvoter q WHERE q.qid = :qid"),
    @NamedQuery(name = "Questionvoter.findByUid", query = "SELECT q FROM Questionvoter q WHERE q.uid = :uid"),
    @NamedQuery(name = "Questionvoter.findByValue", query = "SELECT q FROM Questionvoter q WHERE q.value = :value"),
    @NamedQuery(name = "Questionvoter.findByCreatedtime", query = "SELECT q FROM Questionvoter q WHERE q.createdtime = :createdtime")})
public class Questionvoter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qid")
    private int qid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdtime;

    public Questionvoter() {
    }

    public Questionvoter(Integer id) {
        this.id = id;
    }

    public Questionvoter(Integer id, int qid, int uid, String value, Date createdtime) {
        this.id = id;
        this.qid = qid;
        this.uid = uid;
        this.value = value;
        this.createdtime = createdtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionvoter)) {
            return false;
        }
        Questionvoter other = (Questionvoter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Questionvoter[ id=" + id + " ]";
    }
    
}
