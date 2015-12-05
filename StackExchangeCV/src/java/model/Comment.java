/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *
 * @author User
 */
public class Comment {
    private DB db;
    private Connection conn;
    
    private static String getCurrentTimeStamp() {
        Calendar cal = Calendar.getInstance();  
	Timestamp now = new Timestamp(cal.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now); 
    }
    
    public Comment() {
        db = new DB();
    }
    
    public JSONObject getComments() {
        JSONObject json = new JSONObject();
        JSONArray comments = new JSONArray();
        JSONObject comment;
        
        conn = db.connect();
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT * FROM comment";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            if (!rs.next()) {
                json.put("state", 0);
                return json;
            }
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                comment = new JSONObject();
                comment.put("content", rs.getString("content"));
                comment.put("date", rs.getString("date"));
                comment.put("username", rs.getString("username"));
                comments.add(comment);
            }
            json.put("comments", comments);
            json.put("state", 1);
            
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           ex.getSQLState();
        }
        
        return json;
    }
    
    public JSONObject getCommentByQID(int i){
        JSONObject json = new JSONObject();
        JSONArray comments = new JSONArray();
        JSONObject comment;
        
        conn = db.connect();
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT * FROM comment where id_question = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, i);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            if (!rs.next()) {
                json.put("state", 0);
                return json;
            }
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                comment = new JSONObject();
                comment.put("content", rs.getString("content"));
                comment.put("date", rs.getString("date"));
                comment.put("username", rs.getString("username"));
                comments.add(comment);
            }
            json.put("comments", comments);
            json.put("state", 1);
            
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           ex.getSQLState();
        }
        
        return json;
    }
    
    public Boolean addComment(int id_question, String content, String username) {
        conn = db.connect();
        Boolean state = true;
        try {
            Statement stmt;
            stmt = conn.createStatement();

            String sql;
            sql = "INSERT INTO comment(id_question, content, username, date)"
                    + " VALUES (?, ?, ?, ?)";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id_question);
            dbStatement.setString(2, content);
            dbStatement.setString(3, username);
            dbStatement.setString(4, getCurrentTimeStamp());

            state = dbStatement.execute();
            stmt.close();
            conn.close();
        } 
        catch (SQLException ex) {
            ex.getSQLState();
        }
        
        return state;
    }
}

