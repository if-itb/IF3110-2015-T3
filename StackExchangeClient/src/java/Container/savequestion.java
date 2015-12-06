/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import question.QuestionsWS_Service;
import ClientValidate.ClientValidate;

/**
 *
 * @author mochamadtry
 */
public class savequestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/QuestionsWS.wsdl")
    private QuestionsWS_Service service;

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

        Cookie[] cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent"); // Ambil user agent dari client
        String ipAddress = request.getHeader("X-FORWARDED-FOR");  // ** Ambil IP Address Client
        if (ipAddress == null)
            ipAddress = request.getRemoteAddr();  
        
        // validate the token
        String token = ClientValidate.tokenExtract(ipAddress, useragent, cookies);
        if (token == null) {
            request.setAttribute("error", "You have to log in first!");
            response.sendRedirect("login.jsp");
        } else {
            int qid = Integer.parseInt(request.getParameter("qid"));
            String newTopic = request.getParameter("topic");
            String newContent = request.getParameter("content");
            // update the question
            updateQuestion(token, qid, newTopic, newContent);
            response.sendRedirect("viewpost?qid="+qid);
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

    private int updateQuestion(java.lang.String token, int qid, java.lang.String newTopic, java.lang.String newContent) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.updateQuestion(token, qid, newTopic, newContent);
    }

}
