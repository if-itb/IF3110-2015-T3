/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import DatabaseWS.DB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Vanji
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    Connection conn = DB.connect();
    @WebMethod(operationName = "register")
    @WebResult(name = "User")
    public int register (@WebParam(name = "name") String name,
                        @WebParam(name = "email") String email,
                        @WebParam(name = "password") String password){
        
        int user = 0;
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE email = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet rs = dbStatement.executeQuery();
            
            
            if (rs != null){
                sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
                dbStatement = conn.prepareStatement(sql);
                ResultSet res = dbStatement.executeQuery();
                user = 1;
                res.close();
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            
        }

        return user;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteUp")
    @WebResult(name = "vUp")
    public int voteUp(@WebParam(name = "access_token") String token, 
                              @WebParam(name = "uid") int u_id,
                              @WebParam(name = "qid") int q_id,
                              @WebParam(name = "aid") int a_id,
                              @WebParam(name = "count") int count)
            throws Exception {
        // cek token (kasih IS)
        
        int Valid;
        String url = "localhost:8082/WBD_IS/testrestservlet/?access_token=" + token;
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String USER_AGENT = "Mozilla/5.0";
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'Get' request to " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
            
        }
        in.close();
                
        System.out.println(response.toString());
        
        if (response.toString() == token){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO vote (u_id, q_id, a_id, count) VALUES(?, ?, ?, 1)";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, u_id);
                dbStatement.setInt(2, q_id);
                dbStatement.setInt(3, a_id);

                ResultSet rs = dbStatement.executeQuery();
                rs.close();
                stmt.close();
                Valid = 1;
            } catch (SQLException e){
                Valid = 0;
            }
            
        }
        else {
            Valid = 0;
        }
        return Valid;
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteDown")
    @WebResult(name = "vUp")
    public int voteDown(@WebParam(name = "access_token") String token, 
                              @WebParam(name = "uid") int u_id,
                              @WebParam(name = "qid") int q_id,
                              @WebParam(name = "aid") int a_id,
                              @WebParam(name = "count") int count)
            throws Exception {
        // cek token (kasih IS)
        
        int Valid;
        String url = "localhost:8082/WBD_IS/testrestservlet/?access_token=" + token;
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String USER_AGENT = "Mozilla/5.0";
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'Get' request to " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
            
        }
        in.close();
                
        System.out.println(response.toString());
        
        if (response.toString() == token){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO vote (u_id, q_id, a_id, count) VALUES(?, ?, ?, -1)";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, u_id);
                dbStatement.setInt(2, q_id);
                dbStatement.setInt(3, a_id);

                ResultSet rs = dbStatement.executeQuery();
                rs.close();
                stmt.close();
                Valid = 1;
            } catch (SQLException e){
                Valid = 0;
            }
            
        }
        else {
            Valid = 0;
        }
        return Valid;
       
    }
}
