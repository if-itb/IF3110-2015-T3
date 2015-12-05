/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import java.util.ArrayList;
import java.sql.Connection;
import DatabaseAdapter.database;
import java.sql.*;
import java.util.logging.*;
import IdentityServiceAdapter.IdentityValidator;

/**
 *
 * @author user
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    
    database DB = new database();
    
    Connection conn = DB.connect();
    
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name="Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        
        ArrayList<Answer> answers = new ArrayList<>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM answer where Q_ID = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()) {
                int aid = rs.getInt("A_ID");
                String content = rs.getString("Content");
                int uid = rs.getInt("User_ID");
                int vote = rs.getInt("Vote");
                String time = rs.getString("Time");
                
                String retrieveName;
                retrieveName = "SELECT * FROM user where User_ID = ?";
                
                PreparedStatement ps  = conn.prepareStatement(retrieveName);
                ps.setInt(1, uid);
            
                ResultSet rset = ps.executeQuery();
                rset.next();
                
                String uname = rset.getString("Name");         
                
                answers.add(new Answer(aid, qid, uname, content, vote, time));
                ++i;
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return answers;
    }

    @WebMethod(operationName = "createAnswer")    
    @WebResult(name="AnswerID")
    public int createAnswer(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid, @WebParam(name = "content") String content) {
        int aid = 0;
        // Call Identity Service
        int uid = IdentityValidator.getUID(token);     
        
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO answer(Q_ID,User_ID,Content,Vote) VALUES (?,?,?,0)";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            dbStatement.setInt(1, qid);
            dbStatement.setInt(2, uid);
            dbStatement.setString(3, content);
            dbStatement.executeUpdate();
            ResultSet rs = dbStatement.getGeneratedKeys();
            while (rs.next()) {
		aid = rs.getInt(1);
            }            
            
            String updateAnsCount;
            updateAnsCount = "UPDATE question SET Answer_Count = Answer_Count + 1 WHERE Q_ID = ?";
            dbStatement = conn.prepareStatement(updateAnsCount);
            dbStatement.setInt(1, qid);            
            dbStatement.executeUpdate();
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aid;
    }
    
    @WebMethod(operationName = "voteAnswer")    
    @WebResult(name="AnswerID")
    public int voteAnswer(@WebParam(name = "aid") int aid, @WebParam(name = "vote") int vote) {            
        try {
            Statement stmt = conn.createStatement();
                                    
            String sql;
            sql = "UPDATE answer SET Vote = Vote + ? WHERE A_ID = ?";
                    
            PreparedStatement dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            dbStatement.setInt(1, vote);
            dbStatement.setInt(2, aid);
                    
            dbStatement.executeUpdate();
            ResultSet rs = dbStatement.getGeneratedKeys();
            while (rs.next()) {
                aid = rs.getInt(1);
            }    
                    
            rs.close();
            stmt.close();
                    
        } catch (SQLException ex) {
            Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aid;
    }
}
