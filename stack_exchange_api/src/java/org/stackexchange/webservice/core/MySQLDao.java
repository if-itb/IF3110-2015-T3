package org.stackexchange.webservice.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDao {
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/stack_exchange_2";

    final String USER = "root";
    final String PASS = "rahasia";

    protected Connection conn = null;

    protected void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void closeConnection() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
}
