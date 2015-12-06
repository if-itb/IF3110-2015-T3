/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public class DB {
    private String hostParam;
    private String user;
    private String password;
    
    public DB() {
        this.hostParam = "jdbc:mysql://localhost:3306/stackexchange2?zeroDateTimeBehaviour=convertToNull";
        this.user = "root";
        this.password = "dininta";
    }
    
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(hostParam, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
