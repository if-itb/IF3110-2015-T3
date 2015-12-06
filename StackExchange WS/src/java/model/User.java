/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Acer
 */

@XmlRootElement(name = "User")
public class User {
  @XmlElement(name="id", required=true)
  private int id;
  @XmlElement(name="password", required=true)
  private String password;
  @XmlElement(name="name", required=true)
  private String name;
  @XmlElement(name="email", required=true)
  private String email;
  
  public User() {
    id = 0;
  }
  
  public User(int id, String password, String name, String email) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.email = email;
  }
  
  /* Getter */
  public int getId() {
    return id;
  } 
  
  public String getPassword() {
    return password;
  }
  
  public String getName() {
    return name;
  }
  
  public String getEmail() {
    return email;
  }
  
}
