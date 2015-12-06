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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Tifani
 */
public class ISConnector {
    // Path for the Identity Service
    private final static String CONTEXT_PATH = "http://localhost:8082/StackExchange_IdentityService";
    
    public static int validateToken(String accessToken) {
        JSONObject response = null;
        try {
            String utf8 = java.nio.charset.StandardCharsets.UTF_8.name();
            String query = String.format("token=%s",
                    URLEncoder.encode(accessToken, utf8));
     
            // Establish HTTP connection with Identity Service
            URL url = new URL(CONTEXT_PATH + "/auth");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //Create the form content
            try (OutputStream out = conn.getOutputStream()) {
                out.write(query.getBytes());
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
        int u_id = -1;
        if (response!=null && response.containsKey("id")) {
            long id = (long) response.get("id");
            u_id = (int) id;
        }
        return u_id;
    }
}
