/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebService;

/**
 *
 * @author Adz
 */
@WebService(serviceName = "StackExchange", portName = "StackExchangePort", endpointInterface = "service.StackExchange", targetNamespace = "http://service/", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/localhost_8081/StackExchange.wsdl")
public class NewWebServiceFromWSDL {

    public java.util.List<service.Question> search(java.lang.String query) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public service.User getUser(int id) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int getQuestionVoteState(int idUser, int idQuestion) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int getAnswerVoteState(int idUser, int idAnswer) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.lang.String addUser(java.lang.String name, java.lang.String email, java.lang.String password) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public service.Question getQuestion(int id) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.util.List<service.Question> getQuestions() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public service.Question addQuestion(int idUser, java.lang.String topic, java.lang.String content) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean deleteQuestion(int id) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean updateQuestion(int id, java.lang.String topic, java.lang.String content) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean voteQuestionUp(int idUser, int idQuestion) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean voteQuestionDown(int idUser, int idQuestion) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public service.Answer getAnswer(int id) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.util.List<service.Answer> getAnswers(int idQuestion) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public service.Answer addAnswer(int idQuestion, int idUser, java.lang.String content) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean voteAnswerUp(int idUser, int idAnswer) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean voteAnswerDown(int idUser, int idAnswer) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
