/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import AnswerModel.Answer;
import DatabaseWS.DB;
import QuestionModel.Question;
import QuestionModel.QuestionWS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public int register (@WebParam(name = "name") String name,
                        @WebParam(name = "email") String email,
                        @WebParam(name = "password") String password){
        int Valid = 1;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT email FROM user WHERE email = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, email);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            if(rs.next()){        
              Valid = 0;
            }
            else {
                sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, name);
                dbStatement.setString(2, email);
                dbStatement.setString(3, password);

                dbStatement.executeUpdate();
            }
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            Valid = 0;
        }
        
        return Valid;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserByUID")
    public User getUserByUID(@WebParam(name = "uid") int uid) {
        User result = null;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "SELECT * FROM user WHERE u_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, uid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            if(rs.next()){
                result = new User( rs.getInt("u_id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("password")
                                );
            }else{
                result = new User();
            }
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
