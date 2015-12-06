/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.register;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "RegisterWS") 
public class RegisterWS {
    Connection conn = null;
    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public int register(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        String sql = "";
        int result = 0;
        try {
            if (name.equals("") || email.equals("") || password.equals("")) {
                return 0;
            } else {
                //TODO write your implementation code here:
                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
                Statement stmt = conn.createStatement();
                sql = "SELECT * FROM users WHERE Email = ?";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, email);
                ResultSet rs = dbStatement.executeQuery();
                //jika email belum teregister
                if (!rs.next()) {
                    sql = "INSERT INTO users (Name, Email, Password) VALUES (?,?,?)";

                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setString(1, name);
                    dbStatement.setString(2, email);
                    dbStatement.setString(3, password);
                    result = dbStatement.executeUpdate();
                }
                conn.close();
                stmt.close();
                dbStatement.close();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
