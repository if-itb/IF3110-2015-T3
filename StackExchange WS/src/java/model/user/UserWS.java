/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Venny
 */
@WebService(serviceName = "UserWS")
public class UserWS {
    
    /* Connect to database */
    Connection conn = DB.connect();
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "addUser")
    @Oneway
    public void addUser(@WebParam(name = "u") User u) {
        try{
            String sql = "INSERT INTO user (name,email,password) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,u.getName());
            stmt.setString(2,u.getEmail());
            stmt.setString(3,u.getPassword());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log
            (Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserByID")
    @WebResult(name="User")
    public User getUserByID (@WebParam(name = "user_id") int user_id) {
        ArrayList<User> user = new ArrayList<User>();
        try{
            String sql;
            sql = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,user_id);
            ResultSet rs = stmt.executeQuery();
            
            /* Get every data returned by SQL query */
            while(rs.next()){
                user.add(new User( rs.getInt("user_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log
            (Level.SEVERE, null, ex);
           }
        return user.get(0);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getIDbyEmail")
    @WebResult(name="Integer")
    public Integer getIDbyEmail (@WebParam(name = "email") String email) {
        int id = -1;
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT user_id FROM user WHERE email = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, email);
            ResultSet rs = dbStatement.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("user_id");
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log
            (Level.SEVERE, null, ex);
           }
        return id;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserByToken")
    @WebResult(name="User")
    public User getUserByToken (@WebParam(name = "token") String token) {
        User user = new User();
        try{
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT user_id FROM token WHERE token_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);
            ResultSet rs = dbStatement.executeQuery();
            
            while(rs.next()){
                user.setUserID(rs.getInt("user_id"));
            }
            rs.close();
            
            String sql2 = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement dbStatement2 = conn.prepareStatement(sql);
            dbStatement2.setInt(1, user.getUserID());
            ResultSet rs2 = dbStatement2.executeQuery();
            
            while(rs2.next()){
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
            rs2.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log
            (Level.SEVERE, null, ex);
           }
        
        return user;
    }
}
