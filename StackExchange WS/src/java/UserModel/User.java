/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

/**
 *
 * @author User
 */
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "User")
public class User {

  @XmlElement(name="id", required=true)
  private int id;
  @XmlElement(name="email", required=true)
  private String email;
  @XmlElement(name="password", required=true)
  private String password;
  @XmlElement(name="name", required=true)
  private String name;
    
  public User() {
    id = 0;
  }
  
  public User(int id, String email, String password, String name) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
  }
  
  /* Getter */
  public int getId() {
    return id;
  }  
  public String getEmail() {
    return email;
  }
  public String getPassword() {
    return password;
  }
  public String getName() {
    return name;
  }
  
}
