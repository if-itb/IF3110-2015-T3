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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.Answer;
import model.Question;
import org.stackexchange.webservice.service.AnswerWS_Service;
import org.stackexchange.webservice.service.QuestionWS_Service;
import org.stackexchange.webservice.service.TokenService;

/**
 *
 * @author Alex
 */
public class AddAnswerServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/stack_exchange_web_services/QuestionWS.wsdl")
    private QuestionWS_Service service_1 = new QuestionWS_Service();

//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/stack_exchange_web_services/AnswerWS.wsdl")
    private AnswerWS_Service service = new AnswerWS_Service();

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
      
        String json = getByQuestionId(Long.valueOf(request.getParameter("question_id")));
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Answer>>() {}.getType();
        List<Answer> answerListFromJson = gson.fromJson(json, listType);
        
        json = getById(Long.valueOf(request.getParameter("question_id")));
        gson = new Gson();
        Question questionFromJson = gson.fromJson(json, Question.class);
        
        TokenService TS = new TokenService();
        Map<String,String> userInfo = TS.getUserInfo(questionFromJson.getUserId());
        String question_email = userInfo.get("email") ;
        String question_name = userInfo.get("name");
        String token = request.getParameter("token");
        int user_id = TS.getUserId(token);
        List<String> answerName = new ArrayList<>();
        List<String> answerEmail = new ArrayList<>();
        String answer_name;
        String answer_email;
        for(Answer a : answerListFromJson) {
            userInfo = TS.getUserInfo(a.getUserId());
            answer_name = userInfo.get("name");
            answer_email = userInfo.get("email");
            answerName.add(answer_name);
            answerEmail.add(answer_email);
        }
            
        request.setAttribute("answer_name", answerName);
        request.setAttribute("answer_email", answerEmail);
        request.setAttribute("question", questionFromJson);
        request.setAttribute("answer", answerListFromJson);
        request.setAttribute("question_name",question_name);
        request.setAttribute("question_email",question_email);
        request.setAttribute("token",token);
        request.setAttribute("user_id",user_id);
        request.getRequestDispatcher("question.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        insertAnswer(Long.valueOf(request.getParameter("question_id")),request.getParameter("content"),request.getParameter("token"));
        response.sendRedirect("question?question_id=" + Long.valueOf(request.getParameter("question_id")) + "&token=" + request.getParameter("token") + "&from=question");
    }
    
    private String getByQuestionId(long questionId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.stackexchange.webservice.service.AnswerWS port = service.getAnswerWSPort();
        return port.getByQuestionId(questionId);
    }

    private String getById(long id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.stackexchange.webservice.service.QuestionWS port = service_1.getQuestionWSPort();
        return port.getById(id);
    }

    private boolean insertAnswer(long questionId, java.lang.String content, java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.stackexchange.webservice.service.AnswerWS port = service.getAnswerWSPort();
        return port.insertAnswer(questionId, content, token);
    }

}
