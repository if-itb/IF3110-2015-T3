/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.user_package;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;

@XmlRootElement(name="User")
/**
 *
 * @author ryanyonata
 */
public class User {
    @XmlElement(name="id", required=true)
    private int id;
    
    @XmlElement(name="name", required=true)
    private String name;
    
    @XmlElement(name="email", required=true)
    private String email;
    
    @XmlElement(name="password", required=true)
    private String password;
    
    public User() {
        id=0;
        name = "";
        email = "";
        password = "";
    }
    
    public User(int nid, String nname, String nemail, String npass){
        this.id= nid;
        this.name = nname;
        this.email = nemail;
        //this.password = DigestUtils.sha1Hex(npass);
        this.password = npass;
    }
    
    public int getUserId() {
        return id;
    }
    
    public String getUserName() {
        return name;
    }
    
    public String getUserEmail() {
        return email;
    }
    
    public String getUserPassword() {
        return password;
    }
    
    public void setUserId(int id) {
        this.id=id;
    }
    
    public void setUserEmail(String email){
        this.email = email;
    }
    
    public void setUserPassword(String password){
        //this.password = DigestUtils.sha1Hex(password);
        this.password = password;
    }
    
}