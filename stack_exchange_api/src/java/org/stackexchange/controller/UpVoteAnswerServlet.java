/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stackexchange.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.Answer;
import model.Question;
import org.stackexchange.webservice.dao.AnswerDao;
import org.stackexchange.webservice.dao.AnswerVoteDao;
import org.stackexchange.webservice.dao.QuestionDao;
import org.stackexchange.webservice.service.TokenService;

/**
 *
 * @author Alex
 */
public class UpVoteAnswerServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String from = request.getParameter("from");
        String token = request.getParameter("token");
        if (token != null && !token.isEmpty()) {
            if (from.equals("index")){
                upvoteAnswer(Long.valueOf(request.getParameter("answer_id")),request.getParameter("token"));
                response.sendRedirect("/stack_exchange_netbeans/index?token=" + request.getParameter("token")+ "&from=" +from);
            }
            else{
                upvoteAnswer(Long.valueOf(request.getParameter("answer_id")),request.getParameter("token"));
                response.sendRedirect("/stack_exchange_netbeans/question?question_id=" + request.getParameter("question_id") + "&token=" + request.getParameter("token")+ "&from=" +from);
            }
        }
        else{
            response.sendRedirect("http://localhost:7000/login");
        }
       
    }
      

    public boolean upvoteAnswer(long id,String token) {
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
    
    public String getByQuestionId(long questionId) {
        AnswerDao answerDao = new AnswerDao();
        
        List<Answer> answerList = answerDao.getByQuestionId(questionId);
        Gson gson = new Gson();
        return gson.toJson(answerList);
    }

    public String getById(long id) {
        QuestionDao questionDao = new QuestionDao();
        Gson gson = new Gson();
        String json = gson.toJson(questionDao.getById(id));
        
        //cara balikin
        Question question = gson.fromJson(json, Question.class);
        
        return json;
    }
}