/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import DatabaseWS.DB;
import QuestionModel.QuestionWS;
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
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

    /* Connect to Database */
    Connection conn = DB.connect();
    
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name="Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM answer WHERE q_id = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                answers.add(new Answer (rs.getInt("a_id"),
                                        rs.getInt("q_id"),
                                        rs.getInt("u_id"),
                                        rs.getInt("a_vote"),
                                        rs.getString("a_content"),
                                        rs.getString("a_date"),
                                        rs.getString("u_name")
                                        )
                            );
                ++i;
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            Logger.getLogger(QuestionWS.class.getName()).log
                    (Level.SEVERE, null, e);
        }
        
        return answers;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertAnswer")
    @WebResult(name = "insAnswer")
    public int insertAnswer(@WebParam(name = "access_token") String token,
                            @WebParam(name = "qid") int qid,
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
                sql = "INSERT INTO answer (q_id, a_content) VALUES (?, ?)";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, qid);
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
}
