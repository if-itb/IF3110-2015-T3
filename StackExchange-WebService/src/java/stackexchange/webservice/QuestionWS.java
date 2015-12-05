/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import stackexchange.webservice.auth.Auth;
import stackexchange.webservice.model.Question;
import stackexchange.webservice.model.User;
import stackexchange.webservice.util.Database;
import stackexchange.webservice.util.Vote;

/**
 *
 * @author fauzanrifqy
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestions")
    @WebResult(name="Question")
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<Question>();
        Database db = new Database();
        try{
            String sql="select * from questions";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question(rs.getInt("id"), rs.getInt("userid"), rs.getString("name"), rs.getString("email"), rs.getString("topic"), rs.getString("content"), rs.getTimestamp("dateMade"), rs.getInt("vote"), rs.getInt("answer"));
                questions.add(question);
            }
            return questions;
        }catch(Exception e){
            Question question = new Question();
            questions.add(question);
            return questions;
        }finally{
            db.closeConnection();
            db = null;
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestions_2")
    @RequestWrapper(className = "stackexchange.getQuestions_2")
    @ResponseWrapper(className = "stackexchange.getQuestions_2Response")
    @WebResult(name="Question")
    public List<Question> getQuestions(String query) {
        List<Question> questions = new ArrayList<Question>();
        Database db = new Database();
        try{
            String sql="select * from questions where topic like '%"+query+"%' or content like '%"+query+"%' ";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question(rs.getInt("id"), rs.getInt("userid"), rs.getString("name"), rs.getString("email"), rs.getString("topic"), rs.getString("content"), rs.getTimestamp("dateMade"), rs.getInt("vote"), rs.getInt("answer"));
                questions.add(question);
            }
            return questions;
        }catch(Exception e){
            Question question = new Question();
            questions.add(question);
            return questions;
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestions_1")
    @RequestWrapper(className = "stackexchange.getQuestions_1")
    @ResponseWrapper(className = "stackexchange.getQuestions_1Response")
    @WebResult(name="Question")
    public List<Question> getQuestions(@WebParam(name="id") int id) {
        List<Question> questions = new ArrayList<Question>();
        Database db = new Database();
        try{
            String sql="select * from questions where id=" + id;
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question(rs.getInt("id"), rs.getInt("userid"), rs.getString("name"), rs.getString("email"), rs.getString("topic"), rs.getString("content"), rs.getTimestamp("dateMade"), rs.getInt("vote"), rs.getInt("answer"));
                questions.add(question);
            }
            return questions;
        }catch(Exception e){
            Question question = new Question();
            questions.add(question);
            return questions;
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addQuestion")
    @Oneway
    public void addQuestion(@WebParam(name = "question") Question question, @WebParam(name = "token")String token) {
        Auth auth = new Auth();
    	int ret = auth.check(question.getEmail(),token);
        UserWS usrws = new UserWS();
        User user = usrws.getUserByEmail(question.getEmail());
        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
        Question newQuestion = new Question(user.getId(), user.getName(), user.getEmail(), question.getTopic(), question.getContent(),currentTime, 0,0 );
    	
        Database db = new Database();
        if(ret == 1 || ret == 0){
            try{
                String values="(";
                values+=newQuestion.getUserid() + ",";
                values+= "'"+ newQuestion.getName() +"',";
                values+= "'"+ newQuestion.getEmail() +"',";
                values+= "'"+ newQuestion.getTopic() +"',";
                values+= "'"+ newQuestion.getContent() +"',";
                values+= "'"+ newQuestion.getDateMade() +"',";
                values+= newQuestion.getVote() +",";
                values+= newQuestion.getAnswer() +")";
                String sql="insert into questions (userid, name, email, topic, content, dateMade, vote, answer) values " + values;
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ps.executeUpdate();
            }catch(Exception e){
                
            }finally{
                db.closeConnection();
                db = null;
            }
    	}	
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    @Oneway
    public void deleteQuestion(@WebParam(name = "id") int id, @WebParam(name = "email") String email, @WebParam(name = "token") String token) {
    	Auth auth = new Auth();
    	int ret = auth.check(email,token);
        UserWS usrws = new UserWS();
        User user = usrws.getUserByEmail(email);
        Database db = new Database();
        try{
            String query = "select * from questions where id=" + id;
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if((ret == 1 || ret == 0)&&user.getEmail().equals(rs.getString("email"))){
                    query="delete from answers where questionId="+id;
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                    
                    query="delete from questions where id="+ id;
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                }
            }
        
        }catch(Exception e){
            
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     * @param question
     * @param token
     * @param inc
     */
    @WebMethod(operationName = "voteQuestion")
    @Oneway
    public void voteQuestion(@WebParam(name = "question") Question question, @WebParam(name = "token")String token, @WebParam(name = "inc") boolean inc) {
        Database db = new Database();
        Auth auth = new Auth();
        int ret = auth.check(question.getEmail(), token);
        if(ret == 1|| ret == 0){
            Vote vote = new Vote();
            if(vote.voting(question, inc)){
                try{
                    String val;
                    if (inc) {
                        val = "+";
                    }else{
                        val = "-";
                    }
                    int id = question.getId();
                    String values="";
                    values+= "vote = vote "+ val + " 1";
                    String sql="update questions set " + values + " where id=" + id;
                    PreparedStatement ps = db.getConnection().prepareStatement(sql);
                    ps.executeUpdate();
                }catch(Exception e){

                }finally{
                    db.closeConnection();
                    db = null;
                }
            }
        }
    }

    /**
     * Web service operation
     * @param id
     * @param inc
     */
    @WebMethod(operationName = "voteQuestion_1")
    @Oneway
    @RequestWrapper(className = "stackexchange.voteQuestion_1")
    public void voteQuestion(@WebParam(name = "id") int id, @WebParam(name = "inc") boolean inc) {
        Database db = new Database();
        try{
            int val=0;
            if (inc) {
                val++;
            }else{
                val--;
            }
            String values="";
            values+= "vote=val+("+ val + ")";
            String sql="update question set " + values + " where id=" + id;
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    public void updateQuestion(@WebParam(name = "question") Question question, @WebParam(name = "token") String token){
        Auth auth = new Auth();
    	int ret = auth.check(question.getEmail(),token);
        UserWS usrws = new UserWS();
        User user = usrws.getUserByEmail(question.getEmail());
        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
        Question newQuestion = new Question(user.getId(), user.getName(), user.getEmail(), question.getTopic(), question.getContent(),currentTime, 0,0 );
    	Database db = new Database();
        try{
            String query = "select * from questions where id=" + question.getId();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if((ret == 1 || ret == 0)&&user.getEmail().equals(rs.getString("email"))){
                    String values="";
                    values+= "userid="+newQuestion.getUserid() + ",";
                    values+= "name='"+ newQuestion.getName() +"',";
                    values+= "email='"+ newQuestion.getEmail() +"',";
                    values+= "topic='"+ newQuestion.getTopic() +"',";
                    values+= "content='"+ newQuestion.getContent() +"',";
                    values+= "dateMade='"+ newQuestion.getDateMade() +"'";
                    query="update questions set " + values + " where id=" + question.getId();
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                }
            }
        
        }catch(Exception e){
            
        }finally{
            db.closeConnection();
            db = null;
        }
    }
}
