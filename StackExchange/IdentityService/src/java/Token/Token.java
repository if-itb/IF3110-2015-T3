package Token;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import Database.DatabaseConnect; 
import java.util.Calendar;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Token {
    private String access_token;
    private String lifetime;
    
    public Token(String ac_token){
        access_token = ac_token;
        String query = "SELECT * FROM token WHERE value='"+ ac_token +"'";
        PreparedStatement statement;
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            if(rs.next()){
                lifetime = rs.getString("lifetime"); 
            }
            rs.close();
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
	}
    }
    
    public boolean isTokenValid(){
        
        boolean valid = false;
        
	long expire_ms = Long.parseLong(lifetime);
        
	Date dt = new Date();
        java.text.SimpleDateFormat sdf = 
                          new java.text.SimpleDateFormat("yyyyMMddHHmmss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        String cur_date = sdf.format(cal.getTime());
        long cur_date_ms = Long.parseLong(cur_date.trim());
        
	if(expire_ms>cur_date_ms){
            valid = true;
	}
        return valid;
        
    }

    public String getLifetime() {
        return lifetime;
    }
}
