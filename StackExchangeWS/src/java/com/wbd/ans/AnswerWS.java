/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.ans;

import com.wbd.tokenChecker.TokenChecker;
import java.util.*;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebResult;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    /**
     * Web service operation
     */    
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name = "Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        //TODO write your implementation code here:
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM answer WHERE IDQ = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                answers.add(new Answer(rs.getInt("IDAns"),rs.getInt("IDQ"),rs.getInt("IDUser"),rs.getString("Answer"),rs.getInt("Vote")));
                ++i;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteUp")
    public int voteUp(@WebParam(name = "access_token") String access_token, @WebParam(name = "ansid") String ansid) {
       //Check Token 
        Connection conn = null;
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        System.out.println("ACCESS TOKEN : " + access_token);
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Validity : " + token_check.getValid());
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        try {
            if (token_check.getValid() == 1){
                //Can Vote. Right Identity
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                String sql = "SELECT * FROM vote_answer NATURAL JOIN token WHERE access_token = ? AND IDAns = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1,access_token);
                dbStatement.setString(2,ansid);
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
                    
                    String sql4 = "INSERT INTO vote_answer(IDUser, IDAns, vote_direction) VALUES(?,?,?)";
                    PreparedStatement dbStatement4 = conn.prepareStatement(sql4);
                    dbStatement4.setInt(1,user_id);
                    dbStatement4.setString(2,ansid);
                    dbStatement4.setInt(3,1);
                     System.out.println("Checkpoint 3");
                    dbStatement4.executeUpdate();
                    
                     conn.setAutoCommit(false);

                    Statement stmt = conn.createStatement();
                    String sql3 = "UPDATE answer SET Vote = Vote + 1 WHERE IDAns = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, ansid);
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteDown")
    public int voteDown(@WebParam(name = "access_token") String access_token, @WebParam(name = "ansid") String ansid) {
       //Check Token 
        Connection conn = null;
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        System.out.println("ACCESS TOKEN : " + access_token);
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Validity : " + token_check.getValid());
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        try {
            if (token_check.getValid() == 1){
                //Can Vote. Right Identity
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                String sql = "SELECT * FROM vote_answer NATURAL JOIN token WHERE access_token = ? AND IDAns = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1,access_token);
                dbStatement.setString(2,ansid);
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
                    
                    String sql4 = "INSERT INTO vote_answer(IDUser, IDAns, vote_direction) VALUES(?,?,?)";
                    PreparedStatement dbStatement4 = conn.prepareStatement(sql4);
                    dbStatement4.setInt(1,user_id);
                    dbStatement4.setString(2,ansid);
                    dbStatement4.setInt(3,0);
                     System.out.println("Checkpoint 3");
                    dbStatement4.executeUpdate();
                    
                     conn.setAutoCommit(false);

                    Statement stmt = conn.createStatement();
                    String sql3 = "UPDATE answer SET Vote = Vote - 1 WHERE IDAns = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, ansid);
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createAns")
    public int createAns(@WebParam(name = "access_token") String access_token, @WebParam(name = "qid") String qid, @WebParam(name = "content") String content) {
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        try {
            if (token_check.getValid() == 1){
                //Can access. Right Identity
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO answer(IDQ, IDUser, Answer, Vote) VALUES (?,?,?,?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, qid);
                dbStatement.setInt(2, token_check.getIDUser());
                dbStatement.setString(3, content);
                dbStatement.setInt(4, 0);
                dbStatement.executeUpdate();
                stmt.close();
                message = 1;
                conn.close();
            }else{
                //Wrong identity. Something wrong
                message = -1;                
            } 
        } catch (Exception ex){
            ex.printStackTrace();
            message = 11;
        }
        return message;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNamaAns")

    public String getNamaAns(@WebParam(name = "idans") int idans) {

        String result = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
            Statement stmt = conn.createStatement();
            
            String sql;

            sql = "SELECT user.Nama FROM user NATURAL JOIN answer WHERE IDAns = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,idans);

            
            ResultSet rs = dbStatement.executeQuery();
            if (rs.next()){
                result = rs.getString("Nama");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            
        }
        return result;
    }

}
