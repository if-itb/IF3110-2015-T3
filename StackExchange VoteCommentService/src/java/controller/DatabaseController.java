package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tifani
 */
public class DatabaseController {
    // JDBC driver name and database URL
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/stackexchange_service";
    
    // Database credentials
    private static final String username = "root";
    private static final String password = "";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(driver);
            // Establish connection to db
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
}
