/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackExchangeWS.services;

import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import stackExchangeWS.database.DbUserManager;
import stackExchangeWS.database.User;

/**
 *
 * @author davidkwan
 */
@WebService(serviceName = "UserHandler")
public class UserHandler {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registerUser")
    public int registerUser(@WebParam(name = "email") String email, @WebParam(name = "password") String password) throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if(DbUserManager.isUserExist(user)){
            // User already exist, return -1
            return -1;
        }
        else{
            DbUserManager.registerUser(user);
            // Registration successful, return 1
            return 1;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "logout")
    public boolean logout(String token) throws SQLException {
        //TODO write your implementation code here:
        DbUserManager.logout(token);
        
        return true;
    }
    
}
