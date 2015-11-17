/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Exception_Exception;
import service.Question;
import service.StackExchangeService_Service;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "EditQuestion", urlPatterns = {"/edit"})
public class GetEditedQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws service.Exception_Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        Question question = getQuestion(Integer.parseInt(request.getParameter("idEdited")));
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!doctype html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Ask Your Question</title>");
            out.println("    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>");
            out.println("    <link rel='stylesheet' href='style.css'>");
            out.println("    <script type='text/javascript' src='../scripts/validate.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>");
            out.println("<div class='container'>");
            out.println("    <h2>Edit your question!</h2>");
            out.println("    <form name='questionForm'  action='editQuestion' method='POST'>");
            out.println("        <input type='text' id='qtopic' name='qtopic' placeholder='Question Topic' value='" + question.getQtopic() + "'/>");
            out.println("        <textarea  id='qcontent' name='qcontent' placeholder='Content' >" + question.getQcontent()+ "</textarea>");
            out.println("        <input type='hidden' name='idEdited' value='" + question.getQid() + "'/>");
            out.println("        <input type='hidden' name='fromDetail' value='" + request.getParameter("fromDetail") + "'/>");
//          out.println("        <input type='hidden' name='isFromDetailPage' value='<?php echo $isFromDetailPage ?>'/>");
            out.println("        <button  id='submitBtn' class='submitBtn' >Edit</button>");
            out.println("    </form>");
            out.println("</div>");
        }
    }

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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(GetEditedQuestion.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(GetEditedQuestion.class.getName()).log(Level.SEVERE, null, ex);
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

    private Question getQuestion(int qid) throws Exception_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.getQuestion(qid);
    }

}
