/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerRS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanji
 */
@Entity
@Table(name = "vote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vote.findAll", query = "SELECT v FROM Vote v"),
    @NamedQuery(name = "Vote.findByVId", query = "SELECT v FROM Vote v WHERE v.vId = :vId"),
    @NamedQuery(name = "Vote.findByUId", query = "SELECT v FROM Vote v WHERE v.uId = :uId"),
    @NamedQuery(name = "Vote.findByQId", query = "SELECT v FROM Vote v WHERE v.qId = :qId"),
    @NamedQuery(name = "Vote.findByAId", query = "SELECT v FROM Vote v WHERE v.aId = :aId"),
    @NamedQuery(name = "Vote.findByVCount", query = "SELECT v FROM Vote v WHERE v.vCount = :vCount")})
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "v_id")
    private Integer vId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "u_id")
    private int uId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "q_id")
    private int qId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_id")
    private int aId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "v_count")
    private int vCount;

    public Vote() {
    }

    public Vote(Integer vId) {
        this.vId = vId;
    }

    public Vote(Integer vId, int uId, int qId, int aId, int vCount) {
        this.vId = vId;
        this.uId = uId;
        this.qId = qId;
        this.aId = aId;
        this.vCount = vCount;
    }

    public Integer getVId() {
        return vId;
    }

    public void setVId(Integer vId) {
        this.vId = vId;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
        this.qId = qId;
    }

    public int getAId() {
        return aId;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public int getVCount() {
        return vCount;
    }

    public void setVCount(int vCount) {
        this.vCount = vCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vId != null ? vId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.vId == null && other.vId != null) || (this.vId != null && !this.vId.equals(other.vId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AnswerRS.Vote[ vId=" + vId + " ]";
    }
    
}
