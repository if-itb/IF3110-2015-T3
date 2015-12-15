/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommentVoteISConnector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.jws.WebParam;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class IdentityServiceConnector {
    public static int getUID(@WebParam(name = "token") String token, String ip, String userAgent) throws UnsupportedEncodingException {
        JSONObject obj = new JSONObject();
        String JSON = "";
        String message = "";
        ip = URLEncoder.encode(ip, "UTF-8");
        userAgent = URLEncoder.encode(userAgent, "UTF-8");
        
        int uid = -1;
        
        try {
            JSON = String.valueOf(ConnectionHelper.executeGET("http://localhost:8080/StackExchangeIS/Validation?token=" + URLEncoder.encode(token, "UTF-8") + "&ip=" + ip + "&userAgent=" + userAgent));
            obj = new JSONObject(JSON);             
            uid = obj.getInt("userId");            
            
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }      
        
        return uid;
        
    }
    
    public static String getToken(String email, String password, String ip, String userAgent) throws UnsupportedEncodingException{
        JSONObject obj = new JSONObject();
        String JSON = "";
        String token = "";
       

        ip = URLEncoder.encode(ip, "UTF-8");
        userAgent = URLEncoder.encode(userAgent, "UTF-8");
        
        try {
            JSON = String.valueOf(ConnectionHelper.
                        executeGET("http://localhost:8080/StackExchangeIS/Login?" + "email=" + email + "&password=" + password + "&ip=" + ip + "&userAgent=" + userAgent));
           
            obj = new JSONObject(JSON);
            token = obj.getString("token");
            
            
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }      
        
        return token;
    }
}
