/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.crypto.spec.DESedeKeySpec;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Afrizal
 */
public class auth extends HttpServlet 
{
  protected final int timeLimit = 5400;         // token expires in second
  protected static final String DB_URL = "jdbc:mysql://localhost/WBD2";
  protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  protected static final String USER = "root";
  protected static final String PASS = "";
  private Connection conn;
  private Statement stmt;
  private JsonBuilderFactory factory;

  /**
   * Initialize
   * @throws ServletException 
   */
  @Override
  @SuppressWarnings("CallToPrintStackTrace")
  public void init() throws ServletException 
  {
    super.init(); 
    factory = Json.createBuilderFactory(null);
    
    try {
      // Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }

    // Open a connection
    conn = null;
    stmt = null;
    try {
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
  
  /**
   * Handles all request (by POST)
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs 
   */
  @Override
  @SuppressWarnings({"CallToPrintStackTrace", "null"})
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException 
  {
    PrintWriter out = response.getWriter();
    ResultSet rs = null;
    Long time = System.currentTimeMillis() / 1000;
    JsonObject json;

    if (request.getParameter("token") != null)        // token verification
    {
      String token = request.getParameter("token");
      Long expires = 0L;
      try {
        rs = queryExecutor("SELECT expires FROM token WHERE val=" + token);
        expires = rs.getLong("expires");
      } catch (SQLException ex) {
        ex.printStackTrace();
      }

      if (null == expires)          // token invalid
        json = generateError("token expired", 3040);
      else if (expires < time)      // token expired
        json = generateError("token invalid", 4040);
      else {
        json = factory.createObjectBuilder()
          .add("num", 1200)
          .add("detail", "OK")
          .build();
      }
    } 
    else {        // handle login authentication
      String user = request.getParameter("user");
      String pass = request.getParameter("pass");
      int id = -1;
      
      try {   
        rs = queryExecutor("SELECT id FROM user WHERE email=" + user + " AND pass=" + pass);
        id = rs.getInt("id");
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      
      if (id != -1)     // user & pass match
      {
        byte[] bytes = null;
        try {
          SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
          random.setSeed(System.currentTimeMillis());
          bytes = new byte[DESedeKeySpec.DES_EDE_KEY_LEN];
          random.nextBytes(bytes);
        } catch (NoSuchAlgorithmException ex) {
          ex.printStackTrace();
        }

        try {   
          updateExecutor("INSERT INTO token uid, val, expires VALUES (" + id +", " + new String(bytes) +", " + (time+timeLimit) +")");
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        json = factory.createObjectBuilder()
          .add("user", user)
          .add("token", new String(bytes))
          .build();
      }
      else
        json = generateError("authentication failed", 1403);
    }
    
    JsonWriter x;
    x = Json.createWriter(out);
    x.writeObject(json);
    x.close();
    
    out.flush();
    
    // Clean-up environment
    try {
      rs.close();
      conn.close();
      stmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Query executor
   * 
   * @param  sql          
   * @return result set
   * @throws SQLException 
   */
  public ResultSet queryExecutor(String sql) throws SQLException {
    ResultSet rs;    
    rs = stmt.executeQuery(sql);
    rs.next();    
    return rs;
  }

  /**
   * Update executor
   * 
   * @param  sql          
   * @return error status (0 if run well)             
   * @throws SQLException 
   */
  public int updateExecutor(String sql) throws SQLException {
    return stmt.executeUpdate(sql);
  }
  
  /**
   * Generate error flag JSON object
   * 
   * @param detail
   * @param errorNum
   * @return 
   */
  private JsonObject generateError(String detail, int errorNum) 
  {    
    return factory.createObjectBuilder()
      .add("error", factory.createObjectBuilder()
        .add("num", errorNum)
        .add("detail", detail))
      .build();
  }
  
}
