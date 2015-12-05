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
@Table(name = "answervoter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answervoter.findAll", query = "SELECT a FROM Answervoter a"),
    @NamedQuery(name = "Answervoter.findById", query = "SELECT a FROM Answervoter a WHERE a.id = :id"),
    @NamedQuery(name = "Answervoter.findByAid", query = "SELECT a FROM Answervoter a WHERE a.aid = :aid"),
    @NamedQuery(name = "Answervoter.findByUid", query = "SELECT a FROM Answervoter a WHERE a.uid = :uid"),
    @NamedQuery(name = "Answervoter.findByValue", query = "SELECT a FROM Answervoter a WHERE a.value = :value"),
    @NamedQuery(name = "Answervoter.findByCreatedtime", query = "SELECT a FROM Answervoter a WHERE a.createdtime = :createdtime")})
public class Answervoter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aid")
    private int aid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdtime;

    public Answervoter() {
    }

    public Answervoter(Integer id) {
        this.id = id;
    }

    public Answervoter(Integer id, int aid, int uid, String value, Date createdtime) {
        this.id = id;
        this.aid = aid;
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

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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
        if (!(object instanceof Answervoter)) {
            return false;
        }
        Answervoter other = (Answervoter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Answervoter[ id=" + id + " ]";
    }
    
}
