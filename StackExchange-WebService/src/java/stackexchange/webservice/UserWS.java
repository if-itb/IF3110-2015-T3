/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.servlet.http.Cookie;
import stackexchange.webservice.auth.Auth;
import stackexchange.webservice.model.Answer;
import stackexchange.webservice.model.User;
import stackexchange.webservice.util.Database;

/**
 *
 * @author fauzanrifqy
 */
@WebService(serviceName = "UserWS")
public class UserWS {
    public UserWS(){
        newToken = "";
    }
    private String newToken;
    public String getToken(){
        String a = newToken;
        newToken = "";
        return a;
    }
     /**
     * Web service operation
     */
    @WebMethod(operationName = "addUser")
    public boolean addUser(@WebParam(name = "user") User user) {
        Database db = new Database();
        boolean ret=false;
        try{
                String sql = "select * from users where name='"+user.getName()+"' or email='"+user.getEmail()+"'";
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                        ret = false;
                } else {
                    ret = true;
                    String values="(";
                    values+= "'"+ user.getName() +"',";
                    values+= "'"+ user.getEmail() +"',";
                    values+= "'"+ user.getPassword() +"')";
                    sql="insert into users (name, email, password) values " + values;
                    ps = db.getConnection().prepareStatement(sql);
                    ps.executeUpdate();	
                }
        }catch(Exception e){

        }finally{
            db.closeConnection();
            db = null;
        }
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteUser")
    @Oneway
    public void deleteUser(@WebParam(name = "id") int id) {
        Database db = new Database();
        try{
            String sql="delete from users where id=" + id;
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }finally{
            db.closeConnection();
            //db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUsers")
    @WebResult(name="User")
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Database db = new Database();
        try{
            String sql="select * from users";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                users.add(user);
            }
            return users;
        }catch(Exception e){
            User user = new User();
            users.add(user);
            return users;
        }finally{
            db.closeConnection();
            db = null;
        }
    }
    
    /**
     * Web service operation
     * @param email
     * @param token
     * @return 
     */
    @WebMethod(operationName = "getUser")
    @WebResult(name="User")
    public User getUser(@WebParam(name = "email") String email, @WebParam(name = "token") String token) {
        Database db = new Database();
        User user = new User();
        try{
            Auth auth = new Auth();
            int t = auth.check(email, token);
            String sql="select * from users where email='"+email+"'";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(t == 1||t == 0){
                if (rs.next()) {
                    user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                }
                if(t == 0){
                    this.newToken = auth.getToken();
                }
            }
            return user;
        }catch(Exception e){
            user = new User(-2, email, email, email);
            return user;
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "signOut")
    public boolean signOut(@WebParam(name = "email") String email, @WebParam(name = "token") String token) {
        Database db = new Database();
        try{
            Auth auth = new Auth();
            int t = auth.check(email, token);
            String sql="delete from tokens where email='"+email+"' and token='"+token+"'";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            if(t == 1||t == 0){
                ps.executeUpdate();
                return true;
            }
        }catch(Exception e){
            
        }finally{
            db.closeConnection();
            db = null;
        }
        
        return false;
    }
    
    @WebMethod(operationName = "getUserByEmail")
    @WebResult(name="User")
    public User getUserByEmail(@WebParam(name = "email") String email) {
        Database db = new Database();
        User user = new User();
        try{
            String sql="select * from users where email='"+email+"'";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            }
            return user;
        }catch(Exception e){
            
            return user;
        }finally{
            db.closeConnection();
            db = null;
        }
    }
    
}
