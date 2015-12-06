/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.ISConnector;

import java.io.IOException;
import java.net.URLEncoder;
import javax.jws.WebParam;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class IdentityServiceConnector {
    public static int getUID(@WebParam(name = "token") String token) {
        JSONObject obj = new JSONObject();
        String JSON = "";
        String message = "";
        int uid = -1;
        
        System.out.println("Masuk ke sini");
        
        try {
            JSON = String.valueOf(ConnectionHelper.executeGET("http://localhost:8080/StackExchangeIS/Validation?token=" + URLEncoder.encode(token, "UTF-8")));
            obj = new JSONObject(JSON);             
            uid = obj.getInt("userId");            
            
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }      
        
        return uid;
        
    }
    
    public static String getToken(String email, String password){
        JSONObject obj = new JSONObject();
        String JSON = "";
        String token = "";
        
        try {
            JSON = String.valueOf(ConnectionHelper.
                        executeGET("http://localhost:8080/StackExchangeIS/Login?email=" + email + "&password=" + password));
            
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
