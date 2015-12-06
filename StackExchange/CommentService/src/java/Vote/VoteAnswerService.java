/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vote;

import Comment.CommentService;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;

/**
 *
 * @author Gerry
 */
public class VoteAnswerService {
  public Connection conn;
  
  public VoteAnswerService() {
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
      //return response.toString().equals("true");
      return true;
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
      PreparedStatement stmt =  conn.prepareStatement(query);
      try (ResultSet rs = stmt.executeQuery()) {
        if(rs.next()){
          user_id = rs.getInt("user_id"); 
        }
      }
      stmt.close();
    }catch (SQLException se){
      se.printStackTrace();
    }
    finally{
      return user_id;
    }
  }
  
  public String getAnswerVote(int aid) {
      try {
        String sql = "SELECT * FROM answer WHERE id=?";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, aid);
        ResultSet rs = dbStatement.executeQuery();
        rs.next();
        String vote = rs.getString("vote");
        return vote;
      }
      catch (SQLException e) {
          return "error";
      }
  }
  
  public void voteUpAnswer(@WebParam(name = "token") String token, @WebParam(name = "id") int id) {
    if (checkToken(token)) {
      try {
        String sql = "SELECT * FROM answervote WHERE userid=? AND answerid=?";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        //int userId = getUserIDFromToken(token);
        int userId = 3;
        dbStatement.setInt(1, userId);
        dbStatement.setInt(2, id);
        ResultSet rs = dbStatement.executeQuery();

        if (!rs.next()) {
          sql = "INSERT INTO answervote(userid, answerid, type) VALUES(?, ?, ?)";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, userId);
          dbStatement.setInt(2, id);
          dbStatement.setInt(3, 1);
          dbStatement.executeUpdate();

          sql = "SELECT vote FROM answer WHERE id=?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, id);
          rs = dbStatement.executeQuery();

          rs.next();
          int vote = rs.getInt("vote") + 1;

          sql = "UPDATE answer SET vote=? WHERE id=?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, vote);
          dbStatement.setInt(2, id);
          dbStatement.executeUpdate();
        }
        else {
          sql = "SELECT type FROM answervote WHERE userid=? AND answerid=?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, userId);
          dbStatement.setInt(2, id);
          rs = dbStatement.executeQuery();

          rs.next();
          int type = rs.getInt("type");

          if (type == 0) {
            type = 1;
            sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, 1);
            dbStatement.setInt(2, userId);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();

            sql = "SELECT vote FROM answer WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            rs = dbStatement.executeQuery();

            rs.next();
            int vote = rs.getInt("vote") + 1;

            sql = "UPDATE answer SET vote=? WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, id);
            dbStatement.executeUpdate();
          }
          else if (type == 1) {
            type = 0;
            sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, 0);
            dbStatement.setInt(2, userId);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();

            sql = "SELECT vote FROM answer WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            rs = dbStatement.executeQuery();
            rs.next();
            int vote = rs.getInt("vote") - 1;

            sql = "UPDATE answer SET vote=? WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, id);
            dbStatement.executeUpdate();
          }
          else if (type == -1) {
            type = 1;
            sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, 1);
            dbStatement.setInt(2, userId);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();

            sql = "SELECT vote FROM answer WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            rs = dbStatement.executeQuery();

            rs.next();
            int vote = rs.getInt("vote") + 2;

            sql = "UPDATE answer SET vote=? WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, id);
            dbStatement.executeUpdate();
          }
        }
      }
      catch (SQLException ex) {
      }
    }
  }

  public void voteDownAnswer(String token, int id) {
    if (checkToken(token)) {
      try {
        String sql = "SELECT * FROM answervote WHERE userid=? AND answerid=?";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        //int userId = getUserIDFromToken(token);
        int userId = 3;
        dbStatement.setInt(1, userId);
        dbStatement.setInt(2, id);
        ResultSet rs = dbStatement.executeQuery();

        if (!rs.next()) {
          sql = "INSERT INTO answervote(userid, answerid, type) VALUES(?, ?, ?)";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, userId);
          dbStatement.setInt(2, id);
          dbStatement.setInt(3, -1);
          dbStatement.executeUpdate();

          sql = "SELECT vote FROM answer WHERE id=?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, id);
          rs = dbStatement.executeQuery();

          rs.next();
          int vote = rs.getInt("vote") - 1;

          sql = "UPDATE answer SET vote=? WHERE id=?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, vote);
          dbStatement.setInt(2, id);
          dbStatement.executeUpdate();
        }
        else {
          sql = "SELECT type FROM answervote WHERE userid=? AND answerid=?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, userId);
          dbStatement.setInt(2, id);
          rs = dbStatement.executeQuery();

          rs.next();
          int type = rs.getInt("type");

          if (type == 0) {
            type = -1;
            sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, type);
            dbStatement.setInt(2, userId);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();

            sql = "SELECT vote FROM answer WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            rs = dbStatement.executeQuery();

            rs.next();
            int vote = rs.getInt("vote") - 1;

            sql = "UPDATE answer SET vote=? WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, id);
            dbStatement.executeUpdate();
          }
          else if (type == 1) {
            type = -1;
            sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, type);
            dbStatement.setInt(2, userId);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();

            sql = "SELECT vote FROM answer WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            rs = dbStatement.executeQuery();
            rs.next();
            int vote = rs.getInt("vote") - 2;

            sql = "UPDATE answer SET vote=? WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, id);
            dbStatement.executeUpdate();
          }
          else if (type == -1) {
            type = 0;
            sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, type);
            dbStatement.setInt(2, userId);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();

            sql = "SELECT vote FROM answer WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            rs = dbStatement.executeQuery();

            rs.next();
            int vote = rs.getInt("vote") + 1;

            sql = "UPDATE answer SET vote=? WHERE id=?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, id);
            dbStatement.executeUpdate();
          }
        }
      }
      catch (SQLException ex) {
      }
    }
  }   
}
