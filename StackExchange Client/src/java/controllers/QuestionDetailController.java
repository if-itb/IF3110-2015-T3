/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import AnswerWS.AnswerWS_Service;
import QuestionWS.Question;
import QuestionWS.QuestionWS_Service;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Tifani
 */
public class QuestionDetailController extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WebService/QuestionWS.wsdl")
    private QuestionWS_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WebService/AnswerWS.wsdl")
    private AnswerWS_Service service;

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
        response.setContentType("text/html;charset=UTF-8");
        int qId = Integer.parseInt(request.getParameter("q_id"));
        QuestionWS.Question question = getQuestion(qId);
        java.util.List<AnswerWS.Answer> answers = getAnswerByQId(qId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        RequestDispatcher rd = request.getRequestDispatcher("question.jsp");
        rd.forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        int qId = Integer.parseInt(request.getParameter("q_id"));
        QuestionWS.Question question = getQuestion(qId);
        java.util.List<AnswerWS.Answer> answers = getAnswerByQId(qId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        RequestDispatcher rd = request.getRequestDispatcher("question.jsp");
        rd.forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        int qId = Integer.parseInt(request.getParameter("q_id"));
        QuestionWS.Question question = getQuestion(qId);
        java.util.List<AnswerWS.Answer> answers = getAnswerByQId(qId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        RequestDispatcher rd = request.getRequestDispatcher("question.jsp");
        rd.forward(request, response);
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
    
   

    private java.util.List<AnswerWS.Answer> getAnswerByQId(int qId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AnswerWS.AnswerWS port = service.getAnswerWSPort();
        return port.getAnswerByQId(qId);
    }

    private Question getQuestion(int qId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service_1.getQuestionWSPort();
        return port.getQuestion(qId);
    }

}
