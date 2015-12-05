/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.ResultSet;
import java.util.Random;

/**
 *
 * @author Luqman A. Siswanto
 */
@WebService(serviceName = "UserWS")
public class UserWS {
  DB database;
  
  public UserWS() throws Throwable {
    database = new DB();
  }
  
  /**
   * 
   * @param name
   * @param email
   * @param pass 
   */
  @WebMethod(operationName = "createUser")
  @WebResult(name="addUser")
  public void createUser(@WebParam(name = "name") String name,@WebParam(name = "email") String email,@WebParam(name = "pass") String pass) {
    String query = "INSERT INTO `user` (`name`, `email`, `pass`) VALUES ('"+name+"','"+email+"', '"+pass+"')";
    database.executeQuery(query);
  }
  
  @WebMethod(operationName = "getName")
  @WebResult(name="gettingName")
  public String getName(@WebParam(name = "id") int id) {
    String query = "SELECT name FROM `user` WHERE id=" + id;
    ResultSet rs = database.getResultQuery(query);
    String ret = "";
    try {
      rs.next();
      ret =  rs.getString("name");
    } catch(Throwable e) {
      e.printStackTrace();
    }
    return ret;
  }
  
  @WebMethod(operationName = "getUIDByEmail")
  @WebResult(name="getUIDByEmail")
  public int getUIDByEmail(@WebParam(name="email") String email) {
    String query = "SELECT id FROM `user` WHERE email='" + email + "'";
    ResultSet rs = database.getResultQuery(query);
    int id = 0;
    try {
      rs.next();
      id =  rs.getInt("id");
    } catch(Throwable e) {
      e.printStackTrace();
    }
    return id;
  }
  
  @WebMethod(operationName = "getUID")
  @WebResult(name="getUID")
  public int getUID(@WebParam(name="token" )String token) {
    String query = "SELECT `uid` FROM `token` WHERE `val`='" + token + "'";
    ResultSet rs = database.getResultQuery(query);
    int uid = 0;
    try {
      rs.next();
      uid = rs.getInt("uid");
    } catch(Throwable e) {
    }
    return uid;
  }

  String randomString( int len ){
    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rnd = new Random();
    StringBuilder sb = new StringBuilder( len );
    for( int i = 0; i < len; i++ ) 
      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
    return sb.toString();
  }

  @WebMethod(operationName = "createToken")
  @WebResult(name="createToken")
  public String createToken(@WebParam(name="id") int id) {
    String random = randomString(50);
    String query = "INSERT INTO `token` (`uid`, `val`, `expires`) VALUES ("+id+",'"+random+"', 0)";
    database.executeQuery(query);
    return random;
  }
  
  @WebMethod(operationName = "match")
  @WebResult(name="match")
  public boolean match(String email, String pass) {
    String query = "SELECT * FROM `user` WHERE `email`='" + email + "' AND `pass`='" + pass + "'";
    ResultSet rs = database.getResultQuery(query);
    try {
      return rs.next();
    } catch(Throwable e) {
    }
    return false; 
  }
  
  @WebMethod(operationName = "emailDone")
  @WebResult(name="emailDone")
  public boolean emailDone(@WebParam(name="id") String email) {
    String query = "SELECT * FROM user WHERE email='" + email + "'";
    ResultSet rs = database.getResultQuery(query);
    try {
      return rs.next();
    } catch(Throwable e) {
    }
    return false;
  }
}
