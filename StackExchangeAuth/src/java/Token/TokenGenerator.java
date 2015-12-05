/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import Config.DB;

import java.util.UUID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class TokenGenerator {
    private String token;
    
    private static String getCurrentTimeStamp() {
        Calendar cal = Calendar.getInstance();  
	Timestamp now = new Timestamp(cal.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now); 
    }
    
    public TokenGenerator() {}
    
    public TokenGenerator(int lifetime, String userAgent, String ip) {
        this.token = UUID.randomUUID().toString();
        this.token.concat('#'+userAgent+"#"+ip);
        DB db = new DB();
        Connection conn = db.connect();
        
        try {
            Statement stmt;
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO token (token, date_create, valid_hour)VALUES (?, ?, ?)";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);
            dbStatement.setString(2, getCurrentTimeStamp());
            dbStatement.setInt(3, lifetime);
            
            dbStatement.execute();
            
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
          
        }
    }
    
    public String getToken() {
        return token;
    }
}


