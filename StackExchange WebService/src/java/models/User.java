/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.xml.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author jessica
 */
@XmlRootElement(name="User")
public class User {
    @XmlElement(name="u_id", required=true)
    private int uId;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="password", required=true)
    private String password;
    
    public User(){
        
    }
    
    public User(int uId, String name, String email, String password){
        this.uId = uId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getuId() {
        return uId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    
}
