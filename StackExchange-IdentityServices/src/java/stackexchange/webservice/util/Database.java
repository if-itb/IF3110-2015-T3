/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fauzanrifqy
 */
public class Database {
    private Connection connection;
    private String userName = "root";
    private String passWord = "root";
    
    public Database(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new NestedException("Error driver : " + e.getMessage(), e, 0);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/StackExchange2", userName, passWord);
        } catch (Exception e) {
            throw new NestedException("Error connection : " + e.getMessage(), e, 0);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            throw new NestedException("Error close connection : " + e.getMessage(), e, 0);
        }
    }
}
