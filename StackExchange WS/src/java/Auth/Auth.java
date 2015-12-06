/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import Database.DB;
import QuestionModel.QuestionWS;
import java.io.BufferedReader;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Asus
 */
public class Auth {
  
  private String ReST = "http://localhost:8082/Identity_Service/validation";
  
  public int check ( String token, String userIP, String userAgent ) {
    int ret = -1;
    JSONParser parser = new JSONParser();    
    try {
      String charset = "UTF-8";

      URL url = new URL(ReST);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Accept-Charset", charset);
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
      
      String query = String.format("token=%s&userIP=%s&userAgent=%s", 
                                    URLEncoder.encode(token, charset),
                                    URLEncoder.encode(userIP, charset),
                                    URLEncoder.encode(userAgent, charset));

      try (OutputStream output = conn.getOutputStream()) {
          output.write(query.getBytes(charset));
      }

      InputStream res = conn.getInputStream();
      System.out.println(res);

      BufferedReader br = new BufferedReader(new InputStreamReader(
          (conn.getInputStream())));

      String output;      
      Object obj;
      JSONObject jobj;

      while ((output = br.readLine()) != null) {
        obj = parser.parse(output);
        jobj = (JSONObject) obj;

        ret = -1;

        if (((String)jobj.get("message")).equals("valid")) {
          ret = 1;
        } else if (((String)jobj.get("message")).equals("expired")) {
          ret = 0;
        } else {
          if (((String)jobj.get("message")).contains("IP")) {
            ret *= 2;
          }
          if (((String)jobj.get("message")).contains("UA")) {
            ret *= 3;
          }
        }
      }

      conn.disconnect();

          } catch (MalformedURLException e) {

                e.printStackTrace();

          } catch (IOException e) {

                e.printStackTrace();

    } catch (ParseException ex) {
      Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return ret;
  }
  
  public int getUserID ( String token ) {
    int ret = -1;
    DB db = new DB();
    Connection conn = db.connect();  
      try {
          Statement stmt = conn.createStatement();
          String sql;

          sql = "SELECT * FROM access_token WHERE token = ?";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setString(1, token);

          ResultSet rs = dbStatement.executeQuery();

          // Extract data from result set
          while(rs.next()){        
            ret = rs.getInt("user_id");
          }

          rs.close();
          stmt.close();
      } catch(SQLException ex) {
          Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
      }
    return ret;
  }
  
}
