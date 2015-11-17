/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModule;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import DatabaseModule.Database;
import java.sql.*;

/**
 *
 * @author LUCKY
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "InsertUser")
    public void InsertUser(@WebParam(name = "email") String email,@WebParam(name="name") String name, @WebParam(name ="password") String password) {
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps = null;
        try{
            String query = "INSERT INTO UACCOUNT VALUE (?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.executeQuery();
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
}
