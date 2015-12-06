/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModule;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

import java.sql.*;

import java.util.ArrayList;

import DatabaseModule.Database;
import IdentityServiceModule.IdentityService;
import QuestionModule.Question;



/**
 *
 * @author LUCKY
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {
    @WebMethod(operationName = "GetAllQuestion")
    @WebResult(name="Questions")
    public ArrayList<Question> GetAllQuestion() {
        Database DB = new Database();
        Connection con = DB.connect();

        ArrayList<Question> Questions = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            String query = "SELECT * FROM Question NATURAL JOIN UAccount";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                Questions.add(new Question(rs.getInt("qid"), rs.getString("Email"),rs.getString("AuthorName"),rs.getString("QuestionTopic"), rs.getString("Content"),rs.getInt("vote"), rs.getString("created_at") ));
            }
            rs.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
        } catch (Exception ex){
           ex.printStackTrace();
        } finally{
            try{
                if(stmt!=null)    con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(con!=null)    con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
         return Questions;
    }

    @WebMethod(operationName = "GetQuestionByID")
    @WebResult(name="Question")
    public Question GetQuestionByID(@WebParam(name="qid")int qid){
        Database DB = new Database();
        Connection con = DB.connect();

        Question q = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String query = "SELECT * FROM Question NATURAL JOIN UAccount WHERE qid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,qid);
            rs = ps.executeQuery();
            if (rs.next())
                q = new Question(rs.getInt("qid"), rs.getString("Email"),rs.getString("AuthorName"),rs.getString("QuestionTopic"), rs.getString("Content"),rs.getInt("vote"), rs.getString("created_at") );
            else{
                //TODO throw error
                //sementara tidak perlu dulu
                //return list kosong saja
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch(Exception ex){
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
        return q;
    }
    
    @WebMethod(operationName = "InsertQuestion")
    @WebResult(name = "Status")
    public int InsertQuestion(@WebParam(name="access_token")String access_token,@WebParam(name="user_agent")String user_agent, @WebParam(name="user_ip")String user_ip,
            @WebParam(name = "topic") String topic, @WebParam(name = "content")String content){//param token qid topic content , mail ganti token
        Database DB = new Database();
        int qid = 0;
        Connection con = DB.connect();
        PreparedStatement ps=null;
        try{
            String Email = IdentityService.getEmail(access_token,user_agent,user_ip);
            if (Email!=null){
                String query = "INSERT INTO Question(Email,QuestionTopic,Content) VALUES (?,?,?)";
                ps = con.prepareStatement(query);
                ps.setString(1, Email);
                ps.setString(2,topic);
                ps.setString(3,content);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) qid = rs.getInt(1);
                
            }else{
                qid = -1;//TODO kirimkan error bahwa login gagal
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    ps.close();
            }catch(SQLException ex ){
                ex.printStackTrace();
            }
            try{
                if(con!=null)    con.close();
            }catch(SQLException ex ){
                ex.printStackTrace();
            }
        }
        return qid;
    }
    
    @WebMethod(operationName="UpdateQuestion")
    @WebResult(name = "Status")
    public String UpdateQuestion(@WebParam(name="access_token")String access_token,@WebParam(name="user_agent")String user_agent, @WebParam(name="user_ip")String user_ip,
            @WebParam(name="qid") int qid, @WebParam(name="content")String content,@WebParam(name="topic")String topic ){//id content topic token 
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        PreparedStatement checkps=null;
        try{
            String Email = IdentityService.getEmail(access_token,user_agent,user_ip);
            if (Email!=null){
                checkps = con.prepareStatement("SELECT Email FROM Question WHERE qid=?");
                checkps.setInt(1, qid);
                ResultSet rs = checkps.executeQuery();
                if (rs.next()){
                    if (Email.equals(rs.getString("Email"))){
                        String query = "UPDATE Question SET QuestionTopic = ?, Content = ? where qid = ? ";
                        ps = con.prepareStatement(query);
                        ps.setString(1, topic);
                        ps.setString(2, content);
                        ps.setInt(3, qid);
                        ps.executeUpdate();
                    }else{
                        Status = "unauthorized";//TODO kirimkan error unauthorized
                    }
                }else{
                    Status = "qid not found";
                    //TODO Kirimkan error qid tidak ditemukan;
                }
            }else{
                Status = "invalid access_token";
                //TODO kirimkan error bahwa login gagal
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
                if(checkps!=null) checkps.close();
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
    
    @WebMethod(operationName="DeleteQuestion")
    @WebResult(name = "Status")
    public String DeleteQuestion(@WebParam(name="access_token")String access_token,@WebParam(name="user_agent")String user_agent, @WebParam(name="user_ip")String user_ip,
            @WebParam(name="qid")int qid){//id token
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        PreparedStatement checkps=null;
        try{
            String Email = IdentityService.getEmail(access_token,user_agent,user_ip);
            if (Email!=null){
                checkps = con.prepareStatement("SELECT Email FROM Question WHERE qid=?");
                checkps.setInt(1, qid);
                ResultSet rs = checkps.executeQuery();
                if (rs.next()){
                    if (Email.equals(rs.getString("Email"))){
                        String query = "DELETE FROM Question WHERE qid=? ";
                        ps = con.prepareStatement(query);
                        ps.setInt(1, qid);
                        ps.executeUpdate();
                    }else{
                        Status = "unauthorized";//TODO kirimkan error unauthorized
                    }
                }else{
                    Status = "qid not found";//TODO Kirimkan error qid tidak ditemukan;
                }
            }else{
                Status = "invalid access_token";//TODO kirimkan error bahwa login gagal
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
                if(checkps!=null) checkps.close();
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
    
    @WebMethod(operationName="voteQuestion")
    @WebResult(name = "Status")
    public String voteQuestion(@WebParam(name="qid")int qid, @WebParam(name="up")boolean up, @WebParam(name="access_token")String access_token,@WebParam(name="user_agent")String user_agent, @WebParam(name="user_ip")String user_ip){
        String Status = "Success";
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        PreparedStatement checkvote=null;
        PreparedStatement checkps=null;
        int plus  = 0;
        
            System.out.println("check");
            System.out.println(user_agent);
            System.out.println(user_ip);
        try{
            String Email = IdentityService.getEmail(access_token,user_agent,user_ip);
            if (Email!=null){
                checkvote = con.prepareStatement("SELECT up from HasVotedQuestion WHERE qid = ? AND Email = ?");
                checkvote.setInt(1,qid);
                checkvote.setString(2,Email);
                ResultSet rs2 = checkvote.executeQuery();
                if(!rs2.next()){
                        if(up==false) plus = -1;
                        else plus=1;
                        String query = "UPDATE Question SET vote = (vote+?) where qid = ?";
                        ps = con.prepareStatement(query);
                        ps.setInt(1, plus);
                        ps.setInt(2, qid);
                        ps.executeUpdate();
                        query = "INSERT INTO HasVotedQuestion (Email, qid, up) VALUES ( ? , ? , ? )";
                        ps = con.prepareStatement(query);
                        ps.setString(1, Email);
                        ps.setInt(2, qid);
                        ps.setBoolean(3, up);
                        ps.executeUpdate();
                }else{
                    if(rs2.getBoolean("up")){
                        Status = "already upvoted";
                    }else{
                        Status = "already downvoted";
                    }
                }
                rs2.close();                    
            }else{
                Status = "invalid access_token";//TODO Kirimkan error invalid access token
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
                if(checkps!=null) checkps.close();
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
