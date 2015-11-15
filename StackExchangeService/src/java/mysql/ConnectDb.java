package mysql;

import java.sql.*;

public class ConnectDb {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/stackExchange?zeroDateTimeBehavior=convertToNull";

   static final String USER = "root";
   static final String PASS = "";
   
   static Connection conn = null;
   public static Connection connect() {
       try{
           Class.forName("com.mysql.jdbc.Driver");
           System.out.println("Connecting to database...");
           conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(SQLException | ClassNotFoundException se){
        }finally{
            return conn;
        }
   }
}