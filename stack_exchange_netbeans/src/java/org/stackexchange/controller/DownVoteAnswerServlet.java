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
import javax.servlet.ServletException;
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
public class DownVoteAnswerServlet extends HttpServlet {

   // @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/stack_exchange_web_services/QuestionWS.wsdl")
    private QuestionWS_Service service_1 = new QuestionWS_Service();

   // @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/stack_exchange_web_services/AnswerWS.wsdl")
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
        String from = request.getParameter("from");
        String token = request.getParameter("token");
        if (token != null && !token.isEmpty()) {
            if (from.equals("index")){
                downvoteAnswer(Long.valueOf(request.getParameter("answer_id")),request.getParameter("token"));
                response.sendRedirect("/stack_exchange_netbeans/index?token=" + request.getParameter("token")+ "&from=" +from);
            }
            else{
                downvoteAnswer(Long.valueOf(request.getParameter("answer_id")),request.getParameter("token"));
                response.sendRedirect("/stack_exchange_netbeans/question?question_id=" + request.getParameter("question_id") + "&token=" + request.getParameter("token")+ "&from=" +from);
            }
        }
        else{
            response.sendRedirect("http://localhost:7000/login");
        }
       
    }

   
    private boolean downvoteAnswer(long id, java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.stackexchange.webservice.service.AnswerWS port = service.getAnswerWSPort();
        return port.downvoteAnswer(id, token);
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

    

}