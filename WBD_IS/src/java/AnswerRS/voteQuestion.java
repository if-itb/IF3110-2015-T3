/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerRS;

import Auth.Auth;
import DatabaseWS.DB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Vanji
 */
public class voteQuestion {
    
    Connection conn = DB.connect();
    
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
            
        }
        return vote_count;
    }
    
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
                
            }
        }
        
        return Valid;
    }
}
