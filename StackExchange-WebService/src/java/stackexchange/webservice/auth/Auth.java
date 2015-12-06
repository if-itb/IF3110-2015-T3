/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fauzanrifqy
 */
public class Auth {
    public Auth(){ token=""; }
    private final String AuthURL = "http://localhost:8082/StackExchange-IdentityServices/Check";
    private String token;
    public String getToken(){
        return token;
    }
    
    public int check(String email, String token){
        int ret = -1;
        JSONParser parser = new JSONParser();
        try{
            String charset = "UTF-8";
            
            URL url = new URL(AuthURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            String query = String.format("email=%s&token=%s", 
                                        URLEncoder.encode(email, charset), 
                                        URLEncoder.encode(token, charset));
            
            try (OutputStream output = conn.getOutputStream()) {
                output.write(query.getBytes(charset));
            }
            
            InputStream res = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

            String output;      
            Object obj;
            JSONObject jobj;

            while ((output = br.readLine()) != null) {
                obj = parser.parse(output);
                jobj = (JSONObject) obj;
                
                String atoken = (String) jobj.get("token");
                if ( atoken != null ) {
                    token = atoken;
                }
                if (((String)jobj.get("status")).equals("accept")) {
                  return 1;
                } else if (((String)jobj.get("status")).equals("expire")) {
                  return 0;
                } else {
                  ret = -1;
                }
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
    }
}
