/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import DatabaseAdapter.database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author user
 */
@WebService(serviceName = "UserWS")
public class UserWS {
    
    database DB = new database();
    
    Connection conn = DB.connect();
    
    @WebMethod(operationName = "getUserByUID")
    @WebResult(name="User")
    public User getUserByUID(@WebParam(name = "uid") int uid) {
        
        User user = new User();
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user where User_ID = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, uid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            rs.next();
            user = new User(rs.getInt("User_ID"),                     
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("Password")
                            );
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    @WebMethod(operationName = "createUser")    
    @WebResult(name="UserID")
    public int createUser(@WebParam(name = "name") String name,  @WebParam(name = "email") String email, @WebParam(name = "password") String pw) {
        int uid = 0;
        // Call Identity Service
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO user(Name, Email, Password) VALUES (?,?,?)";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            dbStatement.setString(1, name);
            dbStatement.setString(2, email);            
            dbStatement.setString(3, pw);
            dbStatement.executeUpdate();
            ResultSet rs = dbStatement.getGeneratedKeys();
            while (rs.next()) {
		uid = rs.getInt(1);
            }            
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return uid;
    }
}
