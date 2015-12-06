/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ahmad Naufal Farhan
 */
public class ValidationToken {
    
    // authorization flag
    public static final int AUTH_VALID = 1;
    public static final int AUTH_EXPIRED = 0;
    public static final int AUTH_DIFFIP = -1;
    public static final int AUTH_DIFFBROWSER = -2;
    public static final int AUTH_INVALID = -3;
    
    
    /* URL TO ServiceAuth CONTROLLER IN IDENTITY SERVICE */
    private static final String URL_AUTH = "http://localhost:8082/StackExchangeIS/ServiceAuth";
    
    /**
     * Validate token string for WebService functionality
     * @param token
     * @return 
     */
    public static long validateToken(String token, String ipaddress, String useragent) {
        long user_id = -1;
        
        try {
            
            // establish a connection with the identity service that handles login
            URL url = new URL(URL_AUTH);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           // set the request property
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
     
            String params = String.format("token_str=%s&ipaddress=%s&useragent=%s",
                                            URLEncoder.encode(token, "UTF-8"),
                                            URLEncoder.encode(ipaddress, "UTF-8"),
                                            URLEncoder.encode(useragent, "UTF-8"));
      
            try (OutputStream output = conn.getOutputStream()) {
                output.write(params.getBytes("UTF-8"));
            }
     
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                                    conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
        
            String temp;
            while ((temp = in.readLine()) != null)
                sb.append(temp);
        
            // json parser needed to parse the string
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(sb.toString());
            
                // get the attributes and add the cookie
            long isAuth = (Long) object.get("auth");
            
            if (isAuth > 0)
                user_id = (Long) object.get("user_id");
            else{
                user_id = (Long) object.get("auth");
            }
                
            conn.disconnect();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ValidationToken.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user_id;
    }
}
