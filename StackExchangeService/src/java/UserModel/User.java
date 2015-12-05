/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User") 
public class User {
    @XmlElement(name="username", required=true)
    private String username;
    @XmlElement(name="password", required=true)
    private String password;
    @XmlElement(name="nama", required=true)
    private String nama;
    @XmlElement(name="email", required=true)
    private String email;
    
    public User(String username, String password, String nama, String email){
        this.username = username;
        this.email= email;
        this.nama = nama;
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
