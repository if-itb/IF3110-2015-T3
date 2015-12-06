/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import question.Question;
import question.QuestionsWS_Service;
import user.User;
import ClientValidate.ClientValidate;

import user.UserWS_Service;

/**
 *
 * @author mochamadtry
 */
public class editquestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/UserWS.wsdl")
    private UserWS_Service service_1;

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
        
        int qid = Integer.parseInt(request.getParameter("qid"));
        
        Cookie[] cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent").replace(';', '%');// Ambil user agent dari client
        useragent = useragent.replace(',', '$');
        String ipAddress = request.getHeader("X-FORWARDED-FOR");    // ** Ambil IP Address Client
        if (ipAddress == null)
            ipAddress = request.getRemoteAddr();  
        String token = ClientValidate.tokenExtract(ipAddress, useragent, cookies);
        
        if (token == null) {
            request.setAttribute("error", "You have to log in first!");
            response.sendRedirect("login.jsp");
        } else {
            // get user and compare it with the token ID
            User user = getUserByToken(token);
            Question question = getQuestionById(qid);
            if (question.getQuestionUid() == user.getUid()) {
                // if the question is edited by the same user, proceed
                request.setAttribute("question", question);
                request.setAttribute("name", user.getName());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editquestion.jsp"); 
                dispatcher.forward(request, response);

            } else {
                request.setAttribute("error", "You're not the owner!");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + request.getRequestURL().toString()); 
                dispatcher.forward(request, response);
            }
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

    private Question getQuestionById(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.getQuestionById(qid);
    }

    private User getUserByToken(java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_1.getUserWSPort();
        return port.getUserByToken(token);
    }

}
