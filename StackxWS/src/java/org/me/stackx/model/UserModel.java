/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.stackx.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import static org.me.stackx.model.QuestionModel.DB_URL;
import org.me.stackx.module.Question;
import org.me.stackx.module.User;

/**
 *
 * @author natanelia
 */
public class UserModel {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/stackx";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    
    public static String register(String name, String email, String password) {
        //TODO: request to oauth to get who is the requester
        String r = "ERROR";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

           //STEP 4: Execute a query
           String sql;
           sql = "INSERT INTO user (name, email, password, create_date) VALUES(?, ?, ?, CURRENT_TIMESTAMP)";
           stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           stmt.setString(1, name);
           stmt.setString(2, email);
           stmt.setString(3, password);

           int affectedRows = stmt.executeUpdate();
           if (affectedRows == 0) {
               r = "ERROR";
           } else {
               r = "SUCCESS";
           }
           stmt.close();
           conn.close();
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch(Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        } finally {
           //finally block used to close resources
           try {
              if (stmt!=null)
                stmt.close();
           } catch(SQLException se2) { }// nothing we can do
           try {
              if (conn!=null) {
                conn.close();
              }
           } catch (SQLException se) {
                se.printStackTrace();
           }//end finally try
        }//end try
        
        return r;
    }
    
    public static User getById(int id) {
        User r = null;
        Connection conn = null;
        Statement stmt = null;
        try {
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

           //STEP 4: Execute a query
           stmt = (Statement) conn.createStatement();
           String sql;
           sql = "SELECT user_id, name, email, password, create_date FROM user WHERE user_id=" + id;
            //STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                rs.next();
                //Retrieve by column name
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Timestamp createDate = rs.getTimestamp("create_date");
                
                r = new User(userId, name, email, password, createDate.toString());
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
           stmt.close();
           conn.close();
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch(Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        } finally {
           //finally block used to close resources
           try {
              if (stmt!=null)
                 stmt.close();
           } catch(SQLException se2){ }// nothing we can do
           try {
              if (conn!=null)
                 conn.close();
           } catch (SQLException se) {
              se.printStackTrace();
           }//end finally try
        }//end try
        return r;
    }
}
