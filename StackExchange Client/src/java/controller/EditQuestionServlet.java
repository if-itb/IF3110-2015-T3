/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import answermodel.AnswerWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import questionmodel.Question;
import questionmodel.QuestionWS_Service;
import usermodel.UserWS_Service;

/**
 *
 * @author adek
 */
public class EditQuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
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
        //get userid from cookies using web service
            String token = "";
            Cookie[] cookies = request.getCookies();
            if(cookies==null) {      
                System.out.println("COOKIES NULL");
            }
            else {                
                for(Cookie cookie : cookies) {
                    if("token".equals(cookie.getName())) { 
                        token = cookie.getValue();
                        System.out.println(token);
                        break;
                    }   
                }
            }
            int userid = getIDUserbyToken(token);
            int qid = Integer.parseInt(request.getParameter("qid"));
            int useridhome = Integer.parseInt(request.getParameter("id"));
            if(userid==useridhome) {
                Question q = getQuestionByID(qid);
                request.setAttribute("question", q);
                System.out.println();
                request.getRequestDispatcher("EditQuestion.jsp").forward(request, response);
            } else{
                response.sendRedirect(request.getContextPath() + "/ShowQuestionServlet");
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

    private Question getQuestionByID(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestionByID(id);
    }

    private int getIDUserbyToken(java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        usermodel.UserWS port = service_1.getUserWSPort();
        return port.getIDUserbyToken(token);
    }

}
