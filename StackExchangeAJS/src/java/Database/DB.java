/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmad Naufal Farhan
 */
public class DB {
    public static Connection getConnection() {
        
        Connection conn = null;
        
        try {
            // create a mysql database connection
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/stackexchange";
            String username = "root";
            String password = "";
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
