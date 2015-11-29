/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmad Naufal Farhan
 */
@XmlRootElement(name="User")
public class User {
    @XmlElement(name="uid", required=true)
    private int uid;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="email", required=true)
    private String email;
    
    /**
     * Constructor for Register
     */
    public User() {
       uid = 0;
    }
    
    public User(int _id, String _name, String _email) {
        uid = _id;
        name = _name;
        email = _email;
    }

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
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
}
