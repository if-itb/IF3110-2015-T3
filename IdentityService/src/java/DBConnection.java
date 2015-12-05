/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
import java.util.*;
import java.sql.*;

public class DBConnection {
	// JDBC driver name dan database URL
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	final String DB_URL="jdbc:mysql://localhost:3306/restingsoapdb";
		
	//  Username dan password
	final String USER = "root";
	final String PASS = "venzel";//sesuaikan dengan password database
	
	private Connection conn;
	
	public DBConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		} catch (SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public Connection getConn(){
		return conn;
	}
	
}
