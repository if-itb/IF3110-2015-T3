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
            String query = "SELECT * FROM QUESTIONS";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                Questions.add(new Question(rs.getInt("qid"), rs.getString("Email"),rs.getString("QuestionTopic"), rs.getString("Content"),rs.getInt("vote"), rs.getString("created_at") ));
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
            String query = "SELECT * FROM QUESTIONS WHERE ?";
            ps = con.prepareStatement(query);
            ps.setInt(1,qid);
            rs = ps.executeQuery();
            q = new Question(rs.getInt("qid"), rs.getString("Email"),rs.getString("QuestionTopic"), rs.getString("Content"),rs.getInt("vote"), rs.getString("TIMESTAMP") );
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
    public void InsertQuestion(@WebParam(name = "Q") Question Q){//param token qid topic content , mail ganti token
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        try{
            String query = "INSERT INTO QUESTIONS VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, Q.getQid());
            ps.setString(2, Q.getUemail());
            ps.setString(3,Q.getQtopic());
            ps.setString(4,Q.getQcontent());
            ps.executeUpdate();
            ps.close();
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
    public void UpdateQuestion(@WebParam(name="Q") Question Q){//id content topic token 
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        try{
            String query = "UPDATE QUESTIONS SET QuestionTopic = ?, Content = ? where qid = ?";
            ps = con.prepareStatement(query);
            ps.setString(1,Q.getQtopic());
            ps.setString(2, Q.getQcontent());
            ps.setInt(3,Q.getQid());
            ps.executeQuery();
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(ps!=null)    con.close();     
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @WebMethod(operationName="DeleteQuestion")
    public void DeleteQuestion(@WebParam(name="Q")Question Q){//id token
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        try{
            String query = "DELETE FROM QUESTION WHERE qid = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, Q.getQid());
            ps.executeQuery();
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            try{
                if(con!=null) con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
}
