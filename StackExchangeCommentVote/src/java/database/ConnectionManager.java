/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author davidkwan
 */
public class ConnectionManager {
    private static ConnectionManager instance = null;

    private final String USERNAME = "root";
    private final String PASSWORD = "mysql";
    private final String CONN_STRING =  "jdbc:mysql://localhost/stackexchange";

    private Connection conn = null;

    private ConnectionManager(){
    }

    public static ConnectionManager getInstance(){
        if(instance == null){
            instance = new ConnectionManager();
        }
        return instance;
    }
    private boolean openConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
        return true;
    }
    public Connection getConnection(){
        if(conn == null){
                if(openConnection()){
                    System.out.println("Connection Opened");
                    return conn;
                }else{
                    return null;
                }
        }
        return conn;
    }
    
    public void close(){
        System.out.println("Closing Connection");
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn = null;
    }
}

