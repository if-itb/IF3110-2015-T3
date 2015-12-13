/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author adek
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserbyID")
    public String getUserbyID(@WebParam(name = "id") int id) {
        String user="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            Statement stmt = conn.createStatement();
            String sql = "SELECT name FROM users WHERE id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,id);
            ResultSet result = dbStatement.executeQuery();
            int i = 0;
            while(result.next()) {
                user+=result.getString("name");
                ++i;
            }
            result.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getIDUserbyToken")
    public int getIDUserbyToken(@WebParam(name = "token") String token) {
        int userid=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            Statement stmt = conn.createStatement();
            String sql = "SELECT userid FROM tokens WHERE token = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1,token);
            ResultSet result = dbStatement.executeQuery();
            if(result.next()) {
                userid = result.getInt("userid");
            }
            result.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userid;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "removeToken")
    public boolean removeToken(@WebParam(name = "token") String token) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            String sql = "DELETE FROM tokens WHERE token = '"+token+"'";
            Statement stmt = (com.mysql.jdbc.Statement) conn.createStatement();
            stmt.executeUpdate(sql);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
