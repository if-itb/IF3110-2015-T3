/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stackexchange.webservice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import org.stackexchange.Question;
import org.stackexchange.webservice.dao.QuestionDao;
import org.stackexchange.webservice.dao.QuestionVoteDao;
import org.stackexchange.webservice.dao.UserDao;

/**
 *
 * @author vincentsthe
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getAll")
    public String getAll() {
        QuestionDao questionDao = new QuestionDao();
        UserDao userDao = new UserDao();

        ArrayList<Question> questionList = (ArrayList) questionDao.getAll();
        for (Question question: questionList) {
            question.setName(userDao.getById(question.getUserId()).getName());
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(questionList);
        
        // cara balikin
        Type listType = new TypeToken<List<Question>>() {}.getType();
        List<Question> questionListFromJson = gson.fromJson(json, listType);
        
        return json;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insert")
    public boolean insert(@WebParam(name = "title") String title, @WebParam(name = "content") String content, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);

            QuestionDao questionDao = new QuestionDao();
            Question question = questionDao.insert(userId, title, content);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getById")
    public String getById(@WebParam(name = "id") long id) {
        QuestionDao questionDao = new QuestionDao();
        Gson gson = new Gson();
        String json = gson.toJson(questionDao.getById(id));
        
        //cara balikin
        Question question = gson.fromJson(json, Question.class);
        
        return json;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "upvote")
    public boolean upvote(@WebParam(name = "id") long id, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        QuestionDao questionDao = new QuestionDao();
        QuestionVoteDao questionVoteDao = new QuestionVoteDao();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
                    
            if (questionVoteDao.existsByQuestionIdUserId(id, userId)) {
                long vote = questionVoteDao.getVoteCountByQuestionIdUserId(id, userId);
                
                if (vote == -1) {
                    questionDao.upvote(id);
                    questionDao.upvote(id);
                } else if (vote == 0) {
                    questionDao.upvote(id);
                }
                questionVoteDao.update(id, userId, 1);
            } else {
                questionVoteDao.insert(id, userId, 1);
                questionDao.upvote(id);
            }
            
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "downvote")
    public boolean downvote(@WebParam(name = "id") long id, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        QuestionDao questionDao = new QuestionDao();
        QuestionVoteDao questionVoteDao = new QuestionVoteDao();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
                    
            if (questionVoteDao.existsByQuestionIdUserId(id, userId)) {
                long vote = questionVoteDao.getVoteCountByQuestionIdUserId(id, userId);
                
                if (vote == 1) {
                    questionDao.downvote(id);
                    questionDao.downvote(id);
                } else if (vote == 0) {
                    questionDao.downvote(id);
                }
                questionVoteDao.update(id, userId, -1);
            } else {
                questionVoteDao.insert(id, userId, -1);
                questionDao.downvote(id);
            }
            
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "update")
    public boolean update(@WebParam(name = "id") long id, @WebParam(name = "title") String title, @WebParam(name = "content") String content, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
            QuestionDao questionDao = new QuestionDao();

            questionDao.update(id, title, content);
            return true;
        } else {
            return false;
        }
    }
    
    @WebMethod(operationName = "delete")
    public boolean delete(@WebParam(name = "id") int id, @WebParam(name = "token") String token) {
        TokenService tokenService = new TokenService();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
            QuestionDao questionDao = new QuestionDao();

            questionDao.delete(id);
            return true;
        } else {
            return false;
        }
    }
     
}
