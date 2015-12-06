package model.question;

import ConnectionIS.ConnectionIS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.jws.Oneway;
import javax.jws.WebParam;
import org.json.*;

/**
 *
 * @author Asanilta
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {
    Connection conn = DB.connect();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllQuestions")
    @WebResult(name="Question")
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        
        try {
                
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT * FROM question";
                
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        questions.add(new Question(rs.getInt("question_id"),rs.getString("topic"),rs.getString("content"),rs.getInt("user_id"),rs.getString("create_time"),rs.getInt("vote")));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionByID")
    @WebResult(name="Question")
    public Question getQuestionByID(@WebParam(name = "question_id") int question_id) {
        Question question = null;
        try {
            String sql = "SELECT * FROM question WHERE question_id= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,question_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                question = new Question(rs.getInt("question_id"),rs.getString("topic"),rs.getString("content"),rs.getInt("user_id"),rs.getString("create_time"),rs.getInt("vote"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return question;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addQuestion")
    @WebResult(name="Integer")
    public int addQuestion(@WebParam(name = "token") String token, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        int qid = -1;
        
        // Send token to IS connector
        // Get response in json format
        org.json.simple.JSONObject jo = ConnectionIS.requestAuth(token);
        
        // Parse json
        int uid = (int) (long)jo.get("id");
        int status = (int) (long) jo.get("status");
        
        // if status ok, insert question into db, select question_id
        if (status == 1){
            try {
                String sql = "INSERT INTO question(topic,content,user_id) VALUES (?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, topic);
                stmt.setString(2, content);
                stmt.setInt(3, uid);
                stmt.executeUpdate();
                stmt.close();
                
                String sql2 = "SELECT question_id FROM question WHERE topic=? AND content=? AND user_id=?";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setString(1, topic);
                stmt2.setString(2, content);
                stmt2.setInt(3, uid);
                ResultSet rs = stmt2.executeQuery();
                
                while (rs.next()) {
                    qid = rs.getInt("question_id");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // alert: fail to add question
        }

        return qid;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editQuestion")
    @WebResult(name="Integer")
    public int editQuestion(@WebParam(name = "token") String token, @WebParam(name = "question_id") int qid, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        // Send token to IS connector
        // Get response in json format
        org.json.simple.JSONObject jo = ConnectionIS.requestAuth(token);
        
        // Parse json
        int uid = (int) (long)jo.get("id");
        int status = (int) (long) jo.get("status");
        
        // if status ok, update question into db, select qid
        if (status == 1){
            try {
                String sql1 = "SELECT user_id FROM question WHERE question_id = ?";
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setInt(1,qid);
                ResultSet rs = stmt1.executeQuery();
                rs.next();
                if (uid == rs.getInt("user_id")) {
                String sql = "UPDATE question SET topic = ?, content = ? WHERE question_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, topic);
                stmt.setString(2, content);
                stmt.setInt(3, qid);
                stmt.executeUpdate();
                stmt.close();
                } else qid=-1;
                stmt1.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            qid = -1;
        }
        
        return qid;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    @WebResult(name="Integer")
    public int deleteQuestion(@WebParam(name = "token") String token, @WebParam(name = "question_id") int question_id) {
        // Send token to IS connector
        // Get response in json format
        org.json.simple.JSONObject jo = ConnectionIS.requestAuth(token);
        
        // Parse json
        int uid = (int) (long)jo.get("id");
        int status = (int) (long) jo.get("status");
        
        // if status ok, update question into db, select qid
        if (status == 1){
            try {
                String sql1 = "SELECT user_id FROM question WHERE question_id = ?";
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setInt(1,question_id);
                ResultSet rs = stmt1.executeQuery();
                rs.next();
                if (uid == rs.getInt("user_id")) {
                String sql = "DELETE FROM question WHERE question_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, question_id);
                stmt.executeUpdate();
                stmt.close();
                } else question_id=-1;
                stmt1.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            question_id = -1;
        }
        
        return question_id;
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchQuestions")
    @WebResult(name="Question")
    public ArrayList<Question> searchQuestions(String query) {
        ArrayList<Question> results = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM question WHERE topic LIKE ? OR content LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,"%"+query+"%");
            stmt.setString(2,"%"+query+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Question(rs.getInt("question_id"),rs.getString("topic"),rs.getString("content"),rs.getInt("user_id"),rs.getString("create_time"),rs.getInt("vote")));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
}
