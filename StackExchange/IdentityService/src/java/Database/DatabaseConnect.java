package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnect {
	// JDBC driver name dan database URL
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	final String DB_URL="jdbc:mysql://localhost:3306/stack_exchange?zeroDateTimeBehavior=convertToNull";
		
	//  Username dan password
	final String USER = "root";
	final String PASS = "";//sesuaikan dengan password database
	
	public Connection conn;
	public PreparedStatement stmt;

	public DatabaseConnect(){
		try{
			Class.forName(JDBC_DRIVER);
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
	
	public PreparedStatement getDBStmt(){
		return stmt;
	}
	
}