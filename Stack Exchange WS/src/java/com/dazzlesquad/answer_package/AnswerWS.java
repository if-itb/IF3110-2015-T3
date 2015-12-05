/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.answer_package;

import com.dazzlesquad.database_console.DBConnect;
import com.dazzlesquad.question_package.QuestionWS;
import com.dazzlesquad.user_package.User;
import com.dazzlesquad.user_package.UserWS;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
/**
 *
 * @author zulvafachrina
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

    Connection conn; 

    public AnswerWS() throws SQLException {
        DBConnect db = new DBConnect();
        conn = db.connect();
    }

    /**
     * Web service operation
     */
   
    
    @WebMethod(operationName = "getAnswerById")
    @WebResult(name="Answer")
    public Answer getAnswerById(@WebParam(name = "id") int id) {
        Answer answerResult = null;
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Answer WHERE id=?";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,id);
            
            ResultSet result = dbStatement.executeQuery();
            
            if (result.next())
            {
               UserWS userws = new UserWS();
               User user = userws.getUserById(result.getInt("user_id"));
               answerResult = new Answer(result.getInt("id"), result.getInt("question_id"), result.getInt("user_id"),
               result.getString("content"), result.getInt("vote"), result.getString("date"), user.getUserName()); 
            }
            else {
                answerResult = new Answer();
            }
            
            result.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return answerResult;
    }
    
    
    @WebMethod(operationName = "insertAnswer")
    @WebResult(name="NewAnswer")
    public int insertAnswer(@WebParam(name = "answer") Answer answer, @WebParam(name = "token") String token) {
        int insertsuccessful = 1, tokenUserId = 0; // nanti diganti fungsi validasi
        
        if (insertsuccessful == 1) {
            try {
                String sql;
                Statement statement = conn.createStatement();

                sql = "SELECT user_id FROM tokenlist WHERE token = ? LIMIT 1";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, token);

                ResultSet result = dbStatement.executeQuery();

                tokenUserId = 0;
                if (result.next()) {
                    tokenUserId = result.getInt("user_id");
                } else {
                    tokenUserId = 0;
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(tokenUserId!=0){
                try {
                    Statement statement = conn.createStatement();
                    String sql;
                    sql = "INSERT INTO Answer (user_id, question_id, content, vote, date) VALUES (?,?,?,0,now())";

                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1,tokenUserId);
                    dbStatement.setInt(2,answer.getQid());
                    dbStatement.setString(3,answer.getContent());
                

                    dbStatement.executeUpdate(); 

                
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertsuccessful;
    }
    
    @WebMethod(operationName = "deleteAnswer")
    @WebResult(name="Success")
    public int deleteAnswer(@WebParam(name = "qid") int qid) {
        int deletesuccessful = 1; // nanti diganti fungsi validasi
        if (deletesuccessful == 1) {
            try {
                String sql;
                Statement statement = conn.createStatement();

                sql = "DELETE FROM Answer WHERE question_id=?";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1,qid);

                dbStatement.executeUpdate(); 

               
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return deletesuccessful;
    }
       
}
