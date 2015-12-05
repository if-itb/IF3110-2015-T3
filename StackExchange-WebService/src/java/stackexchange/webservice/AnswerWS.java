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
import stackexchange.webservice.model.Answer;
import stackexchange.webservice.model.Question;
import stackexchange.webservice.model.User;
import stackexchange.webservice.util.Database;
import stackexchange.webservice.util.Vote;

/**
 *
 * @author fauzanrifqy
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

     /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswers")
    @WebResult(name="Answer")
    public List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<Answer>();
        Database db = new Database();
        try{
            String sql="select * from answers";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer(rs.getInt("id"), rs.getInt("userid"), rs.getInt("questionId"), rs.getString("name"), rs.getString("email"), rs.getString("content"), rs.getTimestamp("dateMade"), rs.getInt("vote"));
                answers.add(answer);
            }
            return answers;
        }catch(Exception e){
            Answer answer = new Answer();
            answers.add(answer);
            return answers;
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswers_1")
    @RequestWrapper(className = "stackexchange.getAnswers_1")
    @ResponseWrapper(className = "stackexchange.getAnswers_1Response")
    @WebResult(name="Answer")
    public List<Answer> getAnswers(@WebParam(name="questionId") int questionId) {
        List<Answer> answers = new ArrayList<Answer>();
        Database db = new Database();
        try{
            String sql="select * from answers where questionId=" + questionId+ " order by vote desc";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer(rs.getInt("id"), rs.getInt("userid"), rs.getInt("questionId"), rs.getString("name"), rs.getString("email"), rs.getString("content"), rs.getTimestamp("dateMade"), rs.getInt("vote"));
                answers.add(answer);
            }
            return answers;
        }catch(Exception e){
            Answer answer = new Answer();
            answers.add(answer);
            return answers;
        }finally{
            db.closeConnection();
            db = null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addAnswer")
    @Oneway
    public void addAnswer(@WebParam(name = "answer") Answer answer, @WebParam(name = "token")String token) {
    	Auth auth = new Auth();
    	int ret = auth.check(answer.getEmail(),token);
        UserWS usrws = new UserWS();
        User user = usrws.getUserByEmail(answer.getEmail());
        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
        Answer newAnswer = new Answer(user.getId(), answer.getQuestionId(), user.getName(), user.getEmail(), answer.getContent(),currentTime, 0 );
    	
    	if(ret == 1 || ret == 0){
			Database db = new Database();
	        try{
	            String values="(";
                    values+= newAnswer.getUserid() + ",";
	            values+= newAnswer.getQuestionId() +",";
	            values+= "'"+ newAnswer.getName() +"',";
	            values+= "'"+ newAnswer.getEmail() +"',";
	            values+= "'"+ newAnswer.getContent() +"',";
	            values+= "'"+ newAnswer.getDateMade() +"',";
	            values+= newAnswer.getVote() +")";
	            String sql="insert into answers (userid, questionId, name, email, content,dateMade,vote) values " + values;
	            Connection conn = db.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
	            ps.executeUpdate();
                    
                    sql = "update questions set answer= answer+1 where id=" + newAnswer.getQuestionId();
                    ps = conn.prepareStatement(sql);
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
    @WebMethod(operationName = "deleteAnswer")
    @Oneway
    public void deleteAnswer(@WebParam(name = "id") int id, @WebParam(name = "questionId") int questionId, @WebParam(name = "email") String email, @WebParam(name = "token") String token) {
    	Auth auth = new Auth();
    	int ret = auth.check(email,token);
    	if(ret == 1 || ret == 0){
    		Database db = new Database();
	        try{
	            String sql="delete from questions where questionId=" + questionId + "and id=" + id;
	            PreparedStatement ps = db.getConnection().prepareStatement(sql);
	            ps.executeUpdate();
	        }catch(Exception e){
	            
	        }finally{
	            db.closeConnection();
	            //db = null;
	        }	
    	}
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteAnswer")
    @Oneway
    public void voteAnswer(@WebParam(name = "answer") Answer answer, @WebParam(name = "inc") boolean inc, @WebParam(name = "token") String token) {
        Database db = new Database();
    	Auth auth = new Auth();
    	int ret = auth.check(answer.getEmail(),token);
    	if(ret == 1 || ret == 0){
            Vote vote = new Vote();
            if(vote.voting(answer, inc)){
                try{
                    String val;
                    if (inc) {
                        val = "+";
                    }else{
                        val = "-";
                    }
                    int id = answer.getId();
                    int questionId = answer.getQuestionId();
                    String values="";
                    values+= "vote = vote "+ val +" 1";
                    String sql="update answers set " + values + " where id=" + id + " and questionId=" + questionId;
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
     */
    @WebMethod(operationName = "voteAnswer_1")
    @Oneway
    @RequestWrapper(className = "stackexchange.voteAnswer_1")
    public void voteAnswer(@WebParam(name = "id") int id, @WebParam(name = "questionId") int questionId, @WebParam(name = "inc") boolean inc, @WebParam(name = "email") String email, @WebParam(name = "token") String token) {
    	Auth auth = new Auth();
    	int ret = auth.check(email,token);
    	if(ret == 1 || ret == 0){
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
	            String sql="update answers set " + values + " where id=" + id + "and questionId=" + questionId;
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
    @WebMethod(operationName = "updateAnswer")
    @Oneway
    public void updateAnswer(@WebParam(name = "answer") Answer answer, @WebParam(name = "token") String token) {
    	Auth auth = new Auth();
    	int ret = auth.check(answer.getEmail(),token);
    	if(ret == 1 || ret == 0){
    		Database db = new Database();
	        try{
	            int id = answer.getId();
	            int questionId = answer.getQuestionId();
	            String values="";
	            values+= "content='"+ answer.getContent() +"',";
	            values+= "dateMade='"+ answer.getDateMade() +"',";
	            String sql="update answers set " + values + " where id=" + id + "and questionId=" + questionId;
	            PreparedStatement ps = db.getConnection().prepareStatement(sql);
	            ps.executeUpdate();
	        }catch(Exception e){
	            
	        }finally{
	            db.closeConnection();
	            //db = null;
	        }	
    	}
    }

}
