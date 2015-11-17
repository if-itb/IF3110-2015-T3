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
    public ArrayList<Answer> GetAllAnswer(){
        Database DB = new Database();
        Connection con = DB.connect();
        
        ArrayList<Answer> Answers = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            String query = "SELECT * FROM ANSWERS";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Answers.add(new Answer(rs.getInt("aid"),rs.getInt("qid"),rs.getString("Email"),rs.getString("Content"),rs.getInt("vote"),rs.getString("created at")));
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
    public void InsertAnswer(@WebParam(name="A")Answer A){
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String query = "INSERT INTO ANSWERS VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, A.getAid());
            ps.setInt(2, A.getQid());
            ps.setString(3, A.getUemail());
            ps.setString(4, A.getAcontent());
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
    public void UpdateAnswer(@WebParam(name="A")Answer A){
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String query ="UPDATE ANSWERS SET Content = ? WHERE aid = ?" ;
            ps = con.prepareStatement(query);
            ps.setString(1, A.getAcontent());
            ps.setInt(2, A.getAid());
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
    public void DeleteAnswer(@WebParam(name="A")Answer A){
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String query = "DELETE FROM ANSWERS WHERE aid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, A.getAid());
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
