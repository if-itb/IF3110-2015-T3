/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import DatabaseWS.DB;
import QuestionModel.QuestionWS;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Vanji
 */
public class Auth {
  
  private String ReST = "http://localhost:8082/WBD_IS/connectws";
  
 
  public int check ( String token ) throws org.json.simple.parser.ParseException, IOException {
    int ret = -1;
            HttpURLConnection connection = null;
            URL url = new URL("http://localhost:8082/WBD_IS/testrestservlet");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while((line = rd.readLine()) != null) {
              response.append(line);
            }
            rd.close();
            if (response.toString().equals("valid"))
            { ret= 1;}
            else if (response.toString().equals("expired"))
            {ret= -1;}
            else
            {ret= 0;}
    return ret;
  }
  
  public int getUserID ( String token ) {
    int ret = -1;
    DB db = new DB();
    Connection conn = db.connect();  
      try {
          Statement stmt = conn.createStatement();
          String sql;

          sql = "SELECT * FROM token WHERE t_token = ?";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setString(1, token);

          ResultSet rs = dbStatement.executeQuery();

          // Extract data from result set
          while(rs.next()){        
            ret = rs.getInt("u_id");
          }

          rs.close();
          stmt.close();
      } catch(SQLException ex) {
          Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
      }
    return ret;
  }
  
   public String getUName ( String token ) {
    String ret = null;
    int temp = -1;
    DB db = new DB();
    Connection conn = db.connect();  
      try {
          Statement stmt = conn.createStatement();
          String sql;

          sql = "SELECT * FROM token WHERE t_token = ?";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setString(1, token);

          ResultSet rs = dbStatement.executeQuery();

          // Extract data from result set
          while(rs.next()){        
            temp = rs.getInt("u_id");
          }

          sql = "SELECT u_name FROM user WHERE u_id = ?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, temp);

          rs = dbStatement.executeQuery();
          
          while(rs.next()){        
            ret = rs.getString("u_name");
          }
          
          
          rs.close();
          stmt.close();
      } catch(SQLException ex) {
          Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
      }
    return ret;
  }
  
   public String getUEmail ( String token ) {
    String ret = null;
    int temp = -1;
    DB db = new DB();
    Connection conn = db.connect();  
      try {
          Statement stmt = conn.createStatement();
          String sql;

          sql = "SELECT * FROM token WHERE t_token = ?";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setString(1, token);

          ResultSet rs = dbStatement.executeQuery();

          // Extract data from result set
          while(rs.next()){        
            temp = rs.getInt("u_id");
          }

          sql = "SELECT u_email FROM user WHERE u_id = ?";
          dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, temp);

          rs = dbStatement.executeQuery();
          
          while(rs.next()){        
            ret = rs.getString("u_email");
          }
          
          
          rs.close();
          stmt.close();
      } catch(SQLException ex) {
          Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
      }
    return ret;
  }
   
}