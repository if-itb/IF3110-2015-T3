/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Answer;

import AnswerWS.Answer;
import AnswerWS.AnswerWS_Service;
import Tools.Tools;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author User
 */
public class AnswerNew extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
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
      
        Tools tools = new Tools();
        String access_token = tools.getCookie("access_token", request);
        
        String userIP = request.getHeader("X-FORWARDED-FOR");  
        if (userIP == null) {  
            userIP = request.getRemoteAddr();  
        }
        String userAgent = request.getHeader("User-Agent");
        
        AnswerWS.Answer answer = new Answer();
        
        answer.setContent(request.getParameter("content"));
        answer.setIdQuestion(Integer.parseInt(request.getParameter("qid")));
        
        int ret = insertAnswer(access_token, userIP, userAgent, answer);
        
        switch (ret) {
          case 1:
            response.sendRedirect(request.getContextPath() + "/question?id=" + Integer.parseInt(request.getParameter("qid")));
            break;
          case 0:
            response.sendRedirect(request.getContextPath() + "/login?alert=0");            
            break;
          case -1:    
            response.sendRedirect(request.getContextPath() + "/login?alert=-1");        
            break;
          default:
            response.sendRedirect(request.getContextPath() + "/login?alert=-1");
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

    private int insertAnswer(java.lang.String token, java.lang.String userIP, java.lang.String userAgent, AnswerWS.Answer answer) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AnswerWS.AnswerWS port = service.getAnswerWSPort();
        return port.insertAnswer(token, userIP, userAgent, answer);
    }

}
