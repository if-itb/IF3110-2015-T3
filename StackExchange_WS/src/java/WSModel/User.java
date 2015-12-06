/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSModel;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import WSModule.UserClass;
import java.util.ArrayList;

/**
 *
 * @author Jessica
 */
public class User {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String localhost = "jdbc:mysql://localhost:3306/wbd2";
    final static String USER = "root";
    final static String PASS = "";
    
    public static Boolean addUser(String userName,String password, String email) {
        boolean success;
        Connection conn = null;
        Statement statement = null;
        String emailExist = null;
        int countAccount = 0;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            statement = conn.createStatement();
            //cek email
            String accountTable = "SELECT count(*) FROM account";
            ResultSet rs = statement.executeQuery(accountTable);
            if(rs.next()) {
                countAccount = rs.getInt(1);
            }
            int i = 0;
            Boolean found = false;
            while (i < countAccount && !found) {
                String checkEmail = "SELECT email FROM account WHERE email='" + email + "'";
                ResultSet rsEmail = statement.executeQuery(checkEmail);
                if(rsEmail.next()) {
                    emailExist = rsEmail.getString(1);
                }
                if (email.equals(emailExist)) {
                    found = true;
                } else {
                    i++;
                }
            }
            if(found == false)  {
                String query = "INSERT INTO account(`userName`, `password`, `email`) VALUES ('" + userName + "','" + password + "','" + email +"')";
                statement.executeUpdate(query);
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
	
	public static String getUserName(int userID) {
        Connection conn = null;
        Statement statement = null;
        String userName = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            statement = conn.createStatement();
            String query = "SELECT userName FROM account WHERE userID=" + userID;
            ResultSet rsName = statement.executeQuery(query);
            if(rsName.next()) {
                userName = rsName.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userName;
    }
}