/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import models.Question;
//import sun.util.logging.PlatformLogger;
import static connector.ISConnector.validateToken;
/**
 *
 * @author Tifani
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {
    // Open connection to database
    Connection conn = DatabaseController.connect();
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllQuestions")
    @WebResult(name="Question")
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question natural join user GROUP BY date_created DESC";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            /* Get data */
            while (rs.next()) {
                //int qId, int uId, String username, String name, String topic, String content, int vote, int answer, String dateCreated, String dateEdited
                questions.add(new Question (
                    rs.getInt("q_id"),
                    rs.getInt("u_id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("topic"),
                    rs.getString("content"),
                    rs.getInt("vote"),
                    getCountAnswer(rs.getInt("q_id")),
                    rs.getString("date_created"),
                    rs.getString("date_edited")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestion")
    @WebResult(name="Question")
    public Question getQuestion(@WebParam(name = "q_id") int q_id) {
        Question question = new Question();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM question natural join user WHERE q_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, q_id);
            ResultSet rs = dbStatement.executeQuery();
            /* Get data */
            while (rs.next()) {
                //int qId, int uId, String email, String name, String topic, String content, int vote, int answer, String dateCreated, String dateEdited
                question = new Question (
                    rs.getInt("q_id"),
                    rs.getInt("u_id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("topic"),
                    rs.getString("content"),
                    rs.getInt("vote"),
                    getCountAnswer(rs.getInt("q_id")),
                    rs.getString("date_created"),
                    rs.getString("date_edited"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
        }
        return question;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "addNewQuestion")
    @WebResult(name="User ID")
    public int addNewQuestion(@WebParam(name = "token") String token, @WebParam(name = "topic") String topic,  @WebParam(name = "content") String content) {
        int u_id = validateToken(token);
        if(u_id==-1) {
            return -1;
        }
        else {
            try {
                String sql = "INSERT INTO question(u_id,topic,content,date_created) VALUE (?,?,?,now())";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1,u_id);
                dbStatement.setString(2,topic);
                dbStatement.setString(3,content);
                dbStatement.executeUpdate();
                sql = "SELECT q_id FROM question WHERE u_id="+u_id+" ORDER BY date_created DESC LIMIT 1";
                dbStatement = conn.prepareStatement(sql);
                ResultSet rs = dbStatement.executeQuery();
                while(rs.next()) {
                    return rs.getInt("q_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    @WebResult(name="User ID")
    public int deleteQuestion(@WebParam(name = "token") String token, @WebParam(name = "q_id") int q_id) {
        int u_id = validateToken(token);
        if(u_id  == -1) {
            return -1;
        } else {
            try {
                try (Statement stmt = conn.createStatement()) {
                    String sql = "SELECT FROM question WHERE q_id=?";
                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, q_id);
                    ResultSet rs = dbStatement.executeQuery();
                    if(rs.next()) {
                        if(u_id == rs.getInt(u_id)) {
                            sql = "DELETE FROM question WHERE q_id=?";
                            dbStatement = conn.prepareStatement(sql);
                            dbStatement.setInt(1, q_id);
                            dbStatement.executeUpdate();
                        } else {
                            return -1;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCountAnswer")
    @WebResult(name="Answer")
    public int getCountAnswer(@WebParam(name = "q_id") int q_id) {
        int count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql = "select count(a_id) as count from answer where q_id="+q_id+" group by q_id";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("count");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    @WebResult(name="User ID")
    public int updateQuestion(@WebParam(name = "token") String token, @WebParam(name = "q_id") int q_id, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        int u_id = validateToken(token);
        if(u_id  == -1) {
            return -1;
        } else {
            try {
                Statement stmt = conn.createStatement();
                String sql = "SELECT FROM question WHERE q_id=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, q_id);
                ResultSet rs = dbStatement.executeQuery();
                if(rs.next()) {
                    if(u_id == rs.getInt(u_id)) {
                        sql = "UPDATE question SET topic = ?, content = ?, date_edited=now() WHERE q_id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setString(1,topic);
                        dbStatement.setString(2,content);
                        dbStatement.setInt(3, q_id);
                        dbStatement.executeUpdate();
                        stmt.close();
                    }
                } else {
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return q_id;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchQuestion")
    @WebResult(name="List Question")
    public ArrayList<Question> searchQuestion(@WebParam(name = "keyword") String keyword) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Statement stmt = conn.createStatement();
            String key = "%"+keyword+"%"; 
            String sql = "SELECT * FROM question natural join user WHERE topic like ? or content like ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, key);
            dbStatement.setString(2, key);
            ResultSet rs = dbStatement.executeQuery();
            /* Get data */
            while (rs.next()) {
                //int qId, int uId, String email, String name, String topic, String content, int vote, int answer, String dateCreated, String dateEdited
                questions.add(new Question (
                    rs.getInt("q_id"),
                    rs.getInt("u_id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("topic"),
                    rs.getString("content"),
                    rs.getInt("vote"),
                    getCountAnswer(rs.getInt("q_id")),
                    rs.getString("date_created"),
                    rs.getString("date_edited")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
