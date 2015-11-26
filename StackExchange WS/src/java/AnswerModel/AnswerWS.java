/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import Auth.Auth;
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
   /* @WebMethod(operationName = "insertAnswer")
    public int insertAnswer(@WebParam(name = "access_token") String token,
                            @WebParam(name = "qid") int qid,
                            @WebParam(name = "content") String content) throws SQLException {
        // cek token (kasih IS)
        Auth auth = new Auth();
        Answer answer = new Answer();
        int Valid = auth.check(token);
        if (Valid == 1){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO answer (q_id, u_id, a_content) VALUES (?, ?, ?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, answer.getQID());
                dbStatement.setInt(2, auth.getUserID(token));
                dbStatement.setString(3, answer.getAContent());
            } catch (SQLException e){
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return Valid;
    }
*/
    /**
     * Web service operation
     */
  /*  @WebMethod(operationName = "voteAnswer")
    public int voteAnswer(@WebParam(name = "access_token") String token, @WebParam(name = "a_id") int a_id, @WebParam(name = "value") int value) {
        Auth auth = new Auth();
        int Valid = auth.check(token);
        if (Valid == 1){
            try {
                int u_id = auth.getUserID(token);
                int count = 0;
                
                Statement stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM vote WHERE u_id = ? AND a_id = ?";
                
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, u_id);
                dbStatement.setInt(2, a_id);
                
                ResultSet rs = dbStatement.executeQuery();
                
                while (rs.next()){
                    ++count;
                }
                
                if (count == 0){
                    sql = "INSERT INTO vote (u_id, a_id, q_id, v_count) VALUES (?, ?, 0, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, u_id);
                    dbStatement.setInt(2, a_id);
                    dbStatement.setInt(3, value);
                    
                    dbStatement.executeUpdate();
                    
                } else {
                    sql = "UPDATE vote_answer SET v_count = ? WHERE u_id = ? AND a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, value);
                    dbStatement.setInt(2, u_id);
                    dbStatement.setInt(3, a_id);
                    
                    dbStatement.executeUpdate();
                    
                }
                stmt.close();
            } catch (SQLException e){
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        return Valid;
    }
*/
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAVoteByAID")
    public int getAVoteByAID(@WebParam(name = "a_id") int a_id) {
        int vote_count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT SUM(v_count) v_count FROM `vote` WHERE a_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, a_id);

            ResultSet rs = dbStatement.executeQuery();

            while(rs.next()) {
                vote_count += rs.getInt("v_count");
            }
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote_count;
    }
}
