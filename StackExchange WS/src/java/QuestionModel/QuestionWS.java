/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import DatabaseWS.DB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Vanji
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    /* Connect to Database */
    Connection conn = DB.connect();
    
    
    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getQuestion")
    @WebResult(name="Question")
    public ArrayList<Question> getQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT q_id, u_id, q_topic, q_content, q_date FROM question";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                questions.add(new Question (rs.getInt("q_id"),
                                        rs.getInt("u_id"),
                                        rs.getString("q_topic"),
                                        rs.getString("q_content"),
                                        rs.getString("q_date")
                                        )
                            );
                ++i;
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            
        }
        
        return questions;
    }

    /**
     * Web service operation
     * @param token
     * @param content
     * @param title
     */
    @WebMethod(operationName = "insertQuestion")
    @WebResult(name = "insQuestion")
    public int insertQuestion(@WebParam(name = "access_token") String token,
                                    @WebParam(name = "title") String title,
                                    @WebParam(name = "content") String content)
    throws Exception {
        // cek token (kasih IS)
        
        int Valid;
        String url = "localhost:8082/WBD_IS/testrestservlet/?access_token=" + token;
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String USER_AGENT = "Mozilla/5.0";
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'Get' request to " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
            
        }
        in.close();
                
        System.out.println(response.toString());
        
        if (response.toString() == token){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO question (q_topic, q_content) VALUES (?, ?)";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, title);
                dbStatement.setString(2, content);

                ResultSet rs = dbStatement.executeQuery();
                rs.close();
                stmt.close();
                Valid = 1;
            } catch (SQLException e){
                Valid = 0;
            }
            
        }
        else {
            Valid = 0;
        }
        return Valid;
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    @WebResult(name = "updQuestion")
    public int updateQuestion(@WebParam(name = "access_token") String token, 
                              @WebParam(name = "topic") String topic, 
                              @WebParam(name = "content") String content) 
            throws Exception {
        // cek token (kasih IS)
        
        int Valid;
        String url = "localhost:8082/WBD_IS/testrestservlet/?access_token=" + token;
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String USER_AGENT = "Mozilla/5.0";
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'Get' request to " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
            
        }
        in.close();
                
        System.out.println(response.toString());
        
        if (response.toString() == token){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "UPDATE question SET (q_topic = '?', q_content = '?')";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, topic);
                dbStatement.setString(2, content);

                ResultSet rs = dbStatement.executeQuery();
                rs.close();
                stmt.close();
                Valid = 1;
            } catch (SQLException e){
                Valid = 0;
            }
            
        }
        else {
            Valid = 0;
        }
        return Valid;
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuesetion")
    @WebResult(name = "delQuestion")
    public int deleteQuestion(@WebParam(name = "access_token") String token, 
                              @WebParam(name = "qid") int q_id)
            throws Exception {
        // cek token (kasih IS)
        
        int Valid;
        String url = "localhost:8082/WBD_IS/testrestservlet/?access_token=" + token;
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String USER_AGENT = "Mozilla/5.0";
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'Get' request to " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
            
        }
        in.close();
                
        System.out.println(response.toString());
        
        if (response.toString() == token){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "DELETE FROM question WHERE (q_id = ?)";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, q_id);

                ResultSet rs = dbStatement.executeQuery();
                rs.close();
                stmt.close();
                Valid = 1;
            } catch (SQLException e){
                Valid = 0;
            }
            
        }
        else {
            Valid = 0;
        }
        return Valid;
       
    }
}