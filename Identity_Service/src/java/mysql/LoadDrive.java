/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.*;

/**
 *
 * @author zainelwati
 */
public class LoadDrive {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
    
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn = null;
    public static Connection connect(){
        try{
            System.out.println("Connecting to database...");
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch(SQLException | ClassNotFoundException se){
            System.out.println("Failed to connect to databse.");
        }
        finally{
            return conn;
        }
    }
}
