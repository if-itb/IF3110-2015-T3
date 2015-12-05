/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import DatabaseAdapter.database;
import IdentityServiceAdapter.ConnectionHelper;
import IdentityServiceAdapter.IdentityValidator;
import java.io.IOException;
import java.sql.Connection;
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

/**
 *
 * @author user
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    database DB = new database();
    
    Connection conn = DB.connect();
    
    @WebMethod(operationName = "getQuestionByQID")
    @WebResult(name="Question")
    public Question getQuestionByQID(@WebParam(name = "qid") int qid) {
        
        Question question = new Question();
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question where Q_ID = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            rs.next();
            int uid = rs.getInt("User_ID");
            int vote = rs.getInt("Vote");
            int answer = rs.getInt("Answer_Count");
            String time = rs.getString("Time");
            String topic = rs.getString("Title");
            String content = rs.getString("Content");
            
            sql = "SELECT Name FROM user where User_ID = ?";
            
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, uid);
            
            rs = dbStatement.executeQuery();
            
            rs.next();
            String uname = rs.getString("Name");
            
            question = new Question(qid, uname, topic, content, vote, time, answer);
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return question;
    }
    
    @WebMethod(operationName = "getAllQuestions")
    @WebResult(name="Questions")
    public ArrayList<Question> getAllQuestions() {
        
        ArrayList<Question> questions = new ArrayList<>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question ORDER BY Time DESC";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);            
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()) {
                int qid = rs.getInt("Q_ID");
                int uid = rs.getInt("User_ID");
                int vote = rs.getInt("Vote");
                int answer = rs.getInt("Answer_Count");
                String time = rs.getString("Time");
                String topic = rs.getString("Title");
                String content = rs.getString("Content");
                
                String retrieveName;
                retrieveName = "SELECT * FROM user where User_ID = ?";
                
                PreparedStatement ps  = conn.prepareStatement(retrieveName);
                ps.setInt(1, uid);
            
                ResultSet rset = ps.executeQuery();
                rset.next();
                
                String uname = rset.getString("Name");         
                
                questions.add(new Question(qid, uname, topic, content, vote, time, answer));     
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    }
    
    @WebMethod(operationName = "createQuestion")    
    @WebResult(name="QuestionID")
    public int createQuestion(@WebParam(name = "token") String token, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        int qid = 0;
        // Call Identity Service
        int uid = IdentityValidator.getUID(token); 
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO question(User_ID,Title,Content,Vote,Answer_Count) VALUES (?,?,?,0,0)";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            dbStatement.setInt(1, uid);
            dbStatement.setString(2, topic);
            dbStatement.setString(3, content);
            
            dbStatement.executeUpdate();
            ResultSet rs = dbStatement.getGeneratedKeys();
            while (rs.next()) {
		qid = rs.getInt(1);
            }            
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return qid;
    }
        
    @WebMethod(operationName = "updateQuestion")    
    @WebResult(name="QuestionID")
    public int updateQuestion(@WebParam(name = "qid") int qid, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content, @WebParam(name = "token") String token) {
        
        // Call Identity Service
        int uid = IdentityValidator.getUID(token);  
        
        try {
            Statement stmt = conn.createStatement();
            
            String isSameUser;
            isSameUser = "SELECT User_ID FROM question WHERE Q_ID = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(isSameUser);
            dbStatement.setInt(1, qid);
            
            ResultSet rsSameUser = dbStatement.executeQuery();
            rsSameUser.next();
            
            int questionUID = rsSameUser.getInt("User_ID");
            
            if (questionUID == uid) {
                try {                            
                    String sql;
                    sql = "UPDATE question SET Title = ?, Content = ? WHERE Q_ID = ?";
                    
                    dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    dbStatement.setString(1, topic);
                    dbStatement.setString(2, content);
                    dbStatement.setInt(3, qid);
                    
                    dbStatement.executeUpdate();
                    ResultSet rs = dbStatement.getGeneratedKeys();
                    while (rs.next()) {
                        qid = rs.getInt(1);
                    }    
                    
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                qid = 0;
            }                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return qid;
    }
    
    @WebMethod(operationName = "voteQuestion")    
    @WebResult(name="QuestionID")
    public int voteQuestion(@WebParam(name = "qid") int qid, @WebParam(name = "vote") int vote) {            
        try {
            Statement stmt = conn.createStatement();
                                    
            String sql;
            sql = "UPDATE question SET Vote = Vote + ? WHERE Q_ID = ?";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, qid);
                    
            dbStatement.executeUpdate();
            ResultSet rs = dbStatement.getGeneratedKeys();
            while (rs.next()) {
                qid = rs.getInt(1);
            }    
                    
            rs.close();
            stmt.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return qid;
    }


    @WebMethod(operationName = "deleteQuestion")    
    @WebResult(name="QuestionID")
    public int deleteQuestion(@WebParam(name = "qid") int qid, @WebParam(name = "token") String token) {
        
        // Call Identity Service
        int uid = IdentityValidator.getUID(token); 
        
        try {
            Statement stmt = conn.createStatement();
            
            String isSameUser;
            isSameUser = "SELECT User_ID FROM question WHERE Q_ID = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(isSameUser);
            dbStatement.setInt(1, qid);
            
            ResultSet rsSameUser = dbStatement.executeQuery();
            rsSameUser.next();
            
            int questionUID = rsSameUser.getInt("User_ID");
            
            if (questionUID == uid) {
                try {                            
                    String sql;
                    sql = "DELETE FROM question WHERE Q_ID = ?";
                    
                    dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    dbStatement.setInt(1, qid);
                    
                    dbStatement.executeUpdate();
                    ResultSet rs = dbStatement.getGeneratedKeys();
                    while (rs.next()) {
                        qid = rs.getInt(1);
                    }
                    
                    sql = "DELETE FROM answer WHERE Q_ID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);                    
                    dbStatement.executeUpdate();
                    
                    rs.close();
                    stmt.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                qid = 0;
            }                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return qid;
    }    
    
    @WebMethod(operationName = "getSearchQuestions")
    @WebResult(name="Questions")
    public ArrayList<Question> getSearchQuestions(@WebParam(name = "keyword") String keyword) {
        
        ArrayList<Question> questions = new ArrayList<>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question WHERE Title LIKE '%?%' OR Content LIKE '%?%' ORDER BY Time DESC";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);           
            dbStatement.setString(1,keyword);
            dbStatement.setString(2,keyword);
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()) {
                int qid = rs.getInt("Q_ID");
                int uid = rs.getInt("User_ID");
                int vote = rs.getInt("Vote");
                int answer = rs.getInt("Answer_Count");
                String time = rs.getString("Time");
                String topic = rs.getString("Title");
                String content = rs.getString("Content");
                
                String retrieveName;
                retrieveName = "SELECT * FROM user where User_ID = ?";
                
                PreparedStatement ps  = conn.prepareStatement(retrieveName);
                ps.setInt(1, uid);
            
                ResultSet rset = ps.executeQuery();
                rset.next();
                
                String uname = rset.getString("Name");         
                
                questions.add(new Question(qid, uname, topic, content, vote, time, answer));     
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    }
}