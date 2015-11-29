/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import Validation.ValidationToken;
import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Ahmad Naufal Farhan
 */
@WebService(serviceName = "QuestionsWS")
public class QuestionsWS {
    
    Connection conn = DB.getConnection();
            
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionById")
    @WebResult(name = "Question")
    public Question getQuestionById(@WebParam(name = "qid") int qid) {
        Question question = null;
        
        try (Statement st = conn.createStatement()) { 
                String query;
                query = "SELECT * FROM questions WHERE id = ?";
                
                // set the prepared statement by the query and enter the value of where clause
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, qid);
            
                // create a new question object with the result
            try (ResultSet res = pst.executeQuery()) {
                if (res.next())
                    question = new Question(res.getInt("id"),
                            res.getInt("uid"),
                            res.getString("topic"),
                            res.getString("content"),
                            res.getString("timestamp"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return question;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getAllQuestions")
    @WebResult(name = "Questions")
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        
        try (Statement st = conn.createStatement()) {
            
            String query = "SELECT * FROM `questions` ORDER BY timestamp DESC";
            // set the prepared statement by the query and enter the value of where clause
            PreparedStatement pst = conn.prepareStatement(query);
               
            try (ResultSet res = pst.executeQuery()) {
                // get the questions
                while (res.next()) {
                    questions.add(new Question(res.getInt("id"),
                            res.getInt("uid"),
                            res.getString("topic"),
                            res.getString("content"),
                            res.getString("timestamp")));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createQuestion")
    public int createQuestion(@WebParam(name = "token") String token,
                                @WebParam(name = "topic") String topic,
                                @WebParam(name = "content") String content) {
        
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
        
            try (Statement st = conn.createStatement()) {

                String query = "INSERT INTO `questions` (uid, topic, content) VALUES (?, ?, ?)";

                // set the prepared statement by the query and enter the value of where clause
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setLong(1, user_id);
                    pst.setString(2, topic);
                    pst.setString(3, content);
                    // execute update
                    res = pst.executeUpdate();
                    
                    if (res == 1)
                        res = ValidationToken.AUTH_VALID;
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // else: token is invalid, deny request
            res = ValidationToken.AUTH_INVALID;
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    public int updateQuestion(@WebParam(name = "token") String token,
                                @WebParam(name = "qid") int qid,
                                @WebParam(name = "newTopic") String newTopic,
                                @WebParam(name = "newContent") String newContent) {
        
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
        
            try (Statement st = conn.createStatement()) {

                String query = "UPDATE `questions` SET topic = ?, content = ? WHERE id = ?";

                // set the prepared statement by the query and enter the value of where clause
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, newTopic);
                    pst.setString(2, newContent);
                    pst.setInt(3, qid);
                    // execute update
                    res = pst.executeUpdate();
                    if (res > 0)
                        res = ValidationToken.AUTH_VALID;
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // else: token is invalid, deny request
            res = ValidationToken.AUTH_INVALID;
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    public int deleteQuestion(@WebParam(name = "token") String token,
                                @WebParam(name = "qid") int qid) {
        
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
        
            try (Statement st = conn.createStatement()) {

                String query = "DELETE FROM `questions` WHERE id = ?";

                // set the prepared statement by the query and enter the value of where clause
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setInt(1, qid);
                    // execute update
                    res = pst.executeUpdate();
                    if (res > 0)
                        res = ValidationToken.AUTH_VALID;
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // else: token is invalid, deny request
            res = ValidationToken.AUTH_INVALID;
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "votequestion")
    public int votequestion(@WebParam(name = "token") String token,
                            @WebParam(name = "qid") int qid,
                            @WebParam(name = "value") int value) {
        //TODO write your implementation code here:
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
            
            try (Statement st = conn.createStatement()) {
                String query = "SELECT * FROM `vote_questions` WHERE uid = ? AND qid = ?";
                try (PreparedStatement pstselect = conn.prepareStatement(query)) {
                    pstselect.setLong(1, user_id);
                    pstselect.setInt(2, qid);
                    // execute select
                    ResultSet ret = pstselect.executeQuery();
                    if (ret.next() == false){
                        query = "INSERT INTO `vote_questions` (uid, qid, value) VALUES (?, ?, ?)";

                        // set the prepared statement by the query and enter the value of where clause
                        try (PreparedStatement pst = conn.prepareStatement(query)) {
                            pst.setLong(1, user_id);
                            pst.setInt(2, qid);
                            pst.setInt(3, value);
                            // execute update
                            res = pst.executeUpdate();
                            if (res > 0)
                                res = ValidationToken.AUTH_VALID;
                        }
                    } 
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // else: token is invalid, deny request
            res = ValidationToken.AUTH_INVALID;
        }
        
        return res;    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getquestionvote")
    public int getquestionvote(@WebParam(name = "qid") int qid) {
        int res = 0;

        try (Statement st = conn.createStatement()) { 
            String query = "SELECT value FROM vote_questions WHERE qid = ?";
            
            // set the prepared statement by the query and enter the value of where clause
            try (PreparedStatement pst = conn.prepareStatement(query)){
                pst.setInt(1, qid);
                // execuResultSette select
                ResultSet ret = pst.executeQuery();
                while (ret.next()) {
                    res += ret.getInt("value");
                }
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchQuestions")
    @WebResult(name = "questions")
    public List<Question> searchQuestions(@WebParam(name = "keyword") String keyword) {
        List<Question> questions = new ArrayList<>();
        
        try (Statement st = conn.createStatement()) {
            
            String query = "SELECT * FROM `questions` WHERE topic LIKE ? OR content LIKE ?";
            // set the prepared statement by the query and enter the value of where clause
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, "%"+ keyword +"%");
            pst.setString(2, "%"+ keyword +"%");
               
            try (ResultSet res = pst.executeQuery()) {
                // get the questions
                while (res.next()) {
                    questions.add(new Question(res.getInt("id"),
                            res.getInt("uid"),
                            res.getString("topic"),
                            res.getString("content"),
                            res.getString("timestamp")));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    }

    
}
