/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseWS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vanji
 */
public class DB {

    public static Connection connect(){
        try {
            String userName = "root";
            String password = "";

            String url = "jdbc:sqlserver://MYPC\\SQLEXPRESS;databaseName=stackexchange";
            Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:8080/StackExchangeWS", 
                                                        userName, 
                                                        password);
            return conn;
            
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        
   }
}
