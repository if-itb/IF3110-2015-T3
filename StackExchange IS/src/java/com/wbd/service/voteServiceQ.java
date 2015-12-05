/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.service;

import com.wbd.rest.Token;
import static com.wbd.service.tokenGenerate.generateToken;
import com.wbd.tokenChecker.TokenChecker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 *
 * @author User
 */
@Path("/voteQ")
public class voteServiceQ {
    
    public static int voteUp(String access_token,String qid) throws ParseException {
        //Check Token 
        Connection conn = null;
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        
        System.out.println("ACCESS TOKEN : " + access_token);
        token_check.check(access_token);
        
        System.out.println("Validity : " + token_check.getValid());
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        try {
            if (token_check.getValid() == 1){
                //Can Vote. Right Identity
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                String sql = "SELECT * FROM vote_question NATURAL JOIN token WHERE access_token = ? AND IDQ = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1,access_token);
                dbStatement.setString(2,qid);
                ResultSet rs = dbStatement.executeQuery();
                if (!rs.next()){
                    //Jika gak da vote di database, Bisa vote
                    String sql2 = "SELECT * FROM user NATURAL JOIN token WHERE access_token = ?";
                        PreparedStatement dbStatement2 = conn.prepareStatement(sql2);
                        dbStatement2.setString(1,access_token);
                        ResultSet rs2 = dbStatement2.executeQuery();
                        int user_id = 0;
                        if (rs2.next()){
                            user_id = rs2.getInt("IDUser");
                        }

                        String sql4 = "INSERT INTO vote_question(IDUser,IDQ,vote_direction) VALUES(?,?,?)";
                        PreparedStatement dbStatement4 = conn.prepareStatement(sql4);
                        dbStatement4.setInt(1,user_id);
                        dbStatement4.setString(2,qid);
                        dbStatement4.setInt(3,1);
                         System.out.println("Checkpoint 3");
                        dbStatement4.executeUpdate();

                         conn.setAutoCommit(false);

                        Statement stmt = conn.createStatement();
                        String sql3 = "UPDATE question SET Vote = Vote + 1 WHERE IDQ = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql3);
                        pstmt.setString(1, qid);
                        pstmt.executeUpdate();
                        conn.commit();
                        message = 1;
                        conn.close();
                }
                else{
                    //Jika ada vote di database, gak bisa vote lagi
                    message = -5;
                }
            }else{
                //Wrong identity. Something wrong
                message = -1;                
            } 
        } catch (SQLException e){
            e.printStackTrace();
            message = 11;
        }
        System.out.println("Message : " + message);
        return message;
    }
    
    public int voteDown(String access_token, String qid) {
        Connection conn = null;
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        System.out.println("ACCESS TOKEN : " + access_token);
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            Logger.getLogger(voteServiceQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Validity : " + token_check.getValid());
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        try {
            if (token_check.getValid() == 1){
                //Can Vote. Right Identity
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                String sql = "SELECT * FROM vote_question NATURAL JOIN token WHERE access_token = ? AND IDQ = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1,access_token);
                dbStatement.setString(2,qid);
                ResultSet rs = dbStatement.executeQuery();
                if (!rs.next()){
                    //Jika gak da vote di database, Bisa vote
                    String sql2 = "SELECT * FROM user NATURAL JOIN token WHERE access_token = ?";
                    PreparedStatement dbStatement2 = conn.prepareStatement(sql2);
                    dbStatement2.setString(1,access_token);
                    ResultSet rs2 = dbStatement2.executeQuery();
                    int user_id = 0;
                    if (rs2.next()){
                        user_id = rs2.getInt("IDUser");
                    }
                    
                    String sql4 = "INSERT INTO vote_question(IDUser,IDQ,vote_direction) VALUES(?,?,?)";
                    PreparedStatement dbStatement4 = conn.prepareStatement(sql4);
                    dbStatement4.setInt(1,user_id);
                    dbStatement4.setString(2,qid);
                    dbStatement4.setInt(3,0);
                     System.out.println("Checkpoint 3");
                    dbStatement4.executeUpdate();
                    
                     conn.setAutoCommit(false);

                    Statement stmt = conn.createStatement();
                    String sql3 = "UPDATE question SET Vote = Vote - 1 WHERE IDQ = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, qid);
                    pstmt.executeUpdate();
                    conn.commit();
                    message = 1;
                    conn.close();
                }
                else{
                    //Jika ada vote di database, gak bisa vote lagi
                    message = -5;
                }
            }else{
                //Wrong identity. Something wrong
                message = -1;                
            } 
        } catch (SQLException e){
            e.printStackTrace();
            message = 11;
        }
        System.out.println("Message : " + message);
        return message;
    }

	@POST
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int post() {
            //int result = voteUp(access_token,qid);
            System.out.println("HAHAH : Access TOken " );
            return 10;
	}
    
}
