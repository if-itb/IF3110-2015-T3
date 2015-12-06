package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Class for connecting to JDBC resource
public class ConnectDb {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/re-asklyz?zeroDateTimeBehavior=convertToNull";

   static final String USER = "hebakusa";
   static final String PASS = "";
   
   static Connection conn = null;
   public static Connection connect() {
        try {
            System.out.println("Connecting to database...");
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(SQLException | ClassNotFoundException se){
            System.out.println("Failed to connect to the database.");
        }
        finally{
            return conn;
        }
   }
}