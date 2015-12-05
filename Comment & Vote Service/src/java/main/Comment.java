/*
 * Tugas 3 IF3110 Pengembangan Aplikasi Web
 * Website StackExchangeWS Sederhana
 * dengan tambahan web security dan frontend framework
 * 
 * @author Irene Wiliudarsan - 13513002
 * @author Angela Lynn - 13513032
 * @author Devina Ekawati - 13513088
 */
package main;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Kelas untuk menangani comment pada pertanyaan
 */
public class Comment {
  // Atribut
  Database database;
  Connection connection;
  
  // Konstruktor
  public Comment() {
    // Connect database
    database = new Database();
    try {
      connection = database.connectDatabase();
    } catch (SQLException ex) {
      Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  // Method
  public boolean addComment(int idQuestion, String content, String token, String ipAddress, String userAgent) {
    boolean commentAdded = false;
    String urlString = "http://localhost:8082/Identity_Service/TokenController";
    User user = new User();
    
    try {
      Statement statement = connection.createStatement();
      // Request User ke Identity Service
      user = user.getUserFromIS(token, ipAddress, userAgent, urlString);

      if (user.getIdUser() > 0) {
        // Menjalankan query
        String query = "INSERT INTO comment (id_question, id_user, content) VALUES (?, ?, ?)";
        PreparedStatement databaseStatement;
        databaseStatement = connection.prepareStatement(query);
        databaseStatement.setInt(1, idQuestion);
        databaseStatement.setInt(2, user.getIdUser());
        databaseStatement.setString(3, content);
        databaseStatement.executeUpdate();
        
        statement.close();
        commentAdded = true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return commentAdded;
  }
}
