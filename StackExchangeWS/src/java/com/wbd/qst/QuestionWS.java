/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.qst;

import com.wbd.ans.Answer;
import com.wbd.tokenChecker.TokenChecker;
import com.wbd.tokenChecker.TokenIdentity;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.jboss.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveQ")
    public ArrayList<Question> retrieveQ() {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                questions.add(new Question(rs.getInt("IDQ"),rs.getInt("IDUser"),rs.getString("QuestionTopic"),rs.getString("Content"),rs.getInt("Vote")));
                ++i;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionbyID")
    public ArrayList<Question> getQuestionbyID(@WebParam(name = "qid") int qid) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question WHERE IDQ = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,qid);
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                questions.add(new Question(rs.getInt("IDQ"),rs.getInt("IDUser"),rs.getString("QuestionTopic"),rs.getString("Content"),rs.getInt("Vote")));
                ++i;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createQ")
    public int createQ(@WebParam(name = "access_token") String access_token, @WebParam(name = "title") String title, @WebParam(name = "content") String content) throws ParseException {
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        token_check.check(access_token);
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        try {
            if (token_check.getValid() == 1){
                //Can access. Right Identity
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO question(IDUser, QuestionTopic, Content, Vote) VALUES (?,?,?,?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, token_check.getIDUser());
                dbStatement.setString(2, title);
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

        }
        return message;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQ")
    public int updateQ(@WebParam(name = "access_token") String access_token, @WebParam(name = "qid") int qid, @WebParam(name = "title") String title, @WebParam(name = "content") String content) {
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");

            // Turn on transactions
            conn.setAutoCommit(false);
            if (token_check.getValid() == 1){
                Statement stmt = conn.createStatement();
                String sql = "SELECT IDQ FROM token NATURAL JOIN question WHERE access_token = ? AND IDQ = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1,access_token);
                dbStatement.setInt(2,qid);
                ResultSet rs = dbStatement.executeQuery();
                if (rs.next()){
                    sql = "UPDATE question SET QuestionTopic = ?, Content = ? WHERE IDQ = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, title);
                    pstmt.setString(2, content);
                    pstmt.setInt(3, qid);
                    pstmt.executeUpdate();
                    conn.commit();
                    message = 1;
                    conn.close();
                } else {
                    message = -3; //It's your not Question. Not authorize!
                }
            } else {
                message = -1; //Wrong identity. Something wrong
            }
            conn.close();
        }
       catch (Exception e) {
            // Any error is grounds for rollback
            try {
                conn.rollback();
            }
            catch (SQLException ignored) { }
                System.out.println("Order failed. Please contact technical support.");
            message = 4;
       }
        return message;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQ")
    public int deleteQ(@WebParam(name = "access_token") String access_token, @WebParam(name = "qid") int qid) {
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (token_check.getExpired() == 1){
            return -2; //Expired
        }
        Connection conn = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
            if (token_check.getValid() == 1){
                Statement stmt = conn.createStatement();
                String sql = "SELECT IDQ FROM token NATURAL JOIN question WHERE access_token = ? AND IDQ = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1,access_token);
                dbStatement.setInt(2,qid);
                ResultSet rs = dbStatement.executeQuery();
                if (rs.next()){
                    sql = "DELETE FROM question WHERE IDQ = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, qid);
                    pstmt.executeUpdate();
                    
                    sql = "DELETE FROM vote_question WHERE IDQ = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, qid);
                    pstmt.executeUpdate();
                    sql = "SELECT IDAns FROM answer WHERE IDQ = ?";
                    PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                    dbStatement2.setInt(1,qid);
                    ResultSet rs2 = dbStatement2.executeQuery();
                    while (rs2.next()){
                        sql = "DELETE FROM vote_answer WHERE IDAns = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, rs2.getInt("IDAns"));
                        pstmt.executeUpdate();
                    }
                    sql = "DELETE FROM answer WHERE IDQ = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, qid);
                    pstmt.executeUpdate();
                    message = 1;
                    conn.close();
                } else {
                    message = -3; //It's your not Question. Not authorize!
                }
            } else {
                message = -1; //Wrong identity. Something wrong
            }
        } catch (SQLException ex){
            System.out.println(ex);
            message = 4;
        }
        return message;
    }    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteUp")
    public int voteUp(@WebParam(name = "access_token") String access_token, @WebParam(name = "qid") String qid) throws ParseException {
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteDown")
    public int voteDown(@WebParam(name = "access_token") String access_token, @WebParam(name = "qid") String qid) {
        //Check Token 
        Connection conn = null;
        int message = 0;
        TokenChecker token_check = new TokenChecker();
        System.out.println("ACCESS TOKEN : " + access_token);
        try {
            token_check.check(access_token);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNama")
    public String getNama(@WebParam(name = "qid") int qid) {
        String result = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
            Statement stmt = conn.createStatement();
            
            String sql;
            //sql = "SELECT Nama FROM user WHERE IDUser = (SELECT IDUser FROM question WHERE IDQ = ?)";
            sql = "SELECT user.Nama FROM user NATURAL JOIN question WHERE IDQ = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,qid);
            
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchQ")
    public ArrayList<Question> searchQ(@WebParam(name = "QuestionTopic") String QuestionTopic, @WebParam(name = "Content") String Content) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question WHERE QuestionTopic LIKE '%" + QuestionTopic + "%' OR (Content LIKE '%" + Content + "%')";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                questions.add(new Question(rs.getInt("IDQ"),rs.getInt("IDUser"),rs.getString("QuestionTopic"),rs.getString("Content"),rs.getInt("Vote")));
                ++i;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex){
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }
}
