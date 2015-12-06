/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import QuestionWS.Question;
import QuestionWS.QuestionWS_Service;
import UserWS.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
/**
 *
 * @author vanyadeasysafrina
 */
public class DeleteController extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WebService/QuestionWS.wsdl")
    private QuestionWS_Service service;

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
        User user = (User) request.getAttribute("user");
        if (user != null) {
            int qId = Integer.parseInt(request.getParameter("q_id"));
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Cookie[] cookies = httpRequest.getCookies();
            String status = "No cookie";
            // Check cookie with name auth
            if (cookies != null) {
                String token = null;
                for (Cookie cookie : cookies) {
                    status = "No token cookie";
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                        break;
                    }
                }
                Question question = getQuestion(qId);
                if (question.getUId()==user.getUId()) {
                    //Same user
                    int isSuccessful = deleteQuestion(token);
                    if(isSuccessful==1) {
                        request.setAttribute("message", "Question deleted sucessfully");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
                        dispatcher.forward(request, response);
                    } else {
                        request.setAttribute("message", "Question cannot be deleted");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
                        dispatcher.forward(request, response);
                    }
                } else {
                    response.sendRedirect(request.getContextPath());
                } 
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
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
        doGet(request, response);
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

    private int deleteQuestion(int qId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.deleteQuestion(qId);
    }

    private Question getQuestion(int qId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestion(qId);
    }

}
