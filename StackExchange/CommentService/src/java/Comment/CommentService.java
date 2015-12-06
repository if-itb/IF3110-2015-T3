/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comment;

import Database.DatabaseConnect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gerry
 */
public class CommentService {
    private Connection conn;
    
    public CommentService() {
        DatabaseConnect dbc = new DatabaseConnect();
        conn = dbc.getConn();
    }
    
    public boolean checkToken(String token) {
      try {
        HttpURLConnection connection;
        URL url = new URL("http://localhost:8082/IdentityService/ValidateToken?token=" + token);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        InputStream is = connection.getInputStream();
        StringBuffer response;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
          response = new StringBuffer();
          String line;
          while((line = rd.readLine()) != null) {
            response.append(line);
          }
        }
        return response.toString().equals("true");
      }
      catch (MalformedURLException ex) {
          return false;
      }
      catch (IOException ex) {
          return false;
      }
    }
    
    public int getUserIDFromToken(String token){
          String query = "SELECT * FROM token WHERE value='"+ token +"'";
          int user_id = -1;
          DatabaseConnect dbc = new DatabaseConnect();
          try{
            PreparedStatement stmt =  dbc.getConn().prepareStatement(query);
            try (ResultSet rs = stmt.executeQuery()) {
              if(rs.next()){
                user_id = rs.getInt("user_id"); 
              }
            }
              stmt.close();
          } catch (SQLException se){
              se.printStackTrace();
          }
          finally{
              return user_id;
          }
    }
    
    public void insertComment(Integer qid, String content, String token){
        if (checkToken(token)) {
            try {
                String sql = "INSERT INTO comment(questionId, userId, content) VALUES(?, ?, ?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getUserIDFromToken(token);
                dbStatement.setInt(1, qid);
                dbStatement.setInt(2, userId);
                dbStatement.setString(3, content);

                dbStatement.executeUpdate();
            }
            catch(SQLException ex) {
            }
        }
    }
    
    public ArrayList<Comment> getComment(int qid) {
      ArrayList<Comment> comments = new ArrayList<>();
      try {
        String sql = "SELECT * FROM comment WHERE questionId=?";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);
        
        ResultSet rs = dbStatement.executeQuery();  
        while(rs.next()) {
          Comment temp = new Comment(
            rs.getInt("id"),
            rs.getInt("questionId"),
            rs.getInt("userId"),
            rs.getString("content"));
          comments.add(temp);
        }
        
        return comments;
      }
      catch (SQLException ex) {
          return null;
      }
    }
}
