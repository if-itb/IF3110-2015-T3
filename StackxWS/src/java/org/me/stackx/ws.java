/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.stackx;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.me.stackx.model.AnswerModel;
import org.me.stackx.model.QuestionModel;
import org.me.stackx.model.UserModel;
import org.me.stackx.module.Answer;
import org.me.stackx.module.Question;
import org.me.stackx.module.User;

/**
 *
 * @author natanelia
 */
@WebService(serviceName = "ws")
public class ws {


    /**
     * Web service operation
     * @return createdQuestionId
     */
    @WebMethod(operationName = "createQuestion")
    public int createQuestion(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "title") String title, @WebParam(name = "content") String content) {
        User u = new User(access_token, user_agent, ip_address);
        return QuestionModel.create(u, title, content);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllQuestions")
    public Question[] getAllQuestions() {
        return QuestionModel.getAll();
    }

    /**
     * Web service operation
     * @param id
     * @return question
     */
    @WebMethod(operationName = "getQuestionById")
    public Question getQuestionById(@WebParam(name = "id") final int id) {
        return QuestionModel.getById(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editQuestion")
    public int editQuestion(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id, @WebParam(name = "title") final String title, @WebParam(name = "content") final String content) {
        User u = new User(access_token, user_agent, ip_address);
        return QuestionModel.edit(u, id, title, content);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteUpQuestion")
    public int voteUpQuestion(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id) {
        User u = new User(access_token, user_agent, ip_address);
        return QuestionModel.vote(u, id, 1);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteDownQuestion")
    public int voteDownQuestion(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id) {
        User u = new User(access_token, user_agent, ip_address);
        return QuestionModel.vote(u, id, -1);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    public String deleteQuestion(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id) {
        User u = new User(access_token, user_agent, ip_address);
        return QuestionModel.delete(u, id);
    }

    
    /**
     * Web service operation
     * @return createdAnswerId
     */
    @WebMethod(operationName = "createAnswer")
    public int createAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "question_id") int question_id, @WebParam(name = "content") String content) {
        User u = new User(access_token, user_agent, ip_address);
        return AnswerModel.create(u, question_id, content);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllAnswersFromQuestionId")
    public Answer[] getAllAnswersFromQuestionId(@WebParam(name = "id") final int id) {
        return AnswerModel.getAllFromQuestionId(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editAnswer")
    public int editAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id, @WebParam(name = "content") final String content) {
        User u = new User(access_token, user_agent, ip_address);
        return AnswerModel.edit(u, id, content);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteUpAnswer")
    public int voteUpAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id) {
        User u = new User(access_token, user_agent, ip_address);
        return AnswerModel.vote(u, id, 1);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteDownAnswer")
    public int voteDownAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id) {
        User u = new User(access_token, user_agent, ip_address);
        return AnswerModel.vote(u, id, -1);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteAnswer")
    public String deleteAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "ip_address") String ip_address, @WebParam(name = "user_agent") String user_agent, @WebParam(name = "id") final int id) {
        User u = new User(access_token, user_agent, ip_address);
        return AnswerModel.delete(u, id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registerUser")
    public String registerUser(@WebParam(name = "name") final String name, @WebParam(name = "email") final String email, @WebParam(name = "password") final String password) {
        return UserModel.register(name, email, password);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserById")
    public User getUserById(@WebParam(name = "id") final int id) {
        return UserModel.getById(id);
    }
    
}
