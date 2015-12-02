/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import ws.auth.Auth;
import ws.register.RegisterWS;

/**
 *
 * @author yoga
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

    Connection conn = null;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswerbyQID")
    @WebResult(name = "Answer")
    public ArrayList<Answer> getAnswerbyQID(@WebParam(name = "qid") int qid) {

        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            //Take the question
            sql = "SELECT * FROM answers WHERE QuestionID = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            ResultSet rs = dbStatement.executeQuery();
            /* Get every answer for question returned by SQL query */
           
            while (rs.next()) {
                answers.add(new Answer(rs.getInt("AnswerID"),
                        rs.getInt("QuestionID"),
                        rs.getInt("Votes"),
                        rs.getString("Answer"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Datetime")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }   
    
    /**
     * Web service operation up vote an answer
     */
    @WebMethod(operationName = "upAnswer")
    @WebResult(name = "Answer")
    public String upAnswer(@WebParam(name = "AnsId") int AnsId, @WebParam(name = "token") String token) {

        int returnExecution = 0;
        
        String currentEmail = new String("");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String currentAccessToken = token;
            String sql;
            PreparedStatement dbStatement;
            Auth auth = new Auth();
            currentEmail = auth.getEmail(token);
            //Melakukan pengecekan apakah sudah ada atau belum dalam database
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            ResultSet rs = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan jika belum ada insert terlebih dulu
            if(!rs.next()){
                if (!(currentEmail.equals(""))){
                    //Up the the question table
                    sql = "INSERT INTO upanswer (Email,IDAns,totalVote) VALUES(?,?,0)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setString(1, currentEmail);
                    dbStatement.setInt(2, AnsId);
                    dbStatement.executeUpdate();
                } else {
                    return "0";
                }
                
            }
            
            //Melakukan pengecekan apakah sudah pernah di upvote atau tidak
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            rs = dbStatement.executeQuery();
            
            if (rs.next()){
                //jika sudah totalVote == 1 maka dilarang vote up lagi
                returnExecution = rs.getInt("totalVote");
                if(returnExecution == 1){
                    //do nothing because already upvote
                    return ("WRONG");
                } else { //total vote 0 atau -1
                    //search apakah sudah pernah dilakukan vote up atau down sebelumnya 
                    //Up the the question table
                    sql = "UPDATE answers SET Votes = Votes + 1 WHERE AnswerID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.executeUpdate();
                    //Up the the relation of email and question in table upquestion
                    sql = "UPDATE upanswer SET totalVote = totalVote+1 WHERE IDAns = ? AND email = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.setString(2, currentEmail);
                    dbStatement.executeUpdate();
                    return ("Right");
                }
            }
          
            //stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentEmail;
    }
    
    /**
     * Web service operation down vote an answer
     */
    @WebMethod(operationName = "downAnswer")
    @WebResult(name = "Answer")
    public String downAnswer(@WebParam(name = "AnsId") int AnsId, @WebParam(name = "token") String token) {
        int returnExecution = 0;
        
        String currentEmail = new String("");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String currentAccessToken = token;
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            Auth auth = new Auth();
            currentEmail = auth.getEmail(token);
            
            //Melakukan pengecekan apakah sudah ada atau belum dalam database
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            ResultSet rs = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan jika belum ada insert terlebih dulu
            if(!rs.next()){
            
                //Up the the question table
                sql = "INSERT INTO upanswer (Email,IDAns,totalVote) VALUES(?,?,0)";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, currentEmail);
                dbStatement.setInt(2, AnsId);
                dbStatement.executeUpdate();
            }
            
            //Melakukan pengecekan apakah sudah pernah di upvote atau tidak
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            rs = dbStatement.executeQuery();
            
            if (rs.next()){
                //jika sudah totalVote == 1 maka dilarang vote up lagi
                returnExecution = rs.getInt("totalVote");
                if(returnExecution == -1){
                    //do nothing because already upvote
                    return ("WRONG");
                } else { //total vote 0 atau -1
                    //search apakah sudah pernah dilakukan vote up atau down sebelumnya 
                    //Up the the question table
                    sql = "UPDATE answers SET Votes = Votes - 1 WHERE AnswerID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.executeUpdate();
                    //Up the the relation of email and question in table upquestion
                    sql = "UPDATE upanswer SET totalVote = totalVote - 1 WHERE IDAns = ? AND email = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.setString(2, currentEmail);
                    dbStatement.executeUpdate();
                    return ("Right");
                }
            }
          
            //stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentEmail;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "InsertAnswer")
     @WebResult(name = "Answer")
    public int InsertAnswer(@WebParam(name = "qid") int qid ,@WebParam(name = "token") String token, @WebParam(name = "content") String content) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO answers (QuestionID ,Votes,Answer,Name,Email,Datetime) VALUES(?,0,?,?,?,NOW())";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, content);
            Auth auth=new Auth();
            dbStatement.setString(3, auth.getName(token));
            dbStatement.setString(4, auth.getEmail(token));
            dbStatement.executeUpdate();
            /* Get every data returned by SQL query */

                
            sql = "UPDATE questions SET Answers=Answers+1 WHERE QuestionID = ?  ";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return 2;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
}
