/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;


public class DB {
  final private String SRV = "jdbc:mysql://localhost/";
  final private String USR = "root"; 
  final private String PWD = "";
  final private String DB = "wbd2";
  private Connection conn;
  private Statement stmt;
  public DB() throws Throwable {
    Class.forName("com.mysql.jdbc.Driver");   // register JDBC driver
    conn = DriverManager.getConnection(SRV + DB, USR, PWD);  // open connection
    stmt = conn.createStatement();    // initialize statement for query
  }
  
  public void executeQuery(String query) {
    try {
      PreparedStatement dbStatement =  conn.prepareStatement(query); 
      dbStatement.executeUpdate(query);
      stmt.close();
    } 
    catch (Throwable ex) {
      ex.printStackTrace();
    }
  }
  
  public ResultSet getResultQuery(String query) {
    ResultSet res = null;
    try {
      PreparedStatement dbStatement =  conn.prepareStatement(query);
      res = dbStatement.executeQuery(query);
    } catch(Exception ex) {
      ex.printStackTrace();
    }
    return res;
  }
}
