/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author visat
 */
public class Database {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull";

    //  Database credentials
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static Connection conn = null;

    public static Connection connection() {
        if (conn != null)
            return conn;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return conn;
    }
}
