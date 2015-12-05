/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.database_console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author zulvafachrina
 */
public class DBConnect {
    
    String host;
    String username;
    String pass;
    Connection conn;
    
    public DBConnect() {
        host= "jdbc:mysql://localhost:3307/dazzlesquad";
        username="root";
        pass = "password";
             
    }
    
    public Connection connect () {
        try {
            conn = DriverManager.getConnection( host, username, pass );
        }
        catch ( SQLException err ){
            System.out.println( err.getMessage( ) );
        }
        return conn;
    }
}
