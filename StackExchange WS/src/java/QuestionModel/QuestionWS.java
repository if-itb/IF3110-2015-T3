/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import Auth.Auth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import Database.DB;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.jws.WebResult;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

  /* Connecting to Database */
  /* MANDATORY */
  DB db = new DB();
  Connection conn = db.connect();  
  
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    public int deleteQuestion(@WebParam(name = "token") String token, @WebParam(name = "userIP") String userIP, @WebParam(name = "userAgent") String userAgent, @WebParam(name = "id") int id, @WebParam(name = "uid") int uid) {
      Auth auth = new Auth();
      int ret = auth.check(token,userIP,userAgent);
      
      if ( ret == 1 ) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "DELETE FROM questions WHERE id = ? AND id_user = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            dbStatement.setInt(2, uid);
            
            dbStatement.executeUpdate();
            
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertQuestion")
    public int insertQuestion(@WebParam(name = "token") String token, @WebParam(name = "userIP") String userIP, @WebParam(name = "userAgent") String userAgent, @WebParam(name = "question") Question question) {
      Auth auth = new Auth();
      int ret = auth.check(token,userIP,userAgent);

      if ( ret == 1 ) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "INSERT INTO questions (id_user, topic, content) VALUES (?, ?, ?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, auth.getUserID(token));
            dbStatement.setString(2, question.getTopic());
            dbStatement.setString(3, question.getContent());
            dbStatement.executeUpdate();
            
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    public int updateQuestion(@WebParam(name = "token") String token, @WebParam(name = "userIP") String userIP, @WebParam(name = "userAgent") String userAgent, @WebParam(name = "question") Question question) {
        
      Auth auth = new Auth();
      int ret = auth.check(token,userIP,userAgent);

      if ( ret == 1 ) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "UPDATE questions SET topic = ?, content = ? WHERE id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, question.getTopic());
            dbStatement.setString(2, question.getContent());
            dbStatement.setInt(3, question.getId());
            
            dbStatement.executeUpdate();
            
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      return ret;
    }

    /**
     * Web service operation
     * @param id
     * @return Question
     */
    @WebMethod(operationName = "getQuestionById")
    @WebResult(name="Question")
    public Question getQuesstionById(@WebParam(name = "id") int id) {
        Question res = null;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "SELECT * FROM questions WHERE id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            if(rs.next()){
                res = new Question(rs.getInt("id"),
                                            rs.getInt("id_user"),
                                            rs.getString("topic"),
                                            rs.getString("content"),
                                            rs.getString("timestamp")
                                            );
            }else{
                res = new Question();
            }
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * Web service operation
     * @return ArrayList<Question>
     */
    @WebMethod(operationName = "getAllQuestion")
    @WebResult(name="Question")
    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "SELECT * FROM questions ORDER BY id DESC";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            while(rs.next()){        
                questions.add(new Question( rs.getInt("id"),
                                            rs.getInt("id_user"),
                                            rs.getString("topic"),
                                            rs.getString("content"),
                                            rs.getString("timestamp")
                                ));   
            }
      
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }
        
    @WebMethod(operationName = "getAnswerCount")
    public int getAnswerCount(@WebParam(name = "qid") int qid) {
        int ret = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "SELECT * FROM answers WHERE id_question = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            while(rs.next()){        
              ++ret;
            }
      
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    @WebMethod(operationName = "voteQuestion")
    public int voteQuestion(@WebParam(name = "token") String token, @WebParam(name = "userIP") String userIP, @WebParam(name = "userAgent") String userAgent, @WebParam(name = "qid") int qid, @WebParam(name = "value") int value) {
        
      Auth auth = new Auth();
      int ret = auth.check(token,userIP,userAgent);

      if ( ret == 1 ) {
        try {
            int uid = auth.getUserID(token);
            int count = 0;
            
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            
            sql = "SELECT * FROM vote_question WHERE id_user = ? AND id_question = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, uid);
            dbStatement.setInt(2, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            while(rs.next()){        
              ++count;
            }
            
            if ( count == 0 ) {
              sql = "INSERT INTO vote_question (id_user, id_question, value) VALUES (?, ?, ?)";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, uid);
              dbStatement.setInt(2, qid);
              dbStatement.setInt(3, value);

              dbStatement.executeUpdate();
            } else {
              sql = "UPDATE vote_question SET value = ? WHERE id_user = ? AND id_question = ?";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, value);
              dbStatement.setInt(2, uid);
              dbStatement.setInt(3, qid);

              dbStatement.executeUpdate();              
            }
            
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getVoteCountByQId")
    public int getVoteCountByQId(@WebParam(name = "qid") int qid) {
        int vote_count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT SUM(value) vote_count FROM `vote_question` WHERE id_question = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);

            ResultSet rs = dbStatement.executeQuery();

            while(rs.next()) {
                vote_count = rs.getInt("vote_count");
            }
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote_count;
    }
}
