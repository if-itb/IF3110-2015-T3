/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import Auth.Auth;
import Database.DB;
import QuestionModel.QuestionWS;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

  /* Connecting to Database */
  /* MANDATORY */
  DB db = new DB();
  Connection conn = db.connect();  

  /**
   * Web service operation
   */
  @WebMethod(operationName = "getAnswerByQID")
  @WebResult(name="Answer")
  public java.util.ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
    ArrayList<Answer> answers = new ArrayList<Answer>();
    try {      
      Statement stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM answers WHERE id_question = ?";
      PreparedStatement dbStatement = conn.prepareStatement(sql);
      dbStatement.setInt(1, qid);
      
      ResultSet rs = dbStatement.executeQuery();
      
      // Extract data from result set
      while(rs.next()){        
        answers.add(new Answer( rs.getInt("id"),
                                 rs.getInt("id_question"),
                                 rs.getInt("id_user"),
                                 rs.getString("content"),
                                 rs.getString("timestamp")
                                ));   
      }
      
      rs.close();
      stmt.close();
      //conn.close();
    } catch (SQLException ex) {
      Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
    }
    return answers;
  }
  
  /**
   * Web service operation
   */
  @WebMethod(operationName = "insertAnswer")
  public int insertAnswer(@WebParam(name = "token") String token, @WebParam(name = "answer") Answer answer) {
    
    Auth auth = new Auth();
    int ret = auth.check(token);
    
    if ( ret == 1 ) {
      try {      
        Statement stmt = conn.createStatement();
        String sql;

        sql = "INSERT INTO answers (id_question, id_user, content) VALUES (?, ?, ?)";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, answer.getIdQuestion());
        dbStatement.setInt(2, auth.getUserID(token));
        dbStatement.setString(3, answer.getContent());

        dbStatement.executeUpdate();

        stmt.close();
        //conn.close();
      } catch (SQLException ex) {
        Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
      }   
      
    }
    
    return ret;
    
  }
  
  @WebMethod(operationName = "voteAnswer")
    public int voteAnswer(@WebParam(name = "token") String token, @WebParam(name = "aid") int aid, @WebParam(name = "value") int value) {
        
      Auth auth = new Auth();
      int ret = auth.check(token);

      if ( ret == 1 ) {
        try {
            int uid = auth.getUserID(token);
            int count = 0;
            
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            
            sql = "SELECT * FROM vote_answer WHERE id_user = ? AND id_answer = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, uid);
            dbStatement.setInt(2, aid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            while(rs.next()){        
              ++count;
            }
            
            if ( count == 0 ) {
              sql = "INSERT INTO vote_answer (id_user, id_answer, value) VALUES (?, ?, ?)";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, uid);
              dbStatement.setInt(2, aid);
              dbStatement.setInt(3, value);

              dbStatement.executeUpdate();
            } else {
              sql = "UPDATE vote_answer SET value = ? WHERE id_user = ? AND id_answer = ?";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, value);
              dbStatement.setInt(2, uid);
              dbStatement.setInt(3, aid);

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
    @WebMethod(operationName = "getVoteCountByAId")
    public int getVoteCountByAId(@WebParam(name = "aid") int aid) {
        int vote_count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT SUM(value) vote_count FROM `vote_answer` WHERE id_answer = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);

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
