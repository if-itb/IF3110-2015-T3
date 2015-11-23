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
    @XmlElement(name = "uid", required = true)
    private int uid;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "email", required = true)
    private String email;
    @XmlElement(name = "password", required = true)
    private String password;


    public User(){
        uid = 0;
    }

    public User(int _uid, String _name, String _email, String _password){
        this.uid = _uid;
        this.name = _name;
        this.email = _email;
        this.password = _password;
    }


}