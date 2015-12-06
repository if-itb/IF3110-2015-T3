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
import question.Question;
import question.QuestionsWS_Service;
import user.User;
import user.UserWS_Service;
import ClientValidate.ClientValidate;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Bimo
 */
public class deletequestion extends HttpServlet {

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
        
        Cookie[] cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent").replace(';', '%');// Ambil user agent dari client
        useragent = useragent.replace(',', '$');
        // ** Ambil IP Address Client
        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
        if (ipAddress == null)
            ipAddress = request.getRemoteAddr();  
        
        String token = ClientValidate.tokenExtract(cookies);
        if (token == null) {
            int questionid = Integer.parseInt(request.getParameter("qid"));
            question.Question question = getQuestionById(questionid);
            // check if the current logged user is the one that creates the question
            if (getUserByToken(token).getUid() == question.getQuestionUid()) {
                int ins = deleteQuestion(token, ipAddress, useragent, questionid);
                if (ins > 0){
                    response.sendRedirect("home");
                }else if (ins == 0){
                    String error = "Please LOG IN AGAIN : YOUR TOKEN HAS EXPIRED :p";
                    request.setAttribute("error", error);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp"); 
                    dispatcher.forward(request, response); 
                }else if (ins == -1){
                    String error = "YOUR IP ADDRESS HAS CHANGED";
                    request.setAttribute("error", error);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp"); 
                    dispatcher.forward(request, response); 
                }else if (ins == -2){
                    String error = "YOUR WEB BROWSER HAS CHANGED";
                    request.setAttribute("error", error);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp"); 
                    dispatcher.forward(request, response); 
                }else if (ins == -3){
                    String error = "YOUR TOKEN IS INVALID PLEASE LOGIN";
                    request.setAttribute("error", error);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp"); 
                    dispatcher.forward(request, response); 
                }
            }

        } else {
            response.sendRedirect("login.jsp");

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



    private User getUserByToken(java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_1.getUserWSPort();
        return port.getUserByToken(token);
    }

    private Question getQuestionById(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.getQuestionById(qid);
    }

    private int deleteQuestion(java.lang.String token, java.lang.String ipAddress, java.lang.String useragent, int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.deleteQuestion(token, ipAddress, useragent, qid);
    }

}
