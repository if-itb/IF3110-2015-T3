package org.stackexchange.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLModel {

    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/stack_exchange_2";

    final String USER = "root";
    final String PASS = "rahasia";

    Connection conn = null;

    public Connection GetConnection()
    {
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }

    public void CloseConnection(){
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se) {
            se.printStackTrace();
        }
    }
}
