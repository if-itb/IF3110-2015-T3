/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*; 

/**
 *
 * @author Luqman A. Siswanto
 */

@XmlRootElement(name = "User")
public class User {
  @XmlElement(name="id", required=true)  
  private int id;  
  @XmlElement(name="name", required=true)  
  private String name;   
  @XmlElement(name="email", required=true)   
  private String email;   
  @XmlElement(name="pass", required=true)   
  private String pass; 
  
  public User() {
    
  }
  public User(int id, String name, String email, String pass) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.pass = pass;
  }
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }
  public String getPass() {
    return pass;
  }
}
