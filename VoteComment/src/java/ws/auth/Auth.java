/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.auth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author Asus
 */
public class Auth {
    public String getEmail(String token) {
        String currentEmail = null;
        try {
            URL url = new URL("http://localhost:8001/Identity/AuthRSServlet");
            URLConnection urlConn = url.openConnection();
            DataOutputStream outstream;
            
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            
            outstream = new DataOutputStream(urlConn.getOutputStream());
            JSONObject obj = new JSONObject();
            obj.put("access_token", token);
            
            outstream.writeBytes(obj.toString());
            outstream.flush();
            outstream.close();
            
            BufferedReader resReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            
            String resLine = "";
            String in = resReader.readLine();
            
            while (in != null) {
                resLine += in;
                in = resReader.readLine();
            }
            JSONObject objResponse = new JSONObject(resLine);
            currentEmail = objResponse.getString("email");
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentEmail;
    }
    
    public String getName(String token) {
        String Name = null;
        try {
            URL url = new URL("http://localhost:8001/Identity/AuthRSServlet");
            URLConnection urlConn = url.openConnection();
            DataOutputStream outstream;
            
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            
            outstream = new DataOutputStream(urlConn.getOutputStream());
            JSONObject obj = new JSONObject();
            obj.put("access_token", token);
            outstream.writeBytes(obj.toString());
            outstream.flush();
            outstream.close();
            
            BufferedReader resReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            
            String resLine = "";
            String in = resReader.readLine();
            while (in != null) {
                resLine += in;
                in = resReader.readLine();
            }
            
            JSONObject objResponse = new JSONObject(resLine);
            Name = objResponse.getString("name");
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Name;
    }
    
    public Timestamp getExpiredDate(String token) {
        Timestamp exp = null;
        try {
            URL url = new URL("http://localhost:8001/Identity/AuthRSServlet");
            URLConnection urlConn = url.openConnection();
            DataOutputStream outstream;
            
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            
            outstream = new DataOutputStream(urlConn.getOutputStream());
            JSONObject obj = new JSONObject();
            obj.put("access_token", token);
            outstream.writeBytes(obj.toString());
            outstream.flush();
            outstream.close();
            
            BufferedReader resReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            
            String resLine = "";
            String in = resReader.readLine();
            while (in != null) {
                resLine += in;
                in = resReader.readLine();
            }
            
            JSONObject objResponse = new JSONObject(resLine);
            String temp = objResponse.getString("expireddate");
            exp = Timestamp.valueOf(temp);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exp;
    }
}
