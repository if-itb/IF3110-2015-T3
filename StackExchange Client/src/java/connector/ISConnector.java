/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Tifani
 */
public class ISConnector {
    // Path for the Identity Service
    private final static String CONTEXT_PATH = "http://localhost:8082/StackExchange_IdentityService";
    
    public static JSONObject processRequest(String servlet, byte[] query) {
        JSONObject response = null;
        if (servlet != null && !servlet.startsWith("/"))
                servlet = "/" + servlet;
        try {
            // Establish HTTP connection with Identity Service
            URL url = new URL(CONTEXT_PATH + servlet);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            //Create the form content
            try (OutputStream out = conn.getOutputStream()) {
                out.write(query);
                out.close();
            }
            
            // Buffer the result into a string
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            
            rd.close();
            conn.disconnect();
            response = (JSONObject) new JSONParser().parse(sb.toString());
                      
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        return response;
    }
    
    public static JSONObject validateToken(String accessToken) {
        JSONObject response = null;
        try {
            String utf8 = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format("token=%s",
                    URLEncoder.encode(accessToken, utf8));
            response = processRequest("/auth", query.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    public static JSONObject validateLogin(String email, String password) {
        JSONObject response = null;
        try {
            String utf8 = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format("email=%s&password=%s",
                    URLEncoder.encode(email, utf8),
                    URLEncoder.encode(password, utf8));
            response = processRequest("/login", query.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    public static JSONObject vote(String type, String id, String token, String up) {
        JSONObject response = null;
        try {
            String query = String.format("type=%s&id=%s&token=%s&up=%s",
                    type, id, token, up);
            response = processRequest("/vote", query.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    public static JSONObject comment(String type, String id, String token, String up) {
        JSONObject response = null;
        try {
            String query = String.format("type=%s&id=%s&token=%s&up=%s",
                    type, id, token, up);
            response = processRequest("/vote", query.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
