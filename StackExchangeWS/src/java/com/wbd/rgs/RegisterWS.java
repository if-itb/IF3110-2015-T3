/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.rgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "RegisterWS")
public class RegisterWS {
    //Declare Connection
    //private Connection conn;
    
    //Declare Database Name
    static final String DB_NAME = "wbd";    
    //Declare JDBC Driver Name and Database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost:3306/" + DB_NAME +"?zeroDateTimeBehavior=convertToNull";
    
    //Declare Database Credentials
   
    
    //Luminto
    static final String USER = "root";
    static final String PASS = "";
    
   //Albert Tri
    /*static final String USER = "root";
    static final String PASS = "alberttriadrian";*/
    


    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public int register(@WebParam(name = "nama") String name, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        int hasil;
        Connection conn = null;
        PreparedStatement dbStatement = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String sql; 
            sql = "SELECT * FROM user";
            dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            while (rs.next()){
                if (email.equals(rs.getString("email"))){
                    return 0;
                }
            }
            
            sql = "INSERT INTO user(Nama, Email, Password) VALUES (?,?,?)";
            
            dbStatement = conn.prepareStatement(sql);

            dbStatement.setString(1, name);
            dbStatement.setString(2, email);
            dbStatement.setString(3, password);
            
            dbStatement.executeUpdate();
            hasil = 1;
            dbStatement.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
            hasil = 0;
        }
        return hasil;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUsername")
    public String getUsername(@WebParam(name = "accessToken") String accessToken) {
        //TODO write your implementation code here:
        String hasil = "";
        Connection conn = null;
        PreparedStatement dbStatement = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String sql; 
            sql= "SELECT user.Nama as name FROM user INNER JOIN token ON user.IDUser=token.IDUser WHERE token.access_token = '"+ accessToken +"'";
            dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            while (rs.next()){
                hasil = rs.getString("name");
            }
            dbStatement.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
            hasil = "";
        }
        return hasil;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "signout")
    public int signout(@WebParam(name = "access_token") String access_token) {
        int message = 0 ;
        Connection conn = null;
        PreparedStatement dbStatement = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String sql; 
            sql = "DELETE FROM token WHERE access_token = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, access_token);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            message = 1;
        }catch(SQLException se){
            se.printStackTrace();
            message = -1;
        }
        return message;
    }

}
