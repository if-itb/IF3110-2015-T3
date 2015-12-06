/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vanji
 */
public class DB {

    public static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user = "root";
            String pass = "";
            
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", 
                    user, 
                    pass);
            return conn;
            
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
   }
}
