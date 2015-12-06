/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSModule;

import javax.xml.bind.annotation.*;
import javax.xml.bind.*;
/**
 *
 * @author Jessica
 */
public class UserClass {
    private int userID;
    private String userName;
    private int password;
    private String email;
    private String token;
    private String tokenexpired;
    
    public UserClass(int userID, String userName, int password, String email, String token, String tokenexpired) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.token = token;
        this.tokenexpired = tokenexpired;
    }
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getToken() {
        return token;
    }
    
    public String getTokenExpired() {
        return tokenexpired;
    }
}