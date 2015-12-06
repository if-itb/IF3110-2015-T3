/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StackExchangeWS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Calvin
 */
@WebService(serviceName = "StackExchangeWS")
public class StackExchangeWS {
    
    DBConnection DB = new DBConnection();
    Connection conn = DB.getConn();
    
    @WebMethod(operationName = "register")
    @WebResult(name="result")
    public String register(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {        
        try {
            String sql = "SELECT email FROM user";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            
            while (rs.next()) {
                if (email.equals(rs.getString("email"))) return "Email already used";
            }
            
            sql = "INSERT INTO user(name, email, password) VALUES(?, ?, ?)";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, name);
            dbStatement.setString(2, email);
            dbStatement.setString(3, password);
            
            dbStatement.executeUpdate();
            
            return "Successfully added user";
        }
        catch (SQLException ex) {
            return "Error adding user";
        }
    }
    
    @WebMethod(operationName = "getQuestion")
    @WebResult(name="Question")
    public Question getQuestion(@WebParam(name = "qid") int qid) {        
        Question q = new Question();
        
        try {
            String sql = "SELECT * FROM question WHERE id = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            while(rs.next()) {
                q = new Question(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("topic"),
                        rs.getString("content"),
                        rs.getInt("vote"));
            }
            
            return q;
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    @WebMethod(operationName = "getRecentQuestions")
    @WebResult(name="Questions")
    public ArrayList<Question> getRecentQuestions() {        
        ArrayList<Question> questions = new ArrayList<Question>();
        
        try {
            String sql = "SELECT * FROM question";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);            
            ResultSet rs = dbStatement.executeQuery();
            
            while(rs.next()) {
                Question q = new Question(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("topic"),
                        rs.getString("content"),
                        rs.getInt("vote"));
                questions.add(q);
            }
            
            return questions;
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    @WebMethod(operationName = "getAnswer")
    @WebResult(name="Answer")
    public ArrayList<Answer> getAnswer(@WebParam(name = "qid") int qid) {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {
            String sql = "SELECT * FROM answer WHERE questionId = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            while(rs.next()) {
                Answer temp = new Answer(
                        rs.getInt("id"),
                        rs.getInt("questionId"),
                        rs.getInt("userId"),
                        rs.getString("content"),
                        rs.getInt("vote"));
                answers.add(temp);
            }
            
            return answers;
        }
        catch (SQLException ex) {
            return null;
        }
    }

    @WebMethod(operationName = "insertQuestion")
    @Oneway
    public void insertQuestion(@WebParam(name = "token") String token, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {        
        if (checkToken(token)) {
            try {
                String sql = "INSERT INTO question(userId, topic, content) VALUES(?, ?, ?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getIdByToken(token);
                dbStatement.setInt(1, userId);
                dbStatement.setString(2, topic);
                dbStatement.setString(3, content);

                dbStatement.executeUpdate();
            }
            catch(SQLException ex) {
            }
        }
    }
    
    @WebMethod(operationName = "updateQuestion")
    @Oneway
    public void updateQuestion(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        if (checkToken(token)) {
            try {
                String sql = "SELECT userId FROM question WHERE id=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, qid);
                ResultSet rs = dbStatement.executeQuery();
                rs.next();
                int userId = rs.getInt("userId");
                
                if (getIdByToken(token) == userId) {
                    sql = "UPDATE question SET topic = ?, content = ? WHERE id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setString(1, topic);
                    dbStatement.setString(2, content);
                    dbStatement.setInt(3, qid);
                    dbStatement.executeUpdate();
                }
            }
            catch(SQLException ex) {
            }
        }
    }
    
    @WebMethod(operationName = "insertAnswer")
    @Oneway
    public void insertAnswer(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid, @WebParam(name = "content") String content) {
        if (checkToken(token)) {
            try {
                String sql = "INSERT INTO answer(questionId, userId, content) VALUES(?, ?, ?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getIdByToken(token);
                dbStatement.setInt(1, qid);
                dbStatement.setInt(2, userId);
                dbStatement.setString(3, content);

                dbStatement.executeUpdate();
            }
            catch(SQLException ex) {
            }
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    @Oneway
    public void deleteQuestion(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid) {
        if (checkToken(token)) {
            try {
                String sql = "SELECT userId FROM question WHERE id=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, qid);
                ResultSet rs = dbStatement.executeQuery();
                rs.next();
                int userId = rs.getInt("userId");
                if (getIdByToken(token) == userId) {
                    sql = "DELETE FROM question WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.executeUpdate();
                }
            }
            catch (SQLException ex) {
            }
        }
    }

    @WebMethod(operationName = "voteUpQuestion")
    @Oneway
    public void voteUpQuestion(@WebParam(name = "token") String token, @WebParam(name = "id") int id) {
        if (checkToken(token)) {
            try {
                String sql = "SELECT * FROM questionvote WHERE userid=? AND questionid=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getIdByToken(token);
                dbStatement.setInt(1, userId);
                dbStatement.setInt(2, id);
                ResultSet rs = dbStatement.executeQuery();

                if (!rs.next()) {
                    sql = "INSERT INTO questionvote(userid, questionid, type) VALUES(?, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    dbStatement.setInt(3, 1);
                    dbStatement.executeUpdate();

                    sql = "SELECT vote FROM question WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int vote = rs.getInt("vote") + 1;

                    sql = "UPDATE question SET vote=? WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, vote);
                    dbStatement.setInt(2, id);
                    dbStatement.executeUpdate();
                }
                else {
                    sql = "SELECT type FROM questionvote WHERE userid=? AND questionid=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int type = rs.getInt("type");

                    if (type == 0) {
                        type = 1;
                        sql = "UPDATE questionvote SET type=? WHERE userid=? AND questionid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, 1);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM question WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") + 1;

                        sql = "UPDATE question SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == 1) {
                        type = 0;
                        sql = "UPDATE questionvote SET type=? WHERE userid=? AND questionid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, 0);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM question WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();
                        rs.next();
                        int vote = rs.getInt("vote") - 1;

                        sql = "UPDATE question SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == -1) {
                        type = 1;
                        sql = "UPDATE questionvote SET type=? WHERE userid=? AND questionid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, 1);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM question WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") + 2;

                        sql = "UPDATE question SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                }
            }
            catch (SQLException ex) {
            }
        }
    }
    
    @WebMethod(operationName = "voteDownQuestion")
    @Oneway
    public void voteDownQuestion(@WebParam(name = "token") String token, @WebParam(name = "id") int id) {
        if (checkToken(token)) {
            try {
                String sql = "SELECT * FROM questionvote WHERE userid=? AND questionid=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getIdByToken(token);
                dbStatement.setInt(1, userId);
                dbStatement.setInt(2, id);
                ResultSet rs = dbStatement.executeQuery();

                if (!rs.next()) {
                    sql = "INSERT INTO questionvote(userid, questionid, type) VALUES(?, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    dbStatement.setInt(3, -1);
                    dbStatement.executeUpdate();

                    sql = "SELECT vote FROM question WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int vote = rs.getInt("vote") - 1;

                    sql = "UPDATE question SET vote=? WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, vote);
                    dbStatement.setInt(2, id);
                    dbStatement.executeUpdate();
                }
                else {
                    sql = "SELECT type FROM questionvote WHERE userid=? AND questionid=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int type = rs.getInt("type");

                    if (type == 0) {
                        type = -1;
                        sql = "UPDATE questionvote SET type=? WHERE userid=? AND questionid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, type);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM question WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") - 1;

                        sql = "UPDATE question SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == 1) {
                        type = -1;
                        sql = "UPDATE questionvote SET type=? WHERE userid=? AND questionid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, type);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM question WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();
                        rs.next();
                        int vote = rs.getInt("vote") - 2;

                        sql = "UPDATE question SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == -1) {
                        type = 0;
                        sql = "UPDATE questionvote SET type=? WHERE userid=? AND questionid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, type);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM question WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") + 1;

                        sql = "UPDATE question SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                }
            }
            catch (SQLException ex) {
            }
        }
    }
    
    @WebMethod(operationName = "voteUpAnswer")
    @Oneway
    public void voteUpAnswer(@WebParam(name = "token") String token, @WebParam(name = "id") int id) {
        if (checkToken(token)) {
            try {
                String sql = "SELECT * FROM answervote WHERE userid=? AND answerid=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getIdByToken(token);
                dbStatement.setInt(1, userId);
                dbStatement.setInt(2, id);
                ResultSet rs = dbStatement.executeQuery();

                if (!rs.next()) {
                    sql = "INSERT INTO answervote(userid, answerid, type) VALUES(?, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    dbStatement.setInt(3, 1);
                    dbStatement.executeUpdate();

                    sql = "SELECT vote FROM answer WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int vote = rs.getInt("vote") + 1;

                    sql = "UPDATE answer SET vote=? WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, vote);
                    dbStatement.setInt(2, id);
                    dbStatement.executeUpdate();
                }
                else {
                    sql = "SELECT type FROM answervote WHERE userid=? AND answerid=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int type = rs.getInt("type");

                    if (type == 0) {
                        type = 1;
                        sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, 1);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM answer WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") + 1;

                        sql = "UPDATE answer SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == 1) {
                        type = 0;
                        sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, 0);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM answer WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();
                        rs.next();
                        int vote = rs.getInt("vote") - 1;

                        sql = "UPDATE answer SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == -1) {
                        type = 1;
                        sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, 1);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM answer WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") + 2;

                        sql = "UPDATE answer SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                }
            }
            catch (SQLException ex) {
            }
        }
    }
    
    @WebMethod(operationName = "voteDownAnswer")
    @Oneway
    public void voteDownAnswer(@WebParam(name = "token") String token, @WebParam(name = "id") int id) {
        if (checkToken(token)) {
            try {
                String sql = "SELECT * FROM answervote WHERE userid=? AND answerid=?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int userId = getIdByToken(token);
                dbStatement.setInt(1, userId);
                dbStatement.setInt(2, id);
                ResultSet rs = dbStatement.executeQuery();

                if (!rs.next()) {
                    sql = "INSERT INTO answervote(userid, answerid, type) VALUES(?, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    dbStatement.setInt(3, -1);
                    dbStatement.executeUpdate();

                    sql = "SELECT vote FROM answer WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int vote = rs.getInt("vote") - 1;

                    sql = "UPDATE answer SET vote=? WHERE id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, vote);
                    dbStatement.setInt(2, id);
                    dbStatement.executeUpdate();
                }
                else {
                    sql = "SELECT type FROM answervote WHERE userid=? AND answerid=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, userId);
                    dbStatement.setInt(2, id);
                    rs = dbStatement.executeQuery();

                    rs.next();
                    int type = rs.getInt("type");

                    if (type == 0) {
                        type = -1;
                        sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, type);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM answer WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") - 1;

                        sql = "UPDATE answer SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == 1) {
                        type = -1;
                        sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, type);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM answer WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();
                        rs.next();
                        int vote = rs.getInt("vote") - 2;

                        sql = "UPDATE answer SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                    else if (type == -1) {
                        type = 0;
                        sql = "UPDATE answervote SET type=? WHERE userid=? AND answerid=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, type);
                        dbStatement.setInt(2, userId);
                        dbStatement.setInt(3, id);
                        dbStatement.executeUpdate();

                        sql = "SELECT vote FROM answer WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, id);
                        rs = dbStatement.executeQuery();

                        rs.next();
                        int vote = rs.getInt("vote") + 1;

                        sql = "UPDATE answer SET vote=? WHERE id=?";
                        dbStatement = conn.prepareStatement(sql);
                        dbStatement.setInt(1, vote);
                        dbStatement.setInt(2, id);
                        dbStatement.executeUpdate();
                    }
                }
            }
            catch (SQLException ex) {
            }
        }
    }
    
    @WebMethod(operationName = "getNameById")
    @WebResult(name="Name")
    public String getNameById(@WebParam(name = "userId") int userId) {
        try {
            String sql = "SELECT name FROM user WHERE id=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, userId);
            ResultSet rs = dbStatement.executeQuery();
            String name = "Name Unknown";
            while(rs.next())
                name = rs.getString("name");
            return name;
        }
        catch (SQLException ex) {
            return "Name Unknown";
        }
    }

    @WebMethod(operationName = "checkToken")
    @WebResult(name = "valid")
    public boolean checkToken(@WebParam(name = "token") String token) {
        try {
            HttpURLConnection connection = null;
            URL url = new URL("http://localhost:8082/IdentityService/ValidateToken?token=" + token);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while((line = rd.readLine()) != null) {
              response.append(line);
            }
            rd.close();
            if (response.toString().equals("true"))
                return true;
            else
                return false;
        }
        catch (MalformedURLException ex) {
            Logger.getLogger(StackExchangeWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        catch (IOException ex) {
            Logger.getLogger(StackExchangeWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @WebMethod(operationName = "getIdByToken")
    @WebResult(name = "userId")
    public int getIdByToken(@WebParam(name = "token") String token) {
        try {
            HttpURLConnection connection = null;
            URL url = new URL("http://localhost:8082/IdentityService/GetIDServlet?token=" + token);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while((line = rd.readLine()) != null) {
              response.append(line);
            }
            rd.close();
            return Integer.parseInt(response.toString());
        }
        catch (MalformedURLException ex) {
            Logger.getLogger(StackExchangeWS.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        catch (IOException ex) {
            Logger.getLogger(StackExchangeWS.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}