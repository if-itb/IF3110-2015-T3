/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.DB;
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
import model.Answer;
import model.Question;
import model.User;

/**
 *
 * @author visat
 */
@WebService(serviceName = "StackExchange")
public class StackExchange {
    private final Connection connection = DB.connect();
    
    /**
     * Get question by ID
     * @param id
     * @return Question
     */
    @WebMethod(operationName = "getQuestion")
    @WebResult(name = "Question")
    public Question getQuestion(@WebParam(name = "id") final int id) {        
        Question question = null;        
        try {
            String sql ="SELECT * FROM question WHERE id = ?";            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);                
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next())
                        question = new Question(
                            result.getInt("id"),
                            result.getInt("id_user"),
                            result.getString("topic"),
                            result.getString("content"),
                            result.getInt("votes"),
                            result.getString("timestamp")
                        );
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return question;
    }

    /**
     * Get list of all questions from database
     * @return List of all questions     
     */
    @WebMethod(operationName = "getQuestions")
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();        
        try {
            String sql ="SELECT * FROM question ORDER BY timestamp DESC";
            try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
                while (result.next())
                    questions.add(new Question(
                            result.getInt("id"),
                            result.getInt("id_user"),
                            result.getString("topic"),
                            result.getString("content"),
                            result.getInt("votes"),
                            result.getString("timestamp")
                    ));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return questions;
    }

    /**
     * Add question to database
     * @param idUser
     * @param topic
     * @param content
     * @return Boolean true if success, false otherwise     
     */
    @WebMethod(operationName = "addQuestion")
    public Question addQuestion(
            @WebParam(name = "idUser") final int idUser,
            @WebParam(name = "topic") final String topic,
            @WebParam(name = "content") final String content) {
        Question question = null;
        try {
            String query = "INSERT INTO "
                    + "question (id_user, topic, content)"
                    + "VALUES (?, ?, ?)";                        
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, idUser);
                statement.setString(2, topic);
                statement.setString(3, content);                
                if (statement.executeUpdate() > 0) {
                    ResultSet result = statement.getGeneratedKeys();
                    if (result.next()) {
                        int id = result.getInt(1);
                        question = getQuestion(id);
                    }
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return question;
    }

    /**
     * Delete question from database
     * @param id
     * @return Boolean true if success, false otherwise     
     */
    @WebMethod(operationName = "deleteQuestion")
    public boolean deleteQuestion(@WebParam(name = "id") final int id) {
        boolean success = false;        
        try {
            String sql = "DELETE FROM question WHERE id = ?";                        
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);                
                success = statement.executeUpdate() > 0;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return success;
    }

    /**
     * Update question
     * @param id
     * @param topic
     * @param content
     * @return boolean     
     */
    @WebMethod(operationName = "updateQuestion")
    public boolean updateQuestion(
            @WebParam(name = "id") final int id,
            @WebParam(name = "topic") final String topic,
            @WebParam(name = "content") final String content) {
        boolean success = false;        
        try {
            String sql = "UPDATE question SET topic = ?, content = ? WHERE id = ?";                        
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, topic);
                statement.setString(2, content);
                statement.setInt(3, id);
                
                success = statement.executeUpdate() > 0;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);            
        }        
        return success;        
    }
    
    
    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "getAnswer")
    @WebResult(name = "Answer")
    public Answer getAnswer(@WebParam(name = "id") int id) {
        Answer answer = null;
        try{
            String sql = "SELECT * FROM answer WHERE id = ?";            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    answer = new Answer(result.getInt("id"),
                        result.getInt("id_question"),
                        result.getInt("id_user"),
                        result.getString("content"),
                        result.getInt("votes"),
                        result.getString("timestamp"));
                }
            }
        }
        catch(SQLException ex){
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);
        }                                
        return answer;
    }
    
    /**
     * Web service operation
     * @param idQuestion
     * @return 
     */
    @WebMethod(operationName = "getAnswers")
    @WebResult(name = "Answer")
    public List<Answer> getAnswers(@WebParam(name = "idQuestion") int idQuestion) {
        List<Answer> answers = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM answer WHERE id_question = ?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idQuestion);
            
            try (ResultSet result = statement.executeQuery()) {
                while(result.next()){
                    answers.add(new Answer(result.getInt("id"),
                            result.getInt("id_question"),
                            result.getInt("id_user"),
                            result.getString("content"),
                            result.getInt("votes"),
                            result.getString("timestamp")
                    ));
                }
            }
        }
        catch(SQLException ex){
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        return answers;
    }

    /**
     * Web service operation
     * @param idQuestion
     * @param content
     * @param idUser
     * @return 
     */
    @WebMethod(operationName = "addAnswer")
    @WebResult(name = "Answer")
    public Answer addAnswer(@WebParam(name = "idQuestion") int idQuestion,
                            @WebParam(name = "idUser") int idUser, 
                            @WebParam(name = "content") String content) {
        Answer answer = null;
        try{
            String query = "INSERT INTO answer (id_question, id_user, content) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, idQuestion);
                statement.setInt(2, idUser);
                statement.setString(3, content);

                if (statement.executeUpdate() > 0) {
                    ResultSet result = statement.getGeneratedKeys();
                    if (result.next()) {
                        int id = result.getInt(1);
                        answer = getAnswer(id);
                    }
                }
            }
        }
        catch(SQLException ex){
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return answer;
    }    
    
    /**
     * Get answer vote state from a user
     * @param idUser
     * @param idAnswer
     * @return Return 0 if user hasn't vote, 1 if vote up, -1 if vote down
     */
    @WebMethod(operationName = "getAnswerVoteState")
    @WebResult(name = "Answer")
    public int getAnswerVoteState(
            @WebParam(name = "idUser") int idUser,
            @WebParam(name = "idAnswer") int idAnswer) {
        int state = 0;
        try{
            String sql = "SELECT vote_up FROM vote_answer WHERE id_user = ? AND id_answer = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idUser);
                statement.setInt(2, idAnswer);

                try (ResultSet result = statement.executeQuery()) {
                    if (result.next())
                        state = result.getBoolean(1) ? 1 : -1;
                }
            }
        }
        catch(SQLException ex){
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        return state;
    }
    
    /**
     * Web service operation
     * @param name
     * @param email
     * @param password
     * @return 
     */
    @WebMethod(operationName = "addUser")
    @WebResult(name = "User")
    public String addUser(
            @WebParam(name = "name")
                    String name, @WebParam(name = "email")
                            String email, @WebParam(name = "password")        
                                    String password) {        
        String message = "";
        try {
            // validate user's email, same email can't register
            String sql = "SELECT email FROM user WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet temp = statement.executeQuery();
            if(!temp.next()){
                sql = "INSERT INTO user (name, email, password) VALUES (?, ?, MD5(?))";
                statement = connection.prepareStatement(sql);                            
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, password);

                if(statement.executeUpdate() > 0)
                    message = "Register Success";
                else
                    message = "Register Failed";
            }
            else{
                message = "Email already registered";
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);          
        }
        
        return message;
    }

    /**
     * Web service operation
     * @param id
     * @return
     */
    @WebMethod(operationName = "getUser")
    @WebResult(name = "User")
    public User getUser(@WebParam(name = "id") final int id) {
        //TODO write your implementation code here:
        User user = null;
        try{
            String sql = "SELECT * FROM user WHERE id = ?";            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    user = new User (result.getInt("id"),
                        result.getString("password"),
                        result.getString("name"),
                        result.getString("email"));
                }
            }
        }
        catch(SQLException ex){
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);
        }                                
        return user;
    }

    /**
     * Web service operation
     * @param key
     * @return
     */
    @WebMethod(operationName = "search")
    public List<Question> search(@WebParam(name = "query")
            String key) {
        //TODO write your implementation code here:
        List<Question> question = new ArrayList<>();
        try{
            String query = "SELECT * FROM question WHERE topic LIKE ? OR content LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%"+key+"%");
            statement.setString(2, "%"+key+"%");
            
            ResultSet result = statement.executeQuery();
            while(result.next()){
                question.add(new Question(
                    result.getInt("id"),
                    result.getInt("id_user"),
                    result.getString("topic"),
                    result.getString("content"),
                    result.getInt("votes"),
                    result.getString("timestamp")
                ));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(StackExchange.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
}
