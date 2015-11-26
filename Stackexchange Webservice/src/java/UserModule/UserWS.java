/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModule;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebParam;
import DatabaseModule.Database;
import IdentityServiceModule.IdentityService;
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
    @WebResult(name = "Status")
    public String InsertUser(@WebParam(name = "email") String email,@WebParam(name="name") String name, @WebParam(name ="password") String password) {
        String status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps = null;
        PreparedStatement checkps=null;
        try{
            //cek apakah sudah ada
            String query = "SELECT * FROM UAccount WHERE Email=?";
            checkps = con.prepareStatement(query);
            checkps.setString(1, email);
            ResultSet selRes = checkps.executeQuery();
            if (!selRes.next()){
            String queryins = "INSERT INTO UAccount VALUE (?,?,?)";
                ps = con.prepareStatement(queryins);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.executeUpdate();
            }else{
                status = "Email already used";
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (checkps!=null){checkps.close();}
                if(ps!=null){ ps.close(); con.close();}
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    return status;
    }
    
}
