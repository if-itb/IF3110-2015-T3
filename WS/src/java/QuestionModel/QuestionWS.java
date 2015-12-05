/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import AnswerModel.Answer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    Connection conn = null;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionbyID")
    @WebResult(name = "Question")
    public ArrayList<Question> getQuestionbyID(@WebParam(name = "id") int id) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM questions WHERE QuestionID = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            ResultSet rs = dbStatement.executeQuery();
            /* Get every data returned by SQL query */
            int i = 0;
            while (rs.next()) {
                questions.add(new Question(rs.getInt("QuestionID"),
                        rs.getInt("Votes"),
                        rs.getInt("Answers"),
                        rs.getString("Topic"),
                        rs.getString("Question"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Datetime")
                ));
                ++i;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getallQuestions")
    @WebResult(name = "Question")
    public ArrayList<Question> getallQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM questions";

            PreparedStatement dbStatement = conn.prepareStatement(sql);

            ResultSet rs = dbStatement.executeQuery();
            /* Get every data returned by SQL query */
            int i = 0;
            while (rs.next()) {
                questions.add(new Question(rs.getInt("QuestionID"),
                        rs.getInt("Votes"),
                        rs.getInt("Answers"),
                        rs.getString("Topic"),
                        rs.getString("Question"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Datetime")
                ));
                ++i;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    @WebResult(name = "Question")
    public int deleteQuestion(@WebParam(name = "id") int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM questions WHERE QuestionID = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);
            dbStatement.executeUpdate();
            /* Get every data returned by SQL query */

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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "InsertQuestion")
    @WebResult(name = "Question")
    public int InsertQuestion(@WebParam(name = "token") String token, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO questions (Votes,Answers,Topic,Question,Name,Email,Datetime) VALUES(0,0,?,?,?,?,NOW())";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, topic);
            dbStatement.setString(2, content);
            Auth auth = new Auth();
            dbStatement.setString(3, auth.getName(token));
            dbStatement.setString(4, auth.getEmail(token));
            dbStatement.executeUpdate();
            /* Get every data returned by SQL query */

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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    public int updateQuestion(@WebParam(name = "topic") String topic, @WebParam(name = "content") String content, @WebParam(name = "id") int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "UPDATE questions SET Topic=?,Question=? WHERE QuestionID=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, topic);
            dbStatement.setString(2, content);
            dbStatement.setInt(3, id);
            dbStatement.executeUpdate();
            /* Get every data returned by SQL query */

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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionSearch")
    public ArrayList<Question> getQuestionSearch(@WebParam(name = "search") String search) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM questions WHERE  Topic LIKE '%" + search + "%' or Question LIKE '%" + search + "%'";

            PreparedStatement dbStatement = conn.prepareStatement(sql);

            ResultSet rs = dbStatement.executeQuery();
            /* Get every data returned by SQL query */
            int i = 0;
            while (rs.next()) {
                questions.add(new Question(rs.getInt("QuestionID"),
                        rs.getInt("Votes"),
                        rs.getInt("Answers"),
                        rs.getString("Topic"),
                        rs.getString("Question"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Datetime")
                ));
                ++i;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getName")
    public String getName(@WebParam(name = "token") String token) {
        Auth auth = new Auth();
        return auth.getName(token);

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEmail")
    public String getEmail(@WebParam(name = "token") String token) {
        Auth auth = new Auth();
        return auth.getEmail(token);

    }

    @WebMethod(operationName = "getExpiredDate")
    public long getExpiredDate(@WebParam(name = "token") String token) {
        Auth auth = new Auth();
        return auth.getExpiredDate(token).getTime();

    }

}
