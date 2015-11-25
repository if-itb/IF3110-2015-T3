/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nasipadang.jaxws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.data.Answer;
import org.data.Question;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import static java.lang.Math.toIntExact;

/**
 *
 * @author user
 */
@WebService(endpointInterface = "org.jaxws.StackExchange")
public class StackExchangeImpl implements StackExchange {
    private Connection connection;
    private void connectDB() throws SQLException{
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
        String url = "jdbc:mysql://localhost:3306/db_stackexchange";
        String username = "root";
        String password = "";
        connection = null;
        System.out.println("Connecting database...");
        connection = (Connection) DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
    }
    private void closeDB() throws SQLException{
        connection.close();
    }
    private int whoIs(String token){
        String url = ("http://localhost:8080/NasiPadang/rest/identity?token=" + token);
        int id_user = 0;
        try{
            HttpClient c = new DefaultHttpClient();        
            HttpGet p = new HttpGet(url);        
 
            HttpResponse r = c.execute(p);
 
            BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
            String line = "";
            JSONObject o = null;
            while ((line = rd.readLine()) != null) {
               //Parse our JSON response        
               JSONParser j = new JSONParser();
               o = (JSONObject)j.parse(line);
            }
            if(o.get("status").equals("ok")){
                long id_us = (long) o.get("id_user");
                id_user = toIntExact(id_us);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return id_user;
    }
    @Override
    public boolean register(String username, String email, String password){
        boolean success = false;
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = ("SELECT email FROM user WHERE email = '" + email + "'");
            ResultSet rs = st.executeQuery(sql);
            if(!rs.next()){
                closeDB();
                connectDB();
                st = connection.createStatement();
                sql = ("INSERT INTO user (name, email, password) VALUES ('"+ username +"', '"+ email +"', '"+ password +"')");
                st.execute(sql);
                closeDB();
                success = true;
            }
            else closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
    @Override
    public String login(String email, String password){
        String token = "fail";
        String url = ("http://localhost:8080/NasiPadang/rest/identity");
        try{
            HttpClient c = new DefaultHttpClient();        
            HttpPost p = new HttpPost(url);        
            ArrayList<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("email", email));
            postParameters.add(new BasicNameValuePair("password", password));

            p.setEntity(new UrlEncodedFormEntity(postParameters));
            HttpResponse r = c.execute(p);
 
            BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
            String line = "";
            JSONObject o = null;
            while ((line = rd.readLine()) != null) {
               //Parse our JSON response        
               JSONParser j = new JSONParser();
               o = (JSONObject)j.parse(line);
            }
            if(o.get("status").equals("ok")){
                token = (String) o.get("token");
                
                System.out.println("token : " + token);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return token;
    }
    /**
     * Web service operation
     * @param token
     * @param id
     * @return 
     */
    @Override
    public Question getQuestion(String token, int id) {
        int id_user = 0;
        if(!token.equals("")) id_user = whoIs(token);
        Question qu = new Question();
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = ("SELECT question.id, (SELECT name FROM user WHERE user.id_user = question.id_user) as name, question.topic, question.content, question.timestamp, (SELECT SUM(vote_question.value) as votes FROM vote_question WHERE vote_question.id = question.id) as votes, COUNT(answer.id) AS count FROM (question LEFT JOIN answer ON question.id = answer.id) WHERE question.id = '" + id + "'");
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                qu.id = rs.getInt("id");
                qu.name = rs.getString("name");
                qu.topic = rs.getString("topic");
                qu.content = rs.getString("content");
                qu.timestamp = rs.getString("timestamp");
                qu.vote = rs.getInt("votes");
                qu.count = rs.getInt("count");
            }
            closeDB();
            if(id_user != 0){
                connectDB();
                st = connection.createStatement();
                sql = "SELECT * FROM vote_question WHERE id = '" + qu.id + "' AND id_user = '" + id_user + "'";
                rs = st.executeQuery(sql);
                if(rs.next()) qu.hasVote = true;
                else qu.hasVote = false;
                closeDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qu;
    }

    /**
     * Web service operation
     * @return 
     */
    @Override
    public Question[] getAllQuestion(String token) {
        int id_user = 0;
        if(!token.equals("")) id_user = whoIs(token);
        ArrayList<Question> allQuestion = new ArrayList<>();
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = ("SELECT DISTINCT question.id, (SELECT name FROM user WHERE user.id_user = question.id_user) as name, question.topic, question.content, question.timestamp, (SELECT SUM(vote_question.value) as votes FROM vote_question WHERE vote_question.id = question.id) as votes, (SELECT COUNT(answer.id) as count FROM answer WHERE answer.id = question.id) as count FROM question ORDER BY id DESC");
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Question qu = new Question();
                qu.id = rs.getInt("id");
                qu.name = rs.getString("name");
                qu.topic = rs.getString("topic");
                qu.content = rs.getString("content");
                qu.timestamp = rs.getString("timestamp");
                qu.vote = rs.getInt("votes");
                qu.count = rs.getInt("count");
                allQuestion.add(qu);
            }
            closeDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Question qu[] = new Question[allQuestion.size()];
        for(int i = 0; i < allQuestion.size(); i++){
            qu[i] = allQuestion.get(i);
        }
        return qu;
    }
    
    @Override
    public Answer getAnswer(String token, int id_answer) {
        int id_user = 0;
        if(!token.equals("")) id_user = whoIs(token);
        Answer an = new Answer();
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = ("SELECT *, (SELECT name FROM user WHERE user.id_user = answer.id_user) as name, (SELECT SUM(vote_answer.value) as votes FROM vote_answer WHERE vote_answer.id_answer = answer.id_answer) as votes FROM answer WHERE id_answer ='" + id_answer + "'");
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                an.id = rs.getInt("id");
                an.id_answer = rs.getInt("id_answer");
                an.name = rs.getString("name");
                an.content = rs.getString("content");
                an.timestamp = rs.getString("timestamp");
                an.vote = rs.getInt("votes");
            }
            closeDB();
            if(id_user != 0){
                connectDB();
                st = connection.createStatement();
                sql = "SELECT * FROM vote_answer WHERE id_answer = '" + an.id_answer + "'AND id_user = '" + id_user + "'";
                rs = st.executeQuery(sql);
                if(rs.next()) an.hasVote = true;
                else an.hasVote = false;
                closeDB(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return an;
    }
    @Override
    public Answer[] getAllAnswer(String token, int id) {
        int id_user = 0;
        if(!token.equals("")) id_user = whoIs(token);
        ArrayList<Answer> allAnswer = new ArrayList<>();
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = ("SELECT *, (SELECT name FROM user WHERE user.id_user = answer.id_user) as name, (SELECT SUM(vote_answer.value) as votes FROM vote_answer WHERE vote_answer.id_answer = answer.id_answer) as votes FROM answer WHERE id ='" + id + "'");
            ResultSet rs = st.executeQuery(sql);
            
            JSONObject j = new JSONObject();
            while(rs.next()){
                Answer an = new Answer();
                an.id = rs.getInt("id");
                an.id_answer = rs.getInt("id_answer");
                an.name = rs.getString("name");
                an.content = rs.getString("content");
                an.timestamp = rs.getString("timestamp");
                an.vote = rs.getInt("votes");
                allAnswer.add(an);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Answer an[] = new Answer[allAnswer.size()];
        for(int i = 0; i < allAnswer.size(); i++){
            an[i] = allAnswer.get(i);
        }
        if(id_user != 0){
            for(Answer a : an){
                try{
                    connectDB();
                    Statement st = connection.createStatement();
                    String sql = "SELECT * FROM vote_answer WHERE id_answer = '" + a.id_answer + "' AND id_user = '" + id_user + "'";
                    ResultSet rs = st.executeQuery(sql);
                    if(rs.next()) a.hasVote = true;
                    else a.hasVote = false;
                    closeDB();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return an;
    }

    /**
     * Web service operation
     * @param token
     * @param topic
     * @param content
     * @return 
     */
    @Override
    public int addQuestion(String token, String topic, String content){
        int id = 0;
        int id_user = whoIs(token);
        if(id_user != 0){
            try {
                connectDB();
                Statement st = connection.createStatement();
                String sql = "INSERT INTO question (id_user, topic, content) VALUES ('"+ id_user +"', '"+ topic +"', '"+ content +"')";
                st.execute(sql);
                closeDB();
                connectDB();
                st = connection.createStatement();
                sql = "SELECT id FROM question WHERE content LIKE '" + content + "'";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    id = rs.getInt("id");
                }
                closeDB();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }
    @Override
    public boolean addAnswer(int id, String token, String content){
        int id_user = whoIs(token);
        if(id_user != 0){
            try {
                connectDB();
                Statement st = connection.createStatement();
                String sql = "INSERT INTO answer (id, id_user, content) VALUES ('"+ id +"', '"+ id_user +"', '"+ content +"')";
                st.execute(sql);
                closeDB();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return true;
    }
    @Override
    public int editQuestion(int id, String token, String topic, String content){
        int id_user = whoIs(token);
        if(id_user != 0){
            try {
                connectDB();
                Statement st = connection.createStatement();
                String sql = "UPDATE question SET topic = '" + topic + "', content = '" + content + "' WHERE id = '" + id + "' AND id_user = '" + id_user + "'";
                st.execute(sql);
                closeDB();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }
    @Override
    public boolean deleteQuestion(int id, String token){
        int id_user = whoIs(token);
        if(id_user != 0){
            try {
                connectDB();
                Statement st = connection.createStatement();
                String sql = "DELETE FROM question WHERE id = '" + id + "' AND id_user = '" + id_user + "'";
                st.execute(sql);
                closeDB();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return true;
    }
    @Override
    public int updateVoteAnswer(String token, int id_answer, int vote){
        int id_user = whoIs(token);
        int votes = 0;
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO vote_answer (id_answer, id_user, value) VALUES ('"+ id_answer +"', '"+ id_user +"', '"+ vote +"')";
            st.execute(sql);
            closeDB();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return votes;
    }
    @Override
    public int updateVoteQuestion(String token, int id, int vote){
        int id_user = whoIs(token);
        int votes = 0;
        try {
            connectDB();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO vote_question (id, id_user, value) VALUES ('"+ id +"', '"+ id_user +"', '"+ vote +"')";
            st.execute(sql);
            closeDB();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return votes;
    }
}
