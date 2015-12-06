package com.yangnormal.sstackex.ws;

import com.yangnormal.sstackex.ws.classes.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.ArrayList;


@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceInterface {
    @WebMethod
    int getUid(String token);
    @WebMethod
    int register(String name, String email, String password);
    @WebMethod
    int postQuestion(String token, String title, String content, String uagent, String ip) throws Exception;
    @WebMethod
    int postAnswer(int qid, String token, String content, String uagent, String ip) throws Exception;
    @WebMethod
    int deleteQuestion(int qid, String token, String uagent, String ip) throws Exception;
    @WebMethod
    Question getQuestion(int qid);
    @WebMethod
    Question[] getQuestionList();
    @WebMethod
    Answer[] getAnswerList(int qid);
    @WebMethod
    int vote(int type, int id, int direction, String token, String uagent, String ip) throws Exception;
    @WebMethod
    int updateQuestion(int qid, String token, String title, String content, String uagent, String ip) throws Exception;
}
