/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import DatabaseWS.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Vanji
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    Connection conn = DB.connect();
    @WebMethod(operationName = "register")
    @WebResult(name = "User")
    public int register (@WebParam(name = "name") String name,
                        @WebParam(name = "email") String email,
                        @WebParam(name = "password") String password){
        
        int user = 0;
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE email = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet rs = dbStatement.executeQuery();
            
            
            if (rs != null){
                sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
                dbStatement = conn.prepareStatement(sql);
                ResultSet res = dbStatement.executeQuery();
                user = 1;
                res.close();
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            
        }

        return user;
    }
}
