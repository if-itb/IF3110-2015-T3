/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModule;

import DatabaseModule.Database;

import AnswerModule.Answer;

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
    public ArrayList<Answer> GetAllAnswer(){//tambah author name 
        Database DB = new Database();
        Connection con = DB.connect();
        
        ArrayList<Answer> Answers = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            String query = "SELECT * FROM ANSWERS NATURALJOIN UACCOUNT";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Answers.add(new Answer(rs.getString("AuthorName"),rs.getInt("aid"),rs.getInt("qid"),rs.getString("Email"),rs.getString("Content"),rs.getInt("vote"),rs.getString("created at")));
            }
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(stmt!=null) con.close();
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
            String query = "INSERT INTO ANSWERS(qid,Email,Content) VALUES (?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, qid);
            ps.setEmail(2, IdentityService.getEmail(access_token));
            ps.setString(3, content);
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
    
    @WebMethod(operationName = "UpdateAnswer")
    public void UpdateAnswer(@WebParam(name="content")String content, @WebParam(name="aid")int aid){
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String query ="UPDATE ANSWERS SET Content = ? WHERE aid = ?" ;
            ps = con.prepareStatement(query);
            ps.setString(1, content);
            ps.setInt(2, aid);
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
    
}
