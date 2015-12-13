/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistrationModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "RegistrationWS")
public class RegistrationWS {

    @WebMethod(operationName = "register")
    public boolean register(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        Connection conn = null;
        try {            
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            String sql = "SELECT email FROM users WHERE email LIKE '" + email + "'";
            PreparedStatement dbs = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = dbs.executeQuery();
            if(!rs.isBeforeFirst() && (email!=null || email!="")) {
                sql = "INSERT INTO users(name,password,email) VALUES ('"+name+"','"+password+"','"+email+"')";
                Statement stmt = (Statement) conn.createStatement();
                stmt.executeUpdate(sql);
                return true;
            }
        }
        catch (ClassNotFoundException | SQLException e) {         
        }
        return false;
    }
}
