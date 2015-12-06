/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.stackx.module;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author natanelia
 */
public class User {
    private int userId;
    private String name;
    private String email;
    private String createDate;
    private int valid;
    
    
    public User(String accessToken, String userAgent, String ipAddress) {
        try {
            this.valid = -1;
            JSONObject requestObj = new JSONObject();
            requestObj.put("token", accessToken);
            requestObj.put("user_agent", userAgent);
            requestObj.put("ip_address", ipAddress);
            byte[] postData       = requestObj.toString().getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            String request        = "http://localhost:8080/Identity_Service/Authentication";
            URL    url;
            try {
                url = new URL( request );
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                try {
                    conn.setRequestMethod( "POST" );
                } catch (ProtocolException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn.setRequestProperty( "Content-Type", "application/json");
                conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                conn.setUseCaches( false );
                try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                    wr.write( postData );
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                
                String response = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
                JSONObject usr = new JSONObject(response);
                if (usr.getString("user_id").equals("")) {
                    this.userId = -1;
                    this.name = "";
                    this.email = "";
                    this.valid = (usr.getInt("is_valid"));
                    this.createDate = "";
                } else {
                    this.userId = usr.getInt("user_id");
                    this.name = usr.getString("name");
                    this.email = usr.getString("email");
                    this.valid = (usr.getInt("is_valid"));
                    this.createDate = "";
                }
            } catch (JSONException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createDate = "";
        this.valid = 0;
    }

    public User(int userId, String name, String email, String password, String createDate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.valid = 0;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int isValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
    
    
}
