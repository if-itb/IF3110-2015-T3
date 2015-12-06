/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Validation.ValidationToken;
import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Ahmad Naufal Farhan
 */
@WebService(serviceName = "UserWS")
public class UserWS {
    Connection conn = DB.getConnection();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addUser")
    public int addUser(@WebParam(name = "name") String name,
                        @WebParam(name = "email") String email,
                        @WebParam(name = "password") String password) {
        
        int res = 0;
        try {
            // check if the user with the email is already registered
            String sql = "SELECT email FROM users WHERE email = ?"; 
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, email);
            ResultSet rs = pstat.executeQuery(); 
            
            // if email not existed, insert the user
            if (!rs.isBeforeFirst() && (email != null || email.equals(""))) {
                
                // set the prepared statement by the query and enter the value of where clause
                try (Statement st = conn.createStatement()) {
                   
                    String query = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
                    
                    try (PreparedStatement pst = conn.prepareStatement(query)) {
                        pst.setString(1, name);
                        pst.setString(2, email);
                        pst.setString(3, password);
                        
                        res = pst.executeUpdate();
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "logoutUser")
    public int logoutUser(@WebParam(name = "token") String token,
                           @WebParam(name = "ipAddress") String ip,
                           @WebParam(name = "useragent") String uagent) {
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token, ip, uagent); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
        
            try (Statement st = conn.createStatement()) {

                String query = "DELETE FROM `tokens` WHERE uid = ?";

                // set the prepared statement by the query and enter the value of where clause
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setLong(1, user_id);
                    // execute update
                    res = pst.executeUpdate();
                    if (res > 0)
                        res = ValidationToken.AUTH_VALID;
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // else: token is invalid, deny request
            res = ValidationToken.AUTH_INVALID;
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUser")
    @WebResult(name = "User")
    public User getUser(@WebParam(name = "user_id") int user_id) {
        User user = null;
        
        try (Statement st = conn.createStatement()) { 
            String query = "SELECT * FROM users WHERE uid = ?";
                
            // set the prepared statement by the query and enter the value of where clause
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, user_id);
            
            // create a new question object with the result
            try (ResultSet res = pst.executeQuery()) {
                if (res.next())
                    user = new User(res.getInt("uid"), res.getString("name"), res.getString("email"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserByToken")
    @WebResult(name = "user")
    public User getUserByToken(@WebParam(name = "token") String token) {
        User user = null;
        
        try (Statement st = conn.createStatement()) { 
            String query = "SELECT * FROM tokens NATURAL JOIN users WHERE token_str = ?";
                
            // set the prepared statement by the query and enter the value of where clause
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, token);
            
            // create a new question object with the result
            try (ResultSet res = pst.executeQuery()) {
                if (res.next())
                    user = new User(res.getInt("uid"), res.getString("name"), res.getString("email"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    
}
