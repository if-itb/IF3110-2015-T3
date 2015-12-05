/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.servlet;

import AnswerWS.Answer;
import AnswerWS.AnswerWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddAnswer extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8083/Stack_Exchange_WS/AnswerWS.wsdl")
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
        request.setAttribute("token",token);
        
        int question_id = Integer.parseInt(request.getParameter("qid"));
        String answer_content= request.getParameter("answer_content");
        //int question_userid= Integer.parseInt(request.getParameter("question_userid"));
        
       
        Answer answer = new Answer();
        answer.setUserId(1);
        answer.setQuestionId(question_id);
        answer.setContent(answer_content);
        
        /*try (PrintWriter out = response.getWriter()) {
            out.println(question_id);
            out.println(answer_content);
        }*/
         
        int success = insertAnswer(answer, token);
        String location= "/Stack_Exchange_Client/QuestionPage?id=" + question_id;
        response.sendRedirect(location);
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

    private int insertAnswer(AnswerWS.Answer answer, String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AnswerWS.AnswerWS port = service.getAnswerWSPort();
        return port.insertAnswer(answer, token);
    }

}
