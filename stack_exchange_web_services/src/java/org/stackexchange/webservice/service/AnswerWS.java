/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stackexchange.webservice.service;

import com.google.gson.Gson;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.stackexchange.Answer;
import org.stackexchange.Question;
import org.stackexchange.webservice.dao.AnswerDao;
import org.stackexchange.webservice.dao.AnswerVoteDao;
import org.stackexchange.webservice.dao.QuestionDao;
import org.stackexchange.webservice.dao.QuestionVoteDao;

/**
 *
 * @author vincentsthe
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertAnswer")
    public boolean insertAnswer(@WebParam(name = "questionId") long questionId, @WebParam(name = "content") String content, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);

            AnswerDao answerDao = new AnswerDao();
            answerDao.insert(userId, questionId, content);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getByQuestionId")
    public String getByQuestionId(@WebParam(name = "questionId") long questionId) {
        AnswerDao answerDao = new AnswerDao();
        
        List<Answer> answerList = answerDao.getByQuestionId(questionId);
        Gson gson = new Gson();
        return gson.toJson(answerList);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "upvoteAnswer")
    public boolean upvoteAnswer(@WebParam(name = "id") long id, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        AnswerDao answerDao = new AnswerDao();
        AnswerVoteDao answerVoteDao = new AnswerVoteDao();
        System.out.println("test");
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
                    
            if (answerVoteDao.existsByQuestionIdUserId(id, userId)) {
                long vote = answerVoteDao.getVoteCountByAnswerIdUserId(id, userId);
                
                if (vote == -1) {
                    answerDao.upvote(id);
                    answerDao.upvote(id);
                } else if (vote == 0) {
                    answerDao.upvote(id);
                }
                answerVoteDao.update(id, userId, 1);
            } else {
                answerVoteDao.insert(id, userId, 1);
                answerDao.upvote(id);
            }
            
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "downvoteAnswer")
    public boolean downvoteAnswer(@WebParam(name = "id") long id, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        AnswerDao answerDao = new AnswerDao();
        AnswerVoteDao answerVoteDao = new AnswerVoteDao();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
                    
            if (answerVoteDao.existsByQuestionIdUserId(id, userId)) {
                long vote = answerVoteDao.getVoteCountByAnswerIdUserId(id, userId);
                
                if (vote == 1) {
                    answerDao.downvote(id);
                    answerDao.downvote(id);
                } else if (vote == 0) {
                    answerDao.downvote(id);
                }
                answerVoteDao.update(id, userId, -1);
            } else {
                answerVoteDao.insert(id, userId, -1);
                answerDao.downvote(id);
            }
            
            return true;
        } else {
            return false;
        }
    }
}
