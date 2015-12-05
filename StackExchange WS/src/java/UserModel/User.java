/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
@XmlRootElement(name="User")
public class User {
    @XmlElement(name="id", required=true)
    private int id;
    @XmlElement(name="name", required=true)
    private String name;
    @XmlElement(name="password", required=true)
    private String password;
    @XmlElement(name="email", required=true)
    private String email; 
    public User() {
        id = 0;
    }
    public User(int id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
