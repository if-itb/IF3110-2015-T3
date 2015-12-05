/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class IdentityValidator {
    public static int getUID(String token) {
        JSONObject obj = new JSONObject();
        String JSON = "";
        String message = "";
        int uid = 0;
        
        try {
            JSON = String.valueOf(ConnectionHelper.executeGET("http://localhost:8080/IdentityService/ValidationServlet?token=" + token));
            obj = new JSONObject(JSON);
            message = obj.getString("message");
            
            if ("valid".equals(message)) {                
                uid = obj.getInt("User_ID");
            }
            
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ex) {     
            Logger.getLogger(IdentityValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return uid;
        
    }
}
