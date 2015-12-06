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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


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
            sql = "SELECT * FROM question WHERE id=?";
                    
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
            sql = "SELECT * FROM answer WHERE question_id=?";
                    
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

            sql = "SELECT user_id FROM question WHERE id = ? LIMIT 1";

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

                sql = "DELETE FROM question WHERE id=?";

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
        int insertsuccessful = 0; // nanti diganti fungsi validasi
        StringBuilder response = new StringBuilder();
        try {
            JSONParser parser = new JSONParser();
            //try{
            URL url = new URL("http://localhost:8082/Stack_Exchange_IS/Validation");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Authorization", token);
            
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write("");
            writer.flush();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            writer.close();
            reader.close();
            Object obj = 0;
            obj = parser.parse(response.toString());
            JSONObject obj2 = (JSONObject)obj;
            System.out.println(obj2.toString());
            int user_id = 0;
            if (obj != null){
                insertsuccessful = 1;
                obj2.get("user_id");
                user_id = Integer.parseInt(obj2.get("user_id").toString());
            } 
            System.out.println(user_id);
            if (user_id != 0){
                Statement statement = conn.createStatement();
                String sql;
                sql = "INSERT INTO question (user_id, topic, content, vote, date) VALUES (?, ?, ?, 0, now())";

                PreparedStatement dbStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                dbStatement.setInt(1, user_id);
                dbStatement.setString(2, q.getQuestionTopic());
                dbStatement.setString(3, q.getQuestionContent());

                dbStatement.executeUpdate();
                statement.close();                
            }
        } catch (Exception ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
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
            sql = "SELECT * FROM question ORDER BY id DESC";
                    
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

            sql = "SELECT user_id FROM question WHERE id = ? LIMIT 1";

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

                sql = "UPDATE question SET topic = ?, content = ? WHERE id = ?";

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
    
}
