/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author visat
 */
public class VCConnector {
    public static final String CONTEXT_PATH = "http://localhost:8083";

    private static JSONObject request(String servletPath, byte[] query, String userAgent, String remoteAddr) {
        JSONObject object = new JSONObject();
        try {
            if (servletPath == null)
                return object;
            if (!servletPath.startsWith("/"))
                servletPath = "/" + servletPath;
            URLConnection connection = new URL(CONTEXT_PATH + servletPath).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setRequestProperty("Remote-Address", remoteAddr);
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query);
            }
            StringBuilder builder = new StringBuilder();
            BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String read;
            while ((read = buf.readLine()) != null)
                builder.append(read);
            object = (JSONObject)new JSONParser().parse(builder.toString());
        }
        catch (IOException | ParseException ex) {
            System.err.println(ex.getMessage());
        }
        return object;
    }
    
    public static String getComments(String id) {            
        StringBuilder builder = new StringBuilder();        
        try {
            URLConnection connection = new URL(CONTEXT_PATH + "/comment?id=" + id).openConnection();
            BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String read;
            while ((read = buf.readLine()) != null)
                builder.append(read);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }                       
        return builder.toString();
    }

    public static JSONObject requestVote(String auth, String id, String type, String action, String userAgent, String remoteAddr) {
        JSONObject object = new JSONObject();
        try {            
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format(
                    "auth=%s&id=%s&type=%s&action=%s",
                    URLEncoder.encode(auth, charset),
                    URLEncoder.encode(id, charset),
                    URLEncoder.encode(type, charset),
                    URLEncoder.encode(action, charset));
            object = request("/vote", query.getBytes(), userAgent, remoteAddr);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return object;
    }
    
     public static JSONObject requestComment(String auth, String id, String content, String userAgent, String remoteAddr) {
        JSONObject object = new JSONObject();
        try {            
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format(
                    "auth=%s&id=%s&content=%s",
                    URLEncoder.encode(auth, charset),
                    URLEncoder.encode(id, charset),
                    URLEncoder.encode(content, charset));
            object = request("/comment", query.getBytes(), userAgent, remoteAddr);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return object;
     }
    
}
