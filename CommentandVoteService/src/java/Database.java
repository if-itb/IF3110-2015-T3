/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;

/**
 *
 * @author LUCKY
 */
public class Database {
    /*Reference http://stackoverflow.com/questions/14960552/how-to-access-connection-object-in-another-class-in-java*/
    public static Connection connect() {
        Connection con = null;
        String url = "jdbc:mysql://localhost/";
        String db = "stackexchange_db";
        String driver = "com.mysql.jdbc.Driver";
        String user = "stackexchange";
        String pass = "stackexchangespwd";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, pass);
            if (con == null) {
                System.out.println("Connection cannot be established");
            }
            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
