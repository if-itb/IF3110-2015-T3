/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import Config.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author User
 */
public class TokenValidator {
    public static Boolean validateToken(String token) {
        
        DB db = new DB();
        Connection conn = db.connect();
        String token_db;
        String date;
        
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT date_create FROM account where token = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                date = rs.getString("date_create");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
        }
        
        return false;
    }
}
