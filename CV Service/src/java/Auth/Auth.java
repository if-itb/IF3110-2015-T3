/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import Database.DB;
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
  
  public int check ( String token ) {
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
     
      String query = String.format("token=%s", 
                                    URLEncoder.encode(token, charset));
      
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
          ret = -1;
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
  
}
