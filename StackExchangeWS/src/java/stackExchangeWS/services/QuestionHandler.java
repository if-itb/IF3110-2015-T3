/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackExchangeWS.services;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import stackExchangeWS.database.DbQuestionManager;
import stackExchangeWS.database.Question;

/**
 *
 * @author davidkwan
 */
@WebService(serviceName = "QuestionHandler")
public class QuestionHandler {
    /**
     * Web service operation
     */
    @WebMethod(operationName = "askQuestion")
    public int askQuestion(@WebParam(name = "askerId") int askerId, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) throws SQLException {
        Question question = new Question();
        question.setAskerId(askerId);
        question.setTopic(topic);
        question.setContent(content);
        
        DbQuestionManager.askQuestion(question);
        
        return 1;    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestion")
    public Question getQuestion(@WebParam(name = "questionId") int questionId) throws SQLException {
        Question question = DbQuestionManager.selectQuestion(questionId);
        
        return question;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllQuestions")
    public ArrayList<Question> getAllQuestions() throws SQLException {
        //TODO write your implementation code here:
        ArrayList<Question> questions = DbQuestionManager.getAllQuestions();
        
        return questions;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "countVotes")
    public int countVotes(int questionId) throws SQLException {
        return DbQuestionManager.countVoteQuestion(questionId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "votesUpQuestion")
    public int votesUpQuestion(@WebParam(name = "questionId") int questionId, @WebParam(name = "voter") int voter) throws SQLException {
        //TODO write your implementation code here:
        DbQuestionManager.voteQuestion(questionId, voter, 1);
        
        return 1;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "votesDownQuestion")
    public int votesDownQuestion(@WebParam(name = "questionId") int questionId, @WebParam(name = "voter") int voter) throws SQLException {
        //TODO write your implementation code here:
        DbQuestionManager.voteQuestion(questionId, voter, -1);
        
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editQuestion")
    public int editQuestion(@WebParam(name = "questionId") int questionId, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) throws SQLException {
        DbQuestionManager.editQuestion(questionId, topic, content);
        
        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    public int deleteQuestion(@WebParam(name = "questionId") int questionId) throws SQLException {
        DbQuestionManager.deleteQuestion(questionId);
        
        return 1;
    }
    
}
