/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William Sentosa
 */
@XmlRootElement(name = "User")
public class User {
    @XmlElement(name="user_id", required=true)
    public int id;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="password", required=true)
    private String password;
    
    public User() {
        name = null;
        email = null;
        password = null;
    }
    
    public User(int id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }
     
    public int getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
}
