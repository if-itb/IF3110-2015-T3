/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Irene Wiliudarsan - 13513002
 * @author Angela Lynn - 13513032
 * @author Devina Ekawati - 13513088
 */
public class TokenExecutor {
  // Atribut
  private Token token;
  private int idUser;
  private boolean isValid;
  private Connection connection;
  
  // Konstruktor
  public TokenExecutor(String email, String password) {
    // Periksa apakah email dan password ada di basis data
    try {
      // Connect database
      Database database = new Database();
      connection = database.connectDatabase();
        idUser = getIdUser(email, password);
        if (idUser != -999999) {
          token = new Token(email, password);
          isValid = true;
          LogIn(idUser);
        } else {
          // Kirim pesan error
          token = new Token();
          isValid = false;
        }
      connection.close();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
  public TokenExecutor(String accessToken) {
    token = new Token();
    token.setAccessToken(accessToken);
    if (token.isValid()) {
    // Format token valid, maka periksa apakah token ada di basis data
      try {
        // Connect database
        Database database = new Database();
        connection = database.connectDatabase();
        checkTokenValid();
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
  
  // Getter
  public Token getToken() {
    return token;
  }
  public int getIdUser() {
    return idUser;
  }
  public boolean getIsValid() {
    return isValid;
  }
  
  // Method
  private void LogIn(int idUser) throws SQLException {
    addToken(idUser);
  }
  private void addToken(int idUser) throws SQLException {
    // Menjalankan query
    try (Statement statement = connection.createStatement()) {
      // Menjalankan query
      String query = "UPDATE user SET token = '" + token.getAccessToken() + "', lifetime = '" + token.getLifetime() + "' WHERE id_user = " + idUser;
      statement.executeUpdate(query);
      statement.close();
    }
  }
  private int getIdUser(String email, String password) throws SQLException {
    int idUser;
    // Menjalankan query
    String query = "SELECT id_user FROM user WHERE email = ? AND password = ?";
    PreparedStatement dbStatement = connection.prepareStatement(query);
    dbStatement.setString(1, email);
    dbStatement.setString(2, password);
    ResultSet result = dbStatement.executeQuery();
    if (result.next()) {
      // Email and password matched
      idUser = result.getInt("id_user");
    } else {
      idUser = -999999;
    }
    result.close();
    return idUser;
  }
  private void checkTokenLifetimeExisted() throws SQLException {
    try (Statement statement = connection.createStatement()) {
      // Menjalankan query
      String query = "SELECT id_user FROM user WHERE token = ? AND lifetime = ?";
      PreparedStatement databaseStatement = connection.prepareStatement(query);
      databaseStatement.setString(1, token.getAccessToken());
      databaseStatement.setTimestamp(2, token.getLifetime());
      ResultSet result = databaseStatement.executeQuery(query);
      if (result.next()) {
        // Token ada
        isValid = true;
      } else {
        isValid = false;
      }
      result.close();
    }
  }
  private void checkTokenValid() throws SQLException {
    try (Statement statement = connection.createStatement()) {
      // Menjalankan query
      String query = "SELECT id_user, lifetime FROM user WHERE token = '" + token.getAccessToken() + "'";
      ResultSet result = statement.executeQuery(query);
      if (result.next()) {
        // Token ada, periksa lifetime
        idUser = result.getInt("id_user");
        Timestamp lifetime = result.getTimestamp("lifetime");
        if (lifetime.after(new Date())) {
          // Lifetime valid
          isValid = true;
        } else {
          idUser = -999999;
          isValid = false;
        }
      } else {
        idUser = -999999;
        isValid = false;
      }
      result.close();
      statement.close();
    }
  }
  public String getUserName() {
    String name = "not-valid";
    try (Statement statement = connection.createStatement()) {
      // Menjalankan query
      String query = "SELECT name FROM user WHERE token = '" + token.getAccessToken() + "'";
      ResultSet result = statement.executeQuery(query);
      if (result.next()) {
        // Nama user ditemukan
        name = result.getString("name");
      }
      result.close();
      statement.close();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return name;
  }
  public ArrayList<String> getUserIdentity() {
    ArrayList<String> identity = new ArrayList<String>();
    try (Statement statement = connection.createStatement()) {
      // Menjalankan query
      String query = "SELECT name, email FROM user WHERE token = '" + token.getAccessToken() + "'";
      ResultSet result = statement.executeQuery(query);
      if (result.next()) {
        // Nama user ditemukan
        identity.add(result.getString("name"));
        identity.add(result.getString("email"));
      }
      result.close();
      statement.close();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return identity;
  }
  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
