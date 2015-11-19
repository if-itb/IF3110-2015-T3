/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModule;

import DatabaseModule.Database;

import AnswerModule.Answer;
import IdentityServiceModule.IdentityService;

import java.sql.*;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author LUCKY
 */
@WebService(serviceName = "AnswerWS")
@Stateless()
public class AnswerWS {

    @WebMethod(operationName = "GetAllAnswer")
    public ArrayList<Answer> GetAllAnswer(@WebParam (name="qid")int qid){//tambah author name 
        Database DB = new Database();
        Connection con = DB.connect();
        
        ArrayList<Answer> Answers = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            String query = "SELECT * FROM Answer NATURALJOIN UAccount WHERE qid = ?";
            st = con.prepareStatement(query);
            st.setInt(1, qid);
            rs = st.executeQuery();
            while (rs.next()){
                Answers.add(new Answer(rs.getInt("aid"),rs.getInt("qid"),rs.getString("Email"),rs.getString("AuthorName"),rs.getString("Content"),rs.getInt("vote"),rs.getString("created at")));
            }
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(st!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return Answers;
    }
    
    @WebMethod(operationName = "InsertAnswer")
    public void InsertAnswer(@WebParam(name="access_token")String access_token,@WebParam(name="qid")int qid, @WebParam(name="content")String content){//aid qid content token 
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String query = "INSERT INTO Answer (qid,Email,Content) VALUES (?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, qid);
            ps.setString(2, IdentityService.getEmail(access_token));
            ps.setString(3, content);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (ps!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if (con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @WebMethod(operationName = "UpdateAnswer")
    public void UpdateAnswer(@WebParam(name="access_token") String access_token, @WebParam(name="content")String content, @WebParam(name="aid")int aid){
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;PreparedStatement checkps=null;
        try{
            //cek apakah email sama
            String checkquery = "SELECT Email FROM Answer WHERE aid = ?";
            checkps = con.prepareStatement(checkquery);
            checkps.setInt(1, aid);
            ResultSet selRes = checkps.executeQuery();
            String Email=IdentityService.getEmail(access_token); //TODO tangkap error kalau tidak ada
            if (selRes.next()){
                if (Email.equals(selRes.getString("Email"))){
                    String query ="UPDATE Answer SET Content = ? WHERE aid = ?" ;
                    ps = con.prepareStatement(query);
                    ps.setString(1, content);
                    ps.setInt(2, aid);
                    ps.executeUpdate();
                    ps.close();
                }else{
                    //TODO throw error unauthorized
                }
            }else{
                //TODO throw error
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (ps!=null) checkps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if (checkps!=null) checkps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if (con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @WebMethod(operationName = "DeleteAnswer")
    public void DeleteAnswer(@WebParam(name="aid")int aid){
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String query = "DELETE FROM ANSWERS WHERE aid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, aid);
            ps.executeQuery();
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (ps!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if (con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @WebMethod(operationName="voteAnswer")
    public void voteAnswer(@WebParam(name="qid")int qid,@WebParam(name="aid")int aid, @WebParam(name="up")boolean up, @WebParam(name="access_token")String access_token){
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps = null;
        PreparedStatement checkvote = null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if(Email!=null){
                checkvote = con.prepareStatement("SELECT up FROM HasVotedAnswer WHERE qid = ? AND aid=? AND Email = ?");
                checkvote.setInt(1,qid);
                checkvote.setInt(2,aid);
                checkvote.setString(3,Email);
                ResultSet rs = checkvote.executeQuery();
                if(rs.next()){
                    if(rs.getBoolean("up")==false){
                        int plus = 0;
                        if(up==false) plus = -1; 
                        else plus = 1;
                        String query = "UPDATE Answer SET vote = (vote + ?) where qid = ? AND aid = ?";
                        ps = con.prepareStatement(query);
                        ps.setInt(1,plus);
                        ps.executeUpdate();
                        query = "UPDATE HasVotedAnswer SET up = 1 where qid = ? and aid = ?";
                        ps = con.prepareStatement(query);
                        ps.setInt(1,qid);
                        ps.setInt(2,aid);
                        ps.executeUpdate();
                        ps.close();
                    }
                }
                else{
                    //
                }
                rs.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(con!=null)    con.close();     
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    
}
