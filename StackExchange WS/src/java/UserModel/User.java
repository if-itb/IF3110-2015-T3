/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Vanji
 */

@XmlRootElement(name = "User")
public class User {
    @XmlElement(name = "u_id", required = true)
    private int u_id;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "email", required = true)
    private String email;
    @XmlElement(name = "password", required = true)
    private String password;


    public User(){
        u_id = 0;
    }

    public User(int _uid, String _name, String _email, String _password){
        this.u_id = _uid;
        this.name = _name;
        this.email = _email;
        this.password = _password;
    }

    public int getUID(){
        return u_id;
    }
    public String getUName(){
        return name;
    }
    public String getUEmail(){
        return email;
    }
    public String getUPassword(){
        return password;
    }   
}