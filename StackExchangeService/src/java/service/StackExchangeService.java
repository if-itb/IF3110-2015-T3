package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mysql.ConnectDb;
import model.Question;
import java.sql.*;
import java.util.ArrayList;
import model.Answer;
import model.User;


@WebService(serviceName = "StackExchangeService")
public class StackExchangeService {
    
    
    @WebMethod(operationName = "getAllQuestion")
    public ArrayList<Question> getAllQuestion() throws Exception {  
        ArrayList<Question> questions = new ArrayList<>();        

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "select * from questions Order By qid desc";
        PreparedStatement dbStatement = conn.prepareStatement(sql);

        ResultSet rs = dbStatement.executeQuery();
        while(rs.next()){
            questions.add(new Question(
                rs.getInt("qid"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("qtopic"),
                rs.getString("qcontent"),
                rs.getInt("votes"),
                rs.getInt("answer_count"),
                rs.getString("created_at"))
            );
        }
        return questions;
    }

    @WebMethod(operationName = "createQuestion")
    public String createQuestion(
        @WebParam(name = "name") String name,
        @WebParam(name = "email") String email,
        @WebParam(name = "qtopic") String qtopic,
        @WebParam(name = "qcontent") String qcontent
        ) throws Exception {

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "insert into questions(qid, name, email, qtopic, qcontent, votes, answer_count, created_at)" +
        "values (null, ?,?,?,?,0,0,Now())";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setString(1, name);
        dbStatement.setString(2, email);
        dbStatement.setString(3, qtopic);
        dbStatement.setString(4, qcontent);
        int rs = dbStatement.executeUpdate();
        
        return "Create question Success";
    }

    @WebMethod(operationName = "deleteQuestion")
    public String deleteQuestion(
        @WebParam(name = "qid") int qid
        ) throws Exception {

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        
        String sql = "delete from answers where qid = ?";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);
        int rs = dbStatement.executeUpdate();
        
        sql = "delete from questions where qid = ?";
        dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);

        rs = dbStatement.executeUpdate();

        return "Delete question Success";
    }

    @WebMethod(operationName = "editQuestion")
    public String editQuestion(
        @WebParam(name = "qid") int qid,
        @WebParam(name = "name") String name,
        @WebParam(name = "email") String email,
        @WebParam(name = "qtopic") String qtopic,
        @WebParam(name = "qcontent") String qcontent
        ) throws Exception {

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "UPDATE questions SET name = ?, email = ?, qtopic = ?, qcontent = ?" +
        "WHERE qid = ?;";

        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setString(1, name);
        dbStatement.setString(2, email);
        dbStatement.setString(3, qtopic);
        dbStatement.setString(4, qcontent);
        dbStatement.setInt(5, qid);

        int rs = dbStatement.executeUpdate();

        return "Edit question Success";
    }

    @WebMethod(operationName = "getQuestion")
    public Question getQuestion(
        @WebParam(name = "qid") int qid
        ) throws Exception {

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "select * from questions where qid = ?";

        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);

        ResultSet rs = dbStatement.executeQuery();

        while(rs.next()) {
                return new Question(
                        rs.getInt("qid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("qtopic"),
                        rs.getString("qcontent"),
                        rs.getInt("votes"),
                        rs.getInt("answer_count"),
                        rs.getString("created_at"));
        }       
        return null;
    }
    
    @WebMethod(operationName = "getAnswers")
    public ArrayList<Answer> getAnswers(
        @WebParam(name = "qid") int qid
        ) throws Exception {
        
        ArrayList<Answer> answers = new ArrayList<>();

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        
        String sql = "SELECT * FROM answers WHERE qid = ?  ORDER BY  aid DESC";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);

        ResultSet rs = dbStatement.executeQuery();
        while (rs.next()) {
            answers.add(new Answer(
                    rs.getInt("aid"),
                    rs.getInt("qid"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("content"),
                    rs.getInt("votes"),
                    rs.getString("created_at"))
            );
        }
        return answers;
    }
    
    @WebMethod(operationName = "createAnswer")
    public String createAnswer(
            @WebParam(name = "qid") int qid,
            @WebParam(name = "name") String name,
            @WebParam(name = "email") String email,
            @WebParam(name = "content") String content
    ) throws Exception {
        
        
        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "insert into answers(aid, qid, name, email, content, votes, created_at)"
                + "values (null, ?, ?, ?, ?, 0, now())";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);
        dbStatement.setString(2, name);
        dbStatement.setString(3, email);
        dbStatement.setString(4, content);
        int rs = dbStatement.executeUpdate();
        
        sql = "UPDATE questions SET answer_count = answer_count + 1 WHERE qid = ?";
        dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);
        rs = dbStatement.executeUpdate();
        return "Create answer Success";
    }
    
    
    @WebMethod(operationName = "voteQuestion")
    public int voteQuestion(
            @WebParam(name = "qid") int qid,
            @WebParam(name = "operation") String operation
    ) throws Exception {
        
        
        Connection conn = ConnectDb.connect();
        Statement stmt = conn.createStatement();
        PreparedStatement dbStatement;
        String sql = null;
        
        if("up".equals(operation)) {
            sql = "UPDATE questions SET votes=votes + 1 WHERE qid = ?";
            
        }
        else if("down".equals(operation)) {
            sql = "UPDATE questions SET votes=votes-1 WHERE qid = ?";
        }
        
        dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);
        int rs = dbStatement.executeUpdate();
        
        sql = "SELECT votes FROM questions WHERE qid = ?";
        dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);
        ResultSet res = dbStatement.executeQuery();
        
        while(res.next()) {
            return res.getInt("votes");
        }   
        return 0;
    }
    
    @WebMethod(operationName = "voteAnswer")
    public int voteAnswer(
            @WebParam(name = "aid") int aid,
            @WebParam(name = "operation") String operation
    ) throws Exception {

        Connection conn = ConnectDb.connect();
        Statement stmt = conn.createStatement();
        PreparedStatement dbStatement;
        String sql = null;

        if ("up".equals(operation)) {
            sql = "UPDATE answers SET votes=votes + 1 WHERE aid = ?";
        } else if ("down".equals(operation)) {
            sql = "UPDATE answers SET votes=votes - 1 WHERE aid = ?";
        }
        
        dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, aid);
        int rs = dbStatement.executeUpdate();
        
        sql = "SELECT votes FROM answers WHERE aid = ?";
        dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, aid);
        ResultSet res = dbStatement.executeQuery();
        while (res.next()) {
            return res.getInt("votes");
        }
        return 0;
    }
    
    @WebMethod(operationName = "registerUser")
    public int registerUser(
            @WebParam(name = "userName") String name,
            @WebParam(name = "userEmail") String email,
            @WebParam(name = "userPassword") String password
    ) throws Exception {
        
        /* Check that no user with same name */
        
        if (!User.exist(name)) {
            User.create(name, email, password);
        }
        else {
            
        }
        
        return 0;
    }
}


