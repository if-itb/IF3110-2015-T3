
package com.wbd.db;

import java.sql.*;
import java.util.*;

public class DBConnection{

    //Declare Database Name
    final String DB_NAME = "wbd";
    
    //Declare JDBC Driver Name and Database URL
    final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    final String DB_URL="jdbc:mysql://localhost:3306/" + DB_NAME +"?zeroDateTimeBehavior=convertToNull";
    
    //Declare Database Credentials
    final String USER = "root";
    final String PASS = "";
    
    //Declare Connection
	public Connection connection;
	public PreparedStatement statement;

	public DBConnection(){
        try{
			//Register JDBC Driver
            Class.forName(JDBC_DRIVER);
			
			//Open a connection
            connection = DriverManager.getConnection(DB_URL,USER,PASS);   
        } catch(SQLException e1){
        	e1.printStackTrace();
        } catch(Exception e2){
            e2.printStackTrace();
        }      
	}

    public Connection getConnection(){
        return connection;
    }

    public PreparedStatement getStatement(){
        return statement;
    }

  

}