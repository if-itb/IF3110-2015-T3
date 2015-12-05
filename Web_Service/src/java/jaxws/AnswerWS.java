/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import java.sql.*;
import java.util.ArrayList;
import javax.jws.*;

@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    DB database;
    Answer model;

    public AnswerWS() throws Throwable {
        database = new DB();
        model = new Answer();
    }
      @WebMethod(operationName = "getAnswerQID")
      @WebResult(name="gtQID")
      public int getAnswerQID(@WebParam(name = "id") int id) {
        String query = "SELECT `qid` FROM answer WHERE id=" + id;
        int qid = 0;
        try {
          ResultSet tmp = database.getResultQuery(query);
          tmp.next();
          qid = tmp.getInt("qid");
        } catch (Throwable e) {
          e.printStackTrace();
        }
        return qid;
      }
    
    @WebMethod(operationName = "getAnswerVote")
    @WebResult(name="gtVote")
    public int getAnswerVote(@WebParam(name = "id") int id) {
      String query = "SELECT vote FROM answer WHERE id=" + id;
      int vote = 0;
      try {
        ResultSet tmp = database.getResultQuery(query);
        tmp.next();
        vote = tmp.getInt("vote");
      } catch (Throwable e) {
        e.printStackTrace();
      }
      return vote;
    }
    
    @WebMethod(operationName = "getAnswerUID")
    @WebResult(name="getAnswerUID")
    public int getAnswerUID(@WebParam(name="aid") int aid) {
      String query = "SELECT uid FROM answer WHERE id=" + aid;
      ResultSet rs = database.getResultQuery(query);
      int uid = 0;
      try {
        rs.next();
        uid = rs.getInt("uid");
      } catch(Exception e) {
      }
      return uid;
    }
    
    @WebMethod(operationName = "setAnswerVote")
    @WebResult(name="stVote")
    public void setAnswerVote(@WebParam(name = "id") int id, @WebParam(name = "val") int val) {
      String query = "UPDATE `answer` SET `vote`=" + val + " WHERE `id`=" + id;
      try {
        database.executeQuery(query);
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
    
    /**
     * Web service operation
     * @param qid
     * @return 
     */
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name="Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
      ArrayList<Answer> answers = new ArrayList<>();  
      String query = "SELECT * FROM answer JOIN user WHERE uid = user.id AND qid="+qid;
      ResultSet rs = database.getResultQuery(query);
      return model.fetchAnswers(rs);
    }
    /**
     * Web service operation
     * @param uid
     * @param qid
     * @param content
     */
    @WebMethod(operationName = "insertAnswer")
    @WebResult(name="saveAnswer")
    public void insertAnswer(@WebParam(name = "uid") int uid,@WebParam(name = "qid") int qid,@WebParam(name = "content") String content) {
      String query = "INSERT INTO `answer` (`qid`, `uid`, `content`) VALUES ('"+qid+"','"+uid+"', '"+content+"')";
      database.executeQuery(query);
    }
    /**
     * Web service operation
     * @param aid
     * @param uid
     * @param type
     */
    @WebMethod(operationName = "voteAnswer")
    @WebResult(name="vtAnswer")
    public void voteAnswer(@WebParam(name = "aid") int aid,@WebParam(name = "uid") int uid,@WebParam(name = "type") int type) {
      String query = "SELECT * FROM vote_answer WHERE aid=" + aid + " AND uid=" + uid;
      ResultSet tmp = database.getResultQuery(query);
      try {
        if(tmp.next()) return;  // user pernah melakukan vote
      } catch(Throwable e) {
      }
      query = "INSERT INTO `vote_answer` (`aid`, `uid`, `type`) VALUES ('"+aid+"','"+uid+"','"+type+"')";
      database.executeQuery(query);
      int vote = getAnswerVote(aid);
      setAnswerVote(aid, vote + type);
    }
    
    @WebMethod(operationName = "numVoteAnswer")
    @WebResult(name = "numVoteAnswer")
    public int numVoteAnswer(@WebParam(name="uid") int uid, @WebParam(name="aid") int aid) {
      String query = "SELECT type FROM vote_answer WHERE aid=" + aid + " AND uid=" + uid;
      ResultSet rs = database.getResultQuery(query);
      int num = 0;
      try {
        rs.next();
        num = rs.getInt("type");
      } catch(Exception e) {
      }
      return num;
    }
    
    
}
