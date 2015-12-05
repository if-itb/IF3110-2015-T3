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
public class voteAnswer {
    Connection conn = DB.connect();
    
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
                
            }
        }
        
        return Valid;
    }
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
           
        }
        return vote_count;
    }

}
