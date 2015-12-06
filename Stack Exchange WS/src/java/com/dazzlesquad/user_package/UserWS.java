/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.user_package;

import com.dazzlesquad.database_console.DBConnect;
import com.dazzlesquad.answer_package.Answer;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
/**
 *
 * @author ryanyonata
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    Connection conn; 

    public UserWS() throws SQLException {
        DBConnect db = new DBConnect();
        conn = db.connect();
    }

    /**
     * Web service operation
     */
   
    
    @WebMethod(operationName = "getUserById")
    @WebResult(name="User")
    public User getUserById(@WebParam(name = "id") int id) {
        User userResult = null;
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE id=?";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,id);
            
            ResultSet result = dbStatement.executeQuery();
            
            if (result.next())
            {  
               userResult = new User(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password")); 
            }
            else {
               userResult = new User();
            }
            
            result.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userResult;
    }
    
    
    @WebMethod(operationName = "registerUser")
    @WebResult(name = "NewUser")
    public int registerUser(@WebParam(name = "user") User user) {
        int insertsuccessful = 1; // nanti diganti fungsi validasi

        try {
            Statement statement1 = conn.createStatement();
            String sql1;
            sql1 = "SELECT id FROM user WHERE email=?";

            PreparedStatement dbStatement1 = conn.prepareStatement(sql1);
            dbStatement1.setString(1, user.getUserEmail());

            ResultSet result = dbStatement1.executeQuery();
            if (result.next()) {
                insertsuccessful = 0;
            } else {
                insertsuccessful = 1;
            }

            statement1.close();

            if (insertsuccessful == 1) {
                Statement statement2 = conn.createStatement();
                String sql2 = "INSERT INTO user (name, email, password) VALUES (?, ?, SHA1(?))";

                PreparedStatement dbStatement2 = conn.prepareStatement(sql2);
                dbStatement2.setString(1, user.getUserName());
                dbStatement2.setString(2, user.getUserEmail());
                dbStatement2.setString(3, user.getUserPassword());

                dbStatement2.executeUpdate();

                statement2.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return insertsuccessful;
    }

    
}