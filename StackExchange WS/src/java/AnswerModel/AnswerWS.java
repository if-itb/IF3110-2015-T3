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
import java.io.IOException;
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
import org.json.simple.parser.ParseException;

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
    public int insertAnswer(@WebParam(name = "access_token") String token,
                            @WebParam(name = "qid") int qid,
                            @WebParam(name = "uid") int uid,
                            @WebParam(name = "content") String content,
                            @WebParam(name = "name") String name,
                            @WebParam(name = "email") String email)
    throws Exception {
        // cek token (kasih IS)
        Answer answer = new Answer();
        Auth auth = new Auth();
        int Valid = auth.check(token);
        
        if (Valid == 1){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO answer (a_vote, q_id, u_id, u_name, a_email, a_content) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1,0);
                dbStatement.setInt(2, qid);
                dbStatement.setInt(3, uid);
                dbStatement.setString(4, name);
                dbStatement.setString(5, email);
                dbStatement.setString(6, content);

                dbStatement.executeUpdate();
                
                stmt.close();
            } catch (SQLException e){
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return Valid;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteAnswer")
    public int voteAnswer(@WebParam(name = "access_token") String token, 
                            @WebParam(name = "a_id") int a_id, 
                            @WebParam(name = "value") int value) 
            throws ParseException, IOException {
        Auth auth = new Auth();
        Answer answer = new Answer();
        int Valid = auth.check(token);
        
        if (Valid == 1){
            try {
                int u_id = auth.getUserID(token);
                int count = 0;
                int avote = 0;
                
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
                
                if (count == 0){ // User belum vote pada answer tersebut
                    // Insert vote baru
                    sql = "INSERT INTO vote (u_id, a_id, q_id, v_count) VALUES (?, ?, 0, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, u_id);
                    dbStatement.setInt(2, a_id);
                    dbStatement.setInt(3, value);
                    
                    dbStatement.executeUpdate();
                    
                    // Update a_vote pada tabel answer
		    avote = getAVoteByAID(a_id);
                    sql = "UPDATE answer SET a_vote = ? WHERE a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, avote);
                    dbStatement.setInt(2, a_id);
                    
                    dbStatement.executeUpdate();
                    
                } else { // user sudah vote
                    // update vote sesuai value (like atau dislike)
                    sql = "UPDATE vote SET v_count = ? WHERE u_id = ? AND a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, value);
                    dbStatement.setInt(2, u_id);
                    dbStatement.setInt(3, a_id);
                    
                    dbStatement.executeUpdate();
                    
                    // dapatkan a_vote pada answer sebelum user melakukan vote
                    avote = getAVoteByAID(a_id); 
                    
                    // update jumlah a_vote
                    sql = "UPDATE answer SET a_vote = ? WHERE a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, avote);
                    dbStatement.setInt(2, a_id);
                    
                    dbStatement.executeUpdate();
                    
                }
                stmt.close();
            } catch (SQLException e){
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        return Valid;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAVoteByAID")
    public int getAVoteByAID(@WebParam(name = "a_id") int a_id) {
        int vote_count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT SUM(v_count) AS vote_count FROM vote WHERE a_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, a_id);

            ResultSet rs = dbStatement.executeQuery();

            while(rs.next()) {
                vote_count += rs.getInt("vote_count");
            }
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote_count;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserID")
    public int getUserID(@WebParam(name = "token") String token) {
        int ret = -1;
        DB db = new DB();
        Connection conn = db.connect();  
          try {
              Statement stmt = conn.createStatement();
              String sql;

              sql = "SELECT * FROM token WHERE t_token = ?";
              PreparedStatement dbStatement = conn.prepareStatement(sql);
              dbStatement.setString(1, token);

              ResultSet rs = dbStatement.executeQuery();

              // Extract data from result set
              while(rs.next()){        
                ret = rs.getInt("u_id");
              }

              rs.close();
              stmt.close();
          } catch(SQLException ex) {
              Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
          }
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserName")
    public String getUserName(@WebParam(name = "token") String token) {
        String ret = null;
        int temp = -1;
        DB db = new DB();
        Connection conn = db.connect();  
          try {
              Statement stmt = conn.createStatement();
              String sql;

              sql = "SELECT * FROM token WHERE t_token = ?";
              PreparedStatement dbStatement = conn.prepareStatement(sql);
              dbStatement.setString(1, token);

              ResultSet rs = dbStatement.executeQuery();

              // Extract data from result set
              while(rs.next()){        
                temp = rs.getInt("u_id");
              }

              sql = "SELECT name FROM user WHERE u_id = ?";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, temp);

              rs = dbStatement.executeQuery();

              while(rs.next()){        
                ret = rs.getString("name");
              }


              rs.close();
              stmt.close();
          } catch(SQLException ex) {
              Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
          }
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserEmail")
    public String getUserEmail(@WebParam(name = "token") String token) {
        String ret = null;
        int temp = -1;
        DB db = new DB();
        Connection conn = db.connect();  
          try {
              Statement stmt = conn.createStatement();
              String sql;

              sql = "SELECT * FROM token WHERE t_token = ?";
              PreparedStatement dbStatement = conn.prepareStatement(sql);
              dbStatement.setString(1, token);

              ResultSet rs = dbStatement.executeQuery();

              // Extract data from result set
              while(rs.next()){        
                temp = rs.getInt("u_id");
              }

              sql = "SELECT email FROM user WHERE u_id = ?";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, temp);

              rs = dbStatement.executeQuery();

              while(rs.next()){        
                ret = rs.getString("email");
              }


              rs.close();
              stmt.close();
          } catch(SQLException ex) {
              Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
          }
        return ret;
      }
}
