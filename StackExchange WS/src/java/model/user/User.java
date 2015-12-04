/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author Venny
 */

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "User")
public class User {
    
    private String name;
    private String email;
    private String password;
    private int user_id;
    
    public User(){
        user_id=0;
    }
    
    public User(int user_id, String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_id = user_id;
    }
    
    
    @XmlElement(name="user_id", required=true)
    public int getUserID(){
        return user_id;
    }
    
    
    @XmlElement(name="name", required=true)
    public String getName() {
        return name;
    }
    
    @XmlElement(name="password", required=true)
    public String getPassword(){
        return password;
    }
    
    @XmlElement(name="email", required=true)
    public String getEmail() {
        return email;
    }

    public void setUserID(int uid) {
        user_id = uid;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
