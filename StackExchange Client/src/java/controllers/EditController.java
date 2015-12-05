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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Tifani
 */
public class EditController extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WebService/QuestionWS.wsdl")
    private QuestionWS_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        if (request.getParameter("q_id") != null) {
            int qId = Integer.parseInt(request.getParameter("q_id"));
            QuestionWS.Question question = getQuestion(qId);
            if (question!= null) {
                request.setAttribute("question", question);
                RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
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
        User user = (User)request.getAttribute("user");
        int q_id = Integer.parseInt(request.getParameter("q_id"));
        if(user!=null) {
            Question question = getQuestion(q_id);
            if(user.getUId()==question.getUId()) {
                String topic = request.getParameter("topic");
                String content = request.getParameter("content");
                int qId = updateQuestion(q_id, topic, content);
                if (qId != -1 ) {
                    request.setAttribute("message","Question edited successfully");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/question?q_id"+qId);
                    dispatcher.forward(request, response);
                }
                else {
                    request.setAttribute("message","Error: Question cannot be edited");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/question?q_id"+qId);
                    dispatcher.forward(request, response);
                }
            }
            else {
                request.setAttribute("message","You have no right to edit");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/question?q_id"+q_id);
                dispatcher.forward(request, response);
            }
        }
        else {
            String url = request.getContextPath() + "question?q_id=" + q_id;
            request.setAttribute("message", "Please log in first");
            request.setAttribute("url", url);
            response.sendRedirect(request.getContextPath() + "/login?st=0");
        }
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

    private Question getQuestion(int qId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestion(qId);
    }

    private int updateQuestion(int qId, java.lang.String topic, java.lang.String content) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.updateQuestion(qId, topic, content);
    }
    

}
