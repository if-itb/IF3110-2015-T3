/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author erick_000
 */
public class Database {
    // JDBC Driver and URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/stackexchange";
    
    // Database login
    static final String USER = "root";
    static final String PASS = "mysql";
    
    public static Connection connect() {
        Connection ret = null;
        
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Open a connection
            ret = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        return ret;
    }
}
