/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.servlet;

import QuestionWS.Question;
import QuestionWS.QuestionWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author zulvafachrina
 */
public class QuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8083/Stack_Exchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service service_1;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try (PrintWriter out = response.getWriter()) {
        String token = "";
        Cookie[] cookie = request.getCookies();
        
        for(Cookie obj : cookie){
                //out.println(obj.getName());
                if(obj.getName().equals("token")){
                token = obj.getValue();
                //out.println(obj.getValue());
                break;
            }
	}
        
        
        java.util.List<QuestionWS.Question> questions = showAllQuestion();
        request.setAttribute("token", token);
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/question-list.jsp").forward(request, response);
        } 
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int countAnswer(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.countAnswer(qid);
    }

    private int deleteQuestion(int id, String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.deleteQuestion(id, token);
    }

    private int editQuestion(int id, java.lang.String topic, java.lang.String content, java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.editQuestion(id, topic, content, token);
    }

    private java.util.List<QuestionWS.Answer> getAnswerByQuestionId(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.getAnswerByQuestionId(id);
    }

    private Question getQuestionById(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.getQuestionById(id);
    }

    private int insertQuestion(QuestionWS.Question question, String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.insertQuestion(question, token);
    }

    private java.util.List<QuestionWS.Question> showAllQuestion() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.showAllQuestion();
    }    
}
