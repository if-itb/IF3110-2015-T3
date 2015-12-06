/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Authentication.Auth;
import Config.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author User
 */
public class VoteAnswer {
    private DB db= new DB();
    private Connection conn;
    private final Auth auth= new Auth();
    
    public JSONObject getVoteById( int aid) {
        JSONObject json = new JSONObject();

        
        int count= 0,i=0;
            conn = db.connect();
            
        try {
            
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT vote FROM answer where id_answer = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
//            if (!rs.next()) {
//                json.put("state", 0);
//                return json;
//            }
            while(rs.next()) {
                i++;
                json.put("state",1);
                json.put("vote",rs.getInt("vote"));
                
            }
            if(i==0)json.put("state",0);
            rs.close();
            stmt.close();
            conn.close();    
            
        } catch (SQLException ex) {
            Logger.getLogger(VoteAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
    
    public JSONObject voteUp(int aid, String token, String userAgent, String ip ){
        int count;
        JSONObject json = getVoteById(aid);
        count= Integer.parseInt(json.get("vote").toString());
        
        String username= auth.checkToken(token, userAgent, ip);
        if(!isVoteUp(aid, username)){//sudah pernah vote up
            if(!username.equals("-999")){//token generator salah
                if(!username.equals("-998")){//user agent
                    if(!username.equals("-997")){//ip
                            try {
                                conn = db.connect();
                                Statement stmt;
                                stmt = conn.createStatement();
                                //pernah vote down
                                if(isVoteDown(aid,username)){
                                    count+=2;
                                    String sql;
                                    sql="DELETE FROM vote_answer WHERE id_answer = ?";
                                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                                    dbStatement.setInt(1, aid);
                                    dbStatement.execute();
                                    json.put("status", 2);
                                }
                                else{
                                    count+=1;
                                    json.put("status", 1);
                                }
                                String sql, sql_select;
                                sql="UPDATE answer SET vote = ? WHERE id_vote = ? ";

                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                dbStatement.setInt(1, count);
                                dbStatement.setInt(2, aid);
                                dbStatement.execute();
                                sql="INSERT INTO vote_answer (id_answer, username,value) VALUES (?,?,1)";
                                PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                                dbStatement2.setInt(1, aid);
                                dbStatement2.setString(2, username);
                                dbStatement2.executeUpdate();

                                stmt.close();
                                conn.close();
                            }
                            catch(SQLException ex) {
                               Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex); 
                            }
                    }else json.put("status", -3);
                }else json.put("status", -2);
            }else json.put("status", -1);
        }else json.put("status", 0);
        return json;
    }

    public JSONObject voteDown(int aid, String token, String userAgent, String ip ){
        int count;
        JSONObject json = getVoteById(aid);
        count= Integer.parseInt(json.get("vote").toString());
        
        String username= auth.checkToken(token, userAgent, ip);       
        if(!isVoteDown(aid, username)){//voted down
            if(!username.equals("-999")){//failed generator
                if(!username.equals("-998")){//user agent
                    if(!username.equals("-997")){//ip
                        try {
                            conn = db.connect();
                            Statement stmt;
                            stmt = conn.createStatement();
                            if(isVoteUp(aid,username)){
                                count-=2;
                                String sql;
                                sql="DELETE FROM vote_answer WHERE id_answer = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                dbStatement.setInt(1, aid);
                                dbStatement.execute();
                            }else{count-=1;}
                            String sql, sql_select;
                            sql="UPDATE answer SET vote = ? WHERE id_answer = ? ";

                            PreparedStatement dbStatement = conn.prepareStatement(sql);
                            dbStatement.setInt(1, count);
                            dbStatement.setInt(2, aid);
                            dbStatement.execute();

                            sql="INSERT INTO vote_answer (id_answer, username,value) VALUES (?,?,-1)";
                            PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                            dbStatement2.setInt(1, aid);
                            dbStatement2.setString(2, username);
                            dbStatement2.execute();

                            stmt.close();
                            conn.close();
                        }
                        catch(SQLException ex) {
                           Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex); 
                        }
                    }else json.put("status", -3);
                }else json.put("status", -2);
            }else json.put("status", -1);
        }else json.put("status", 0);
        return json;
    }
    public Boolean isVoted(int aid, String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_answer where id_answer = ? and username = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            dbStatement.setString(2, username);

            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                status= true;
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
    public Boolean isVoteUp( int aid, String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_answer where id_answer = ? and username = ? and value = 1";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            dbStatement.setString(2, username);

            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                status= true;
            }
            
            stmt.close();
            //conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
    public Boolean isVoteDown( int aid, String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_answer where id_answer = ? and username = ? and value = -1";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            dbStatement.setString(2, username);

            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                status= true;
            }
            
            stmt.close();
            //conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
}
