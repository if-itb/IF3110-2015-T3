package org.stackexchange.webservice.service;

import org.stackexchange.webservice.helper.ConnectionHelper;

import java.io.IOException;

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
    
    public boolean isTokenValid(String token, String ip, String user_agent) {
        try {
            return Boolean.valueOf(connectionHelper.executeGET("http://localhost:7000/api/token/check?token=" + token + "&ip=" + ip + "&user_agent=" + user_agent));
        } catch (IOException e) {
            e.printStackTrace();
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

}
