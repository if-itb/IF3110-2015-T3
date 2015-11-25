/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nasipadang.jaxws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import org.data.Answer;
import org.data.Question;

/**
 *
 * @author user
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface StackExchange {
    @WebMethod public boolean register(String username, String email, String password);
    @WebMethod public String login(String email, String password);
    @WebMethod public Question getQuestion(String token, int id);
    @WebMethod public Question[] getAllQuestion(String token);
    @WebMethod public Answer getAnswer(String token, int id_answer);
    @WebMethod public Answer[] getAllAnswer(String token, int id);
    @WebMethod public int addQuestion(String token, String topic, String content);
    @WebMethod public boolean addAnswer(int id, String token, String content);
    @WebMethod public int editQuestion(int id, String token, String topic, String content);
    @WebMethod public boolean deleteQuestion(int id, String token);
    @WebMethod public int updateVoteAnswer(String token, int id_answer, int vote);
    @WebMethod public int updateVoteQuestion(String token, int id, int vote);
}