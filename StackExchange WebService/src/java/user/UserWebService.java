/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import answer.AnswerWebService;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author William Sentosa
 */
@WebService(serviceName = "UserWebService")
public class UserWebService {
    
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
    
    @WebMethod(operationName = "addUser")
    @WebResult(name="String")
    public String addUser(String email, String nama, String password) {
        String query = "INSERT INTO user (email, nama, password) "
                + "VALUES ('" + email + "', '" + nama + "', '" + password + "')";
        Database database = new Database();
        database.connect(path);
        database.changeData(query);
        database.closeDatabase();
        return "executed";
    }
    
    @WebMethod(operationName = "getAllUser")
    @WebResult(name="User")
    public ArrayList<User> getAllUser() {
        ArrayList<User> tab = new ArrayList<User>();
        String query = "SELECT * FROM user ";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                    rs.getString("email"), 
                    rs.getString("nama"), 
                    rs.getString("password")
                );
                tab.add(user);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeDatabase();
        return tab;
    }
    
    @WebMethod(operationName = "isUserExist")
    @WebResult(name="boolean")
    public boolean isUserExist(String email) {
        String query = "SELECT * FROM user WHERE email = '" + email + "';";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            if(rs.next()) {
                rs.close();
                database.closeDatabase();
                return true;
            } else {
                rs.close();
                database.closeDatabase();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @WebMethod(operationName = "isPasswordRight")
    @WebResult(name="boolean")
    public boolean isPasswordRight(String email, String pass) {
        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + pass +"';";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            if(rs.next()) {
                rs.close();
                database.closeDatabase();
                return true;
            } else {
                rs.close();
                database.closeDatabase();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @WebMethod(operationName = "getIdUserFromEmail")
    @WebResult(name="int")
    public int getIdUserFromEmail(String email) {
      String query = "SELECT * FROM user WHERE email = '" + email + "';";
      Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            rs.next();
            int id = rs.getInt("user_id");
            rs.close();
            database.closeDatabase();
            return id;
        } catch (SQLException ex) {
            database.closeDatabase();
            Logger.getLogger(UserWebService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    @WebMethod(operationName = "getUser")
    @WebResult(name="User")
    public User getUser(int id) {
        String query = "SELECT * FROM user WHERE user_id = '" + id + "';";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            rs.next();
            User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("nama"), rs.getString("password"));
            rs.close();
            database.closeDatabase();
            return user;
        } catch (SQLException ex) {
            database.closeDatabase();
            Logger.getLogger(UserWebService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
