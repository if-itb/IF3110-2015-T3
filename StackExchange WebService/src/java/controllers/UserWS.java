/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import model.User;

/**
 *
 * @author jessica
 */
@WebService(serviceName = "UserWS")
public class UserWS {
    // Open connection to database
    Connection conn = DatabaseController.connect();
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public Integer register(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        try {
            String selectQuery = "SELECT * FROM user WHERE email = ?";
            
            try (PreparedStatement select = conn.prepareStatement(selectQuery)){
                select.setString(1, email);
                ResultSet result = select.executeQuery();
                
                if(result.next()){
                    return 0; // Has already registered before
                } else {
                    String sql = "INSERT INTO user(email, name, password) VALUE (?, ?, SHA1(?))";
                    try (PreparedStatement statement = conn.prepareStatement(sql)) {
                        statement.setString(1, email);
                        statement.setString(2, name);
                        statement.setString(3, password);
                
                        statement.executeUpdate();
                        return 1; // Successfully registered 
                    }
                }
            }
        }
        catch (SQLException e) {
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            return -1; // Registration failed           
        }        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUser")
    @WebResult(name="User")
    public User getUserClass(@WebParam(name = "u_id") int u_id) {
        User user = new User();
        try {
            String selectQuery = "SELECT * FROM user WHERE u_id = ?";
            PreparedStatement select = conn.prepareStatement(selectQuery);
            select.setInt(1, u_id);
            ResultSet result = select.executeQuery();

            if(result.next()){
                user = new User(
                    result.getInt("u_id"),
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password"));
            }
        } catch (SQLException e) {
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();          
        }        
        return user;
    }
}
