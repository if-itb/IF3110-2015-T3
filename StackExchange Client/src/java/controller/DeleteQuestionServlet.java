/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import questionmodel.ParseException_Exception;
import questionmodel.QuestionWS_Service;
import usermodel.UserWS_Service;

/**
 *
 * @author adek
 */
public class DeleteQuestionServlet extends HttpServlet {
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
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        usermodel.UserWS port2 = service_1.getUserWSPort();
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
            int userid = port2.getIDUserbyToken(token);
            int useridhome = Integer.parseInt(request.getParameter("id"));
            if(userid==useridhome) {
                int qid = Integer.parseInt(request.getParameter("qid"));
                int res=0;
                try {
                    res = port.deleteQuestion(qid,token);
                } catch (ParseException_Exception ex) {
                    Logger.getLogger(DeleteQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("results", res);
                response.sendRedirect(request.getContextPath() + "/ShowQuestionServlet");
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

}
