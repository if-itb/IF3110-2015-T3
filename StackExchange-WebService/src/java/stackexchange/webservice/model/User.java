/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.model;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author fauzanrifqy
 */
@XmlRootElement(name="User")
public class User {
    @XmlElement(name="id", required=true)
    private int id;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="password", required=true)
    private String password;
    
    public User(){
        id = -1;
        email = "";
    }
    
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public User(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the token
     */
    public String getPassword() {
        return password;
    }
}
