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
import javax.jws.WebResult;

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
    @WebResult(name = "Status")
    public String InsertAnswer(@WebParam(name="access_token")String access_token,@WebParam(name="qid")int qid, @WebParam(name="content")String content){//aid qid content token 
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if (Email!=null){
                String query = "INSERT INTO Answer (qid,Email,Content) VALUES (?,?,?)";
                ps = con.prepareStatement(query);
                ps.setInt(1, qid);
                ps.setString(2, Email);
                ps.setString(3, content);
                ps.executeUpdate();
                ps.close();
            }else{
                Status = "invalid access_token";
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            Status = "SQLException: " + ex.getMessage();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (ps!=null) ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
            try{
                if (con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
        }
        return Status;
    }
    
    @WebMethod(operationName = "UpdateAnswer")
    @WebResult(name = "Status")
    public String UpdateAnswer(@WebParam(name="access_token") String access_token, @WebParam(name="content")String content, @WebParam(name="aid")int aid, @WebParam(name ="qid") int qid){
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;PreparedStatement checkps=null;
        try{
            //cek apakah email sama
            String checkquery = "SELECT Email FROM Answer WHERE aid = ? AND qid = ?";
            checkps = con.prepareStatement(checkquery);
            checkps.setInt(1, aid);
            checkps.setInt(2, qid);
            ResultSet selRes = checkps.executeQuery();
            String Email=IdentityService.getEmail(access_token); //TODO tangkap error kalau tidak ada
            if (Email!=null){
                if (selRes.next()){
                    if (Email.equals(selRes.getString("Email"))){
                        String query ="UPDATE Answer SET Content = ? WHERE aid = ? AND qid = ?" ;
                        ps = con.prepareStatement(query);
                        ps.setString(1, content);
                        ps.setInt(2, aid);
                        ps.setInt(3, qid);
                        ps.executeUpdate();
                        ps.close();
                    }else{
                        Status = "unauthorized";//TODO throw error unauthorized
                    }
                }else{
                    Status = "aid not found";//TODO throw error
                }
            }else{
                Status = "invalid access_token";
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            Status = "SQLException: " + ex.getMessage();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (ps!=null) checkps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
            try{
                if (checkps!=null) checkps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
            try{
                if (con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
        }
        return Status;
    }
    
    @WebMethod(operationName = "DeleteAnswer")
    @WebResult(name = "Status")
    public String DeleteAnswer(@WebParam(name="access_token")String access_token, @WebParam(name="aid")int aid,@WebParam(name="qid") int qid){
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        
        PreparedStatement ps = null;
        PreparedStatement checkps = null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if (Email!=null){
                checkps = con.prepareStatement("SELECT Email FROM Answer WHERE aid = ? AND qid = ?");
                checkps.setInt(1, aid);
                checkps.setInt(2, qid);
                ResultSet rs = checkps.executeQuery();
                if (rs.next()){
                    if (Email.equals(rs.getString("Email"))){

                        String query = "DELETE FROM Answer WHERE aid = ? AND qid =?";
                        ps = con.prepareStatement(query);
                        ps.setInt(1, aid);
                        ps.setInt(2, qid);
                        ps.executeQuery();
                    }else{
                        Status = "unauthorized";
                    }
                }else{
                    Status = "aid not found";
                }
            }else{
                Status = "invalid access_token";
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            Status = "SQLException: " + ex.getMessage();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if (ps!=null) ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
            try{
                if (checkps!=null) checkps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
            try{
                if (con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
        }
        return Status;
    }
    
    @WebMethod(operationName="voteAnswer")
    @WebResult(name = "Status")
    public String voteAnswer(@WebParam(name="qid")int qid,@WebParam(name="aid")int aid, @WebParam(name="up")boolean up, @WebParam(name="access_token")String access_token){
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps = null;
        PreparedStatement checkvote = null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if(Email!=null){
                checkvote = con.prepareStatement("SELECT up FROM HasVotedAnswer WHERE qid = ? AND aid=?");
                checkvote.setInt(1,qid);
                checkvote.setInt(2,aid);
                checkvote.setString(3,Email);
                ResultSet rs = checkvote.executeQuery();
                if(rs.next()){ //TODO fix logic error here (and similarly check whether same error happens in QuestionWS
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
            }else{
                Status = "invalid access_token";
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            Status = "SQLException: " + ex.getMessage();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
            try{
                if(con!=null)    con.close();     
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = "SQLException: " + ex.getMessage();
            }
        }
        return Status;
    }

    
}
