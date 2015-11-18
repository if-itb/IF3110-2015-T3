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
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    con.close();
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
    public void InsertQuestion(@WebParam(name = "access_token") String access_token, @WebParam(name = "topic") String topic, @WebParam(name = "content")String content){//param token qid topic content , mail ganti token
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if (Email!=null){
                String query = "INSERT INTO Question(Email,QuestionTopic,Content) VALUES (?,?,?)";
                ps = con.prepareStatement(query);
                ps.setString(1, IdentityService.getEmail(access_token));
                ps.setString(2,topic);
                ps.setString(3,content);
                ps.executeUpdate();
                ps.close();
                
            }else{
                //TODO kirimkan error bahwa login gagal
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    con.close();
            }catch(SQLException ex ){
                ex.printStackTrace();
            }
            try{
                if(con!=null)    con.close();
            }catch(SQLException ex ){
                ex.printStackTrace();
            }
        }
    }
    
    @WebMethod(operationName="UpdateQuestion")
    public void UpdateQuestion(@WebParam(name="access_token") String access_token, @WebParam(name="qid") int qid, @WebParam(name="content")String content,@WebParam(name="topic")String topic ){//id content topic token 
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        PreparedStatement checkps=null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if (Email!=null){
                checkps = con.prepareStatement("SELECT Email FROM Question WHERE qid=?");
                checkps.setInt(1, qid);
                ResultSet rs = checkps.executeQuery();
                checkps.close();
                if (rs.next()){
                    if (Email.equals(rs.getString("Email"))){
                        String query = "UPDATE Question SET QuestionTopic = ?, Content = ? where qid = ? AND Email=  ? ";
                        ps = con.prepareStatement(query);
                        ps.setString(1, topic);
                        ps.setString(2, content);
                        ps.setInt(3, qid);
                        ps.setString(4, Email);
                        ps.executeUpdate();
                        ps.close();
                    }else{
                        //TODO kirimkan error unauthorized
                    }
                }else{
                    //TODO Kirimkan error qid tidak ditemukan;
                }
            }else{
                //TODO kirimkan error bahwa login gagal
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
                if(checkps!=null) checkps.close();
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
    
    @WebMethod(operationName="DeleteQuestion")
    public void DeleteQuestion(@WebParam(name="access_token") String access_token, 
            @WebParam(name="qid")int qid){//id token
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        PreparedStatement checkps=null;
        try{
            String Email = IdentityService.getEmail(access_token);
            if (Email!=null){
                checkps = con.prepareStatement("SELECT Email FROM Question WHERE qid=?");
                checkps.setInt(1, qid);
                ResultSet rs = checkps.executeQuery();
                checkps.close();
                if (rs.next()){
                    if (Email.equals(rs.getString("Email"))){
                        String query = "DELETE FROM Question WHERE qid=? ";
                        ps = con.prepareStatement(query);
                        ps.setInt(1, qid);
                        ps.executeUpdate();
                        ps.close();
                    }else{
                        //TODO kirimkan error unauthorized
                    }
                }else{
                    //TODO Kirimkan error qid tidak ditemukan;
                }
            }else{
                //TODO kirimkan error bahwa login gagal
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
                if(checkps!=null) checkps.close();
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
}
