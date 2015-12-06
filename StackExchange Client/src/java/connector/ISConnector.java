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
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author visat
 */
public class ISConnector {
    private static final String CONTEXT_PATH = "http://localhost:8082";

    private static JSONObject request(String servletPath, byte[] query, String userAgent, String remoteAddr) {
        JSONObject object = null;
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

    public static JSONObject requestLogin(String email, String password, String userAgent, String remoteAddr) {
        JSONObject object = null;
        try {
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format(
                    "email=%s&password=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset));
            object = request("/login", query.getBytes(), userAgent, remoteAddr);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return object;
    }

    public static JSONObject requestAuth(String accessToken, String userAgent, String remoteAddr) {
        JSONObject object = null;
        try {
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format(
                    "auth=%s",
                    URLEncoder.encode(accessToken, charset));
            object = request("/auth", query.getBytes(), userAgent, remoteAddr);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return object;
    }

}
