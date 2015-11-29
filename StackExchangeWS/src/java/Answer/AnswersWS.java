/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Answer;

import Question.QuestionsWS;
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
@WebService(serviceName = "AnswersWS")
public class AnswersWS {

    Connection connAns = DB.getConnection();
    
    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "insertAnswer")
    public int insertAnswer(@WebParam(name = "token") String token,
                            @WebParam(name = "q_id") int q_id,
                            @WebParam(name = "content") String content) {
        
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
        
            try (Statement st = connAns.createStatement()) {

                String query = "INSERT INTO `answers` (uid, qid, content) VALUES (?, ?, ?)";

                // set the prepared statement by the query and enter the value of where clause
                try (PreparedStatement pst = connAns.prepareStatement(query)) {
                    pst.setLong(1, user_id);
                    pst.setInt(2, q_id);
                    pst.setString(3, content);
                    // execute update
                    res = pst.executeUpdate();
                    if (res > 0)
                        res = ValidationToken.AUTH_VALID;
                }

            } catch (SQLException ex) {
                Logger.getLogger(AnswersWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // else: token is invalid, deny request
            res = ValidationToken.AUTH_INVALID;
        }
        
        return res;
    }

    /**
     * Web service operation
     * @param qid
     * @return 
     */
    @WebMethod(operationName = "getAnswersByQid")
    @WebResult(name = "Answer")
    public List<Answer> getAnswersByQid(@WebParam(name = "qid") int qid) {
        //TODO write your implementation code here:
        List<Answer> answers = new ArrayList<>();
        
        try (Statement st = connAns.createStatement()) {
            
            String query = "SELECT * FROM `answers` WHERE qid = ?";
                
            // set the prepared statement by the query and enter the value of where clause
            PreparedStatement pst = connAns.prepareStatement(query);
            pst.setInt(1, qid);
               
            try (ResultSet res = pst.executeQuery()) {
                // get the questions
                while (res.next()) {
                    answers.add(new Answer(res.getInt("id"),
                                            res.getInt("uid"),
                                            res.getInt("qid"),
                                            res.getString("content"),
                                            res.getString("timestamp")));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AnswersWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return answers;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteanswers")
    public int voteanswers(@WebParam(name = "token") String token, @WebParam(name = "aid") int aid, @WebParam(name = "value") int value) {
        int res = ValidationToken.AUTH_ERROR;       // initialize result with error first (assumption)
        long user_id = ValidationToken.validateToken(token); // validate token and get the user id
        
        // token is valid if user_id value is not -1
        if (user_id != -1) {
        
            try (Statement st = connAns.createStatement()) {
                String query = "SELECT * FROM `vote_answers` WHERE uid = ? AND aid = ?";
                try (PreparedStatement pstselect = connAns.prepareStatement(query)) {
                    pstselect.setLong(1, user_id);
                    pstselect.setInt(2, aid);
                    // execute select
                    ResultSet ret = pstselect.executeQuery();
                    if (ret.next() == false){
                        query = "INSERT INTO `vote_answers` (uid, aid, value) VALUES (?, ?, ?)";

                        // set the prepared statement by the query and enter the value of where clause
                        try (PreparedStatement pstins = connAns.prepareStatement(query)) {
                            pstins.setLong(1, user_id);
                            pstins.setInt(2, aid);
                            pstins.setInt(3, value);
                            // execute update
                            res = pstins.executeUpdate();
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
    @WebMethod(operationName = "getanswervote")
    public int getanswervote(@WebParam(name = "aid") int aid) {
        int res = 0;
        ResultSet ret;
        
        try (Statement st = connAns.createStatement()) { 
                String query;
                query = "SELECT value FROM vote_answers WHERE aid = ?";
                // set the prepared statement by the query and enter the value of where clause
                try (PreparedStatement pst = connAns.prepareStatement(query)){
                    pst.setInt(1, aid);
                    // execuResultSette select
                    ret = pst.executeQuery();
                    while (ret.next()) {
                        res += ret.getInt("value");
                    }
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(AnswersWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
}
