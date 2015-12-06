/*
 * Nama File : QuestionWebService.java
 */
package question;

import Auth.Auth;
import answer.AnswerWebService;
import database.Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author William Sentosa
 */

@WebService(serviceName = "QuestionWebService")
public class QuestionWebService {
    /* Atribut */
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
    private final String url = "http://localhost:8082/IdentityServices/IdentityChecker";
    /* Method */
    @WebMethod(operationName = "getAllQuestion")
    @WebResult(name="Question")
    public ArrayList<Question> getAllQuestion() {
        String query = "SELECT * FROM question ";
        ArrayList<Question> tab = new ArrayList<>();
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            while (rs.next()) {
                Question question = new Question(rs.getInt("question_id"), 
                    rs.getString("asker_name"), 
                    rs.getString("asker_email"),
                    rs.getString("question_topic"), 
                    rs.getString("question_content"), 
                    rs.getInt("question_vote"),
                    rs.getInt("user_id")
                );
                tab.add(question);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeDatabase();
        return tab;
    }
    
    @WebMethod(operationName = "getQuestionById")
    @WebResult(name="Question")
    public Question getQuestionById(@WebParam(name = "id") int id) {
        Question question;
        String query = "SELECT * FROM question WHERE question_id = " + id;
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            rs.next();
            question = new Question(rs.getInt("question_id"), 
                rs.getString("asker_name"), 
                rs.getString("asker_email"),
                rs.getString("question_topic"), 
                rs.getString("question_content"), 
                rs.getInt("question_vote"),
                rs.getInt("user_id")
            );
            rs.close();
            return question;
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeDatabase();
        return null;
    }
    
    @WebMethod(operationName = "searchQuestion")
    @WebResult(name="Question")
    public List<Question> searchQuestion(String key) {
        Question question;
        List<Question> result = new ArrayList<>();
        String query = "SELECT * FROM question WHERE question_content LIKE '%" + key + "%' OR question_topic LIKE '%" + key + "%'";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            while(rs.next()) {
                question = new Question(rs.getInt("question_id"), 
                    rs.getString("asker_name"), 
                    rs.getString("asker_email"),
                    rs.getString("question_topic"), 
                    rs.getString("question_content"), 
                    rs.getInt("question_vote"),
                    rs.getInt("user_id")
                );
                result.add(question);
            }
            rs.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeDatabase();
        return result;
    }
    
    @WebMethod(operationName = "addQuestion")
    @WebResult(name="String")
    public String addQuestion(String token, String name, String email, String topic, String content, 
            int userId, String ip, String user_agent) throws ProtocolException, MalformedURLException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(userId);
        String uid = sb.toString();
        
        Auth auth = new Auth(token, uid, ip, user_agent);
        
        if(auth.getResponse(url)){
            String query = "INSERT INTO question (question_id, asker_name, asker_email, question_topic, question_content, user_id) "
                    + "VALUES (NULL, '" + name + "', '" + email + "', '" + topic + "', '" + content + "', " + userId + ")";
            Database database = new Database();
            database.connect(path);
            database.changeData(query);
            database.closeDatabase();
            return "executed";
        } else 
            return "not executed";
    }
    
    @WebMethod(operationName = "editQuestion")
    @WebResult(name="String")
    public String editQuestion(String token, int id, String topic, String content, 
            int userId, String ip, String user_agent) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(userId);
        String uid = sb.toString();
        Auth auth = new Auth(token, uid, ip, user_agent);
        if(auth.getResponse(url)){
            String query = "UPDATE question SET question_topic='" + topic + "', question_content='"
                    + content + "' WHERE question_id = " + id + " AND user_id = " + userId;
            Database database = new Database();
            database.connect(path);
            database.changeData(query);
            database.closeDatabase();
            return "executed";
        } else return "not executed";
    }
    
    @WebMethod(operationName = "deleteQuestion")
    @WebResult(name="String")
    public String deleteQuestion(String token, int id, int userId, String ip, String user_agent) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(userId);
        String uid = sb.toString();
        Auth auth = new Auth(token, uid, ip, user_agent);
        if(auth.getResponse(url)){
            String query = "DELETE FROM question WHERE question_id = " + id + " AND user_id = " + userId;
            Database database = new Database();
            database.connect(path);
            database.changeData(query);
            database.closeDatabase();
            return "executed";
        } else return "not executed";
    }
    
    @WebMethod(operationName = "incrVote")
    @WebResult(name="String")
    public String incrVote(String token, int id, int userId, String ip, String user_agent) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(userId);
        String uid = sb.toString();
        Auth auth = new Auth(token, uid, ip, user_agent);
        if(auth.getResponse(url)){
            String sql = "SELECT * FROM question_vote WHERE question_id = " + id + " AND user_id = " + userId;
            String result;
            Database database = new Database();
            database.connect(path);
            ResultSet rs = database.fetchData(sql);
            try {
                if(!rs.next()) {
                    String query = "UPDATE question SET question_vote = question_vote + 1 WHERE question_id= " + id;
                    result = database.changeData(query);
                    query = "INSERT INTO question_vote (user_id, question_id) VALUES (" + userId + ", " + id + ")";
                    result = database.changeData(query);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AnswerWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            database.closeDatabase();
            return "executed";
        } else {
            return "not executed";
        }
    }
    
    @WebMethod(operationName = "decrVote")
    @WebResult(name="String")
    public String decrVote(String token, int id, int userId, String ip, String user_agent) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(userId);
        String uid = sb.toString();
        Auth auth = new Auth(token, uid, ip, user_agent);
        if(auth.getResponse(url)){
            String sql = "SELECT * FROM question_vote WHERE question_id = " + id + " AND user_id = " + userId;
            String result;
            Database database = new Database();
            database.connect(path);
            ResultSet rs = database.fetchData(sql);
            try {
                if(rs.next()) {
                    String query = "UPDATE question SET question_vote = question_vote - 1 WHERE question_id= " + id;
                    result = database.changeData(query);
                    query = "INSERT INTO question_vote (user_id, question_id) VALUES (" + userId + ", " + id + ")";
                    result = database.changeData(query);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AnswerWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            database.closeDatabase();
            return "executed";
        } else {
            return "not executed";
        }
    }
    
}
