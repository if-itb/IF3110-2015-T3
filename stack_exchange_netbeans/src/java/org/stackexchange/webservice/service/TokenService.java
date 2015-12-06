package org.stackexchange.webservice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.stackexchange.webservice.helper.ConnectionHelper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TokenService {

    ConnectionHelper connectionHelper;

    public TokenService() {
        connectionHelper = new ConnectionHelper();
    }

    public boolean isTokenValid(String token) {
        try {
            return Boolean.valueOf(connectionHelper.executeGET("http://localhost:7000/api/token/check?token=" + token));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isCompleteTokenValid(String token, String ip, String userAgent) {
        try {
            return Boolean.valueOf(connectionHelper.executeGET("http://localhost:7000/api/token/check?token=" + token + "&ip=" + ip + "&user_agent=" + userAgent));
        } catch (IOException e) {
            return false;
        }
    }

    public int getUserId(String token) {
        try {
            return Integer.valueOf(connectionHelper.executeGET("http://localhost:7000/api/id/user?token=" + token));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getUserName(String token) {
        try {
            return connectionHelper.executeGET("http://localhost:7000/api/user?token=" + token);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public Map<String, String> getUserInfo(long userId) {
        try {
            String response = connectionHelper.executeGET("http://localhost:7000/api/user/info?id=" + userId);
            Gson gson = new Gson();
            Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
            return gson.fromJson(response, mapType);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    
    
}
