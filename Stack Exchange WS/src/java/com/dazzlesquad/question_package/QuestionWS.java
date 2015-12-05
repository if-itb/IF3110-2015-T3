/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.question_package;
/**
 *
 * @author ryanyonata
 */
import com.dazzlesquad.database_console.DBConnect;
import com.dazzlesquad.answer_package.*;
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


@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    Connection conn; 
    
    public QuestionWS() throws SQLException {
        DBConnect db = new DBConnect();
        conn = db.connect();
    }
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionById")
    @WebResult(name="Question")
    public Question getQuestionById(@WebParam(name = "id") int id) {
        Question questionresult = null;
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Question WHERE id=?";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,id);
            
            ResultSet result = dbStatement.executeQuery();
            
            if (result.next())
            {
                int count= countAnswer(result.getInt("id"));
                UserWS userws = new UserWS();
                User user = userws.getUserById(result.getInt("user_id"));
                questionresult = new Question(result.getInt("id"), result.getInt("user_id"),result.getString("topic"),result.getString("content"), 
                        result.getInt("vote"), result.getString("date"), count, user.getUserName());
            }
            else {
                questionresult = new Question();
            }
            
            result.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questionresult;
    }
    
    @WebMethod(operationName = "getAnswerByQuestionId")
    @WebResult(name="Answer")
    public java.util.ArrayList<Answer> getAnswerByQuestionId(@WebParam(name = "id") int id) {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Answer WHERE question_id=?";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1,id);
            
            ResultSet result = dbStatement.executeQuery();
            
           
            while(result.next()) {
               UserWS userws = new UserWS();
               User user = userws.getUserById(result.getInt("user_id"));
               answers.add(new Answer(result.getInt("id"), result.getInt("question_id"), result.getInt("user_id"),
               result.getString("content"), result.getInt("vote"), result.getString("date"), user.getUserName())); 
               
            }
            
            result.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWS.class.getName()).log
            (Level.SEVERE, null, ex);
        }
        return answers;
        
    }
    
    @WebMethod(operationName = "deleteQuestion")
    @WebResult(name="Success")
    public int deleteQuestion(@WebParam(name = "id") int id, @WebParam(name = "token") String token) {
        int deletesuccessful = 0, questionUserId = 0, tokenUserId = 0;
        try {
            String sql;
            Statement statement = conn.createStatement();

            sql = "SELECT user_id FROM Question WHERE id = ? LIMIT 1";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);

            ResultSet result = dbStatement.executeQuery();

            questionUserId = 0;
            if (result.next()) {
                questionUserId = result.getInt("user_id");
            } else {
                questionUserId = 0;
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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

        if (questionUserId == tokenUserId) {
            try{
                String sql;
                Statement statement = conn.createStatement();

                sql = "DELETE FROM Question WHERE id=?";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, id);
                
                dbStatement.executeUpdate();
                
                statement.close();
                deletesuccessful = 1;

                AnswerWS answers = new AnswerWS();
                answers.deleteAnswer(id);
            } catch (SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
                
        return deletesuccessful;
    }
    
    @WebMethod(operationName = "insertQuestion")
    @WebResult(name="Question")
    public int insertQuestion(@WebParam(name = "Question") Question q, @WebParam(name = "token") String token) {
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
                    sql = "INSERT INTO Question (user_id, topic, content, vote, date) VALUES (?,?,?,0,now())";

                    PreparedStatement dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    dbStatement.setInt(1,tokenUserId);
                    dbStatement.setString(2,q.getQuestionTopic());
                    dbStatement.setString(3,q.getQuestionContent());

                    dbStatement.executeUpdate(); 
                    statement.close();
                
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertsuccessful;
    }
    
    @WebMethod(operationName = "showAllQuestion")
    @WebResult(name="Question")
    public java.util.ArrayList<Question> showAllQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();
        
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Question ORDER BY id DESC";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet result = dbStatement.executeQuery();
            
           
            while(result.next()) {
               int count= countAnswer(result.getInt("id"));
               UserWS userws = new UserWS();
               User user = userws.getUserById(result.getInt("user_id"));
               questions.add(new Question(result.getInt("id"), result.getInt("user_id"),result.getString("topic"),result.getString("content"), 
                       result.getInt("vote"), result.getString("date"), count, user.getUserName())); 
               
            }
            
            result.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log
            (Level.SEVERE, null, ex);
        }
        return questions;
    }
    
    @WebMethod(operationName = "editQuestion")
    @WebResult(name="NewQuestion")
    public int editQuestion(@WebParam(name = "id") int id, @WebParam(name = "topic") String newtopic, @WebParam(name = "content") String newcontent, @WebParam(name = "token") String token) {
        int editsuccessful = 0, questionUserId = 0, tokenUserId = 0;
        try {
            String sql;
            Statement statement = conn.createStatement();

            sql = "SELECT user_id FROM Question WHERE id = ? LIMIT 1";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);

            ResultSet result = dbStatement.executeQuery();

            questionUserId = 0;
            if (result.next()) {
                questionUserId = result.getInt("user_id");
            } else {
                questionUserId = 0;
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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

        if (questionUserId == tokenUserId) {
            try{
                String sql;
                Statement statement = conn.createStatement();

                sql = "UPDATE Question SET topic = ?, content = ? WHERE id = ?";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, newtopic);
                dbStatement.setString(2, newcontent);
                dbStatement.setInt(3, id);

                dbStatement.executeUpdate(); 

                statement.close();
                editsuccessful = 1;

                AnswerWS answers = new AnswerWS();
                answers.deleteAnswer(id);
            } catch (SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return editsuccessful;
    }
    
    @WebMethod(operationName = "countAnswer")
    @WebResult(name="count")
    public int countAnswer(@WebParam(name = "qid") int qid) {
        java.util.ArrayList<Answer> answers = getAnswerByQuestionId(qid);
        int size = answers.size();
        return size;
    }
    
    @WebMethod(operationName = "voteQuestion")
    @WebResult(name = "Success")
    public int voteQuestion(@WebParam(name = "id") int id, @WebParam(name = "token") String token, @WebParam(name = "flag") int flag) {
        int votesuccessful = 0, questionUserId = 0, tokenUserId = 0, isvoted = 0;
        try {
            String sql;
            Statement statement = conn.createStatement();

            sql = "SELECT user_id FROM tokenlist WHERE token = ? LIMIT 1";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);

            ResultSet result = dbStatement.executeQuery();

            if (result.next()) {
                tokenUserId = result.getInt("user_id");
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql;
            Statement statement = conn.createStatement();

            sql = "SELECT id FROM vote_question WHERE user_id = ? AND question_id = ?";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, tokenUserId);
            dbStatement.setInt(2, id);

            ResultSet result = dbStatement.executeQuery();
            statement.close();

            if (result.next()) {

            } else {

                try {
                    statement = conn.createStatement();

                    sql = "UPDATE question SET vote = vote+? WHERE id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, flag);
                    dbStatement.setInt(2, id);
                    dbStatement.executeUpdate();

                    sql = "INSERT INTO vote_question (question_id, user_id) VALUES (?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    dbStatement.setInt(2, tokenUserId);
                    dbStatement.executeUpdate();

                    statement.close();
                    votesuccessful = 1;

                } catch (SQLException ex) {
                    Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return votesuccessful;
    }
}
