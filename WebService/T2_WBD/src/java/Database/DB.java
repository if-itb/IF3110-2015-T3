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
 * @author Vanji
 */
public class DB {
    public static Connection connect(){
        Connection conn = null;
        try{
            String host = "jdbc:derby://localhost:8080/stackexchange";
            String user = "root";
            String pass = "";
            
            conn = DriverManager.getConnection(host, user, pass);
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
        
    }
}
