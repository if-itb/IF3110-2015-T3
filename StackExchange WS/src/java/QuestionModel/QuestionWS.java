/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import AnswerModel.Answer;
import Auth.Auth;
import DatabaseWS.DB;
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
            String sql = "SELECT q_id, u_id, q_vote, u_name, q_topic, q_content, q_date FROM question";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                questions.add(new Question (rs.getInt("q_id"),
                                        rs.getInt("u_id"),
                                        rs.getInt("q_vote"),
                                        rs.getString("u_name"),
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
                                    @WebParam(name = "topic") String topic,
                                    @WebParam(name = "content") String content,
                                    @WebParam(name = "id") int id,
                                    @WebParam(name = "name") String name,
                                    @WebParam(name = "email") String email)
    throws Exception {
        // cek token (kasih IS)
        Question question = new Question();
        Auth auth = new Auth();
        int Valid = auth.check(token);
        if (Valid == 1){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO question (u_id, u_name, q_email, q_vote, q_topic, q_content) VALUES (?, ?, ?, 0, ?, ?)";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, id);
                dbStatement.setString(2, name);
                dbStatement.setString(3, email);
                dbStatement.setString(4, topic);
                dbStatement.setString(5, content);

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
    @WebMethod(operationName = "updateQuestion")
    @WebResult(name = "updQuestion")
    public int updateQuestion(@WebParam(name = "access_token") String token, 
                              @WebParam(name = "qid") int qid,
                              @WebParam(name = "uid") int uid,
                              @WebParam(name = "topic") String topic, 
                              @WebParam(name = "content") String content) 
            throws Exception {
        // cek token (kasih IS)
        
        Auth auth = new Auth();
        int Valid = auth.check(token);
        if (Valid == 1){
            try {
                Statement stmt = conn.createStatement();
                String sql;
                sql = "UPDATE question SET q_topic = ?, q_content = ? WHERE q_id = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, topic);
                dbStatement.setString(2, content);
                dbStatement.setInt(3, qid);
                
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
    @WebMethod(operationName = "deleteQuestion")
    @WebResult(name = "delQuestion")
    public int deleteQuestion(@WebParam(name = "access_token") String token, 
                              @WebParam(name = "qid") int q_id)
            throws Exception {
        // cek token (kasih IS)
        
        Auth auth = new Auth();
        int ret = auth.check(token);
      
        if ( ret == 1 ) {
            try {
                Statement stmt = conn.createStatement();
                String sql;

                sql = "DELETE FROM question WHERE q_id = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, q_id);

                dbStatement.executeUpdate();

                stmt.close();
            } catch(SQLException ex) {
                Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        return ret;
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswerCount")
    public int getAnswerCount(@WebParam(name = "q_id") int q_id) {
        int count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "SELECT * FROM answer WHERE q_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, q_id);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            while(rs.next()){        
              ++count;
            }
      
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQVoteByQID")
    public int getQVoteByQID(@WebParam(name = "q_id") int q_id) {
        int vote_count = 0;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT SUM(v_count) AS vote_count FROM vote WHERE q_id = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, q_id);

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
     * @param qid
     * @return 
     */
    @WebMethod(operationName = "getQuestionByQID")
    @WebResult(name = "Question")
    public Question getQuestionByQID(@WebParam(name = "qid") int qid) {
        Question res = null;

        try {
            Statement stmt = conn.createStatement();
            String sql;
            
            sql = "SELECT * FROM question WHERE q_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            // Extract data from result set
            if(rs.next()){
                res = new Question(rs.getInt("q_id"),
                                            rs.getInt("u_id"),
                                            rs.getInt("q_vote"),
                                            rs.getString("u_name"),
                                            rs.getString("q_topic"),
                                            rs.getString("q_content"),
                                            rs.getString("q_date")
                                            );
            }else{
                res = new Question();
            }
            rs.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

     /**
     * Web service operation
     */
    @WebMethod(operationName = "voteQuestion")
    public int voteQuestion(@WebParam(name = "access_token") String token, 
                            @WebParam(name = "q_id") int q_id, 
                            @WebParam(name = "value") int value) 
            throws ParseException, IOException {
        Auth auth = new Auth();
        Question question = new Question();
        int Valid = auth.check(token);
        
        if (Valid == 1){
            try {
                int u_id = auth.getUserID(token);
                int count = 0;
                int qvote = 0;
                
                Statement stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM vote WHERE u_id = ? AND q_id = ?";
                
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, u_id);
                dbStatement.setInt(2, q_id);
                
                ResultSet rs = dbStatement.executeQuery();
                
                while (rs.next()){
                    ++count;
                }
                
                if (count == 0){ // User belum vote pada question tersebut
                    // Insert vote baru
                    sql = "INSERT INTO vote (u_id, a_id, q_id, v_count) VALUES (?, 0, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, u_id);
                    dbStatement.setInt(2, q_id);
                    dbStatement.setInt(3, value);
                    
                    dbStatement.executeUpdate();
                    
                    // Update q_vote pada tabel question
                    qvote = getQVoteByQID(q_id);
                    sql = "UPDATE question SET q_vote = ? WHERE q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
		    dbStatement.setInt(1, qvote);
                    dbStatement.setInt(2, q_id);
                    
                    dbStatement.executeUpdate();
                    
                } else { // user sudah vote
                    // update vote sesuai value (like atau dislike)
                    sql = "UPDATE vote SET v_count = ? WHERE u_id = ? AND q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, value);
                    dbStatement.setInt(2, u_id);
                    dbStatement.setInt(3, q_id);
                    
                    dbStatement.executeUpdate();
                    
                    // dapatkan q_vote pada answer sebelum user melakukan vote
		    qvote = getQVoteByQID(q_id);
                    
                    // update jumlah q_vote
                    sql = "UPDATE question SET q_vote = ? WHERE q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qvote);
                    dbStatement.setInt(2, q_id);
                    
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