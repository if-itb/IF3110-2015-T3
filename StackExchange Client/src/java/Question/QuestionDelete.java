/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import QuestionWS.QuestionWS_Service;
import Tools.Tools;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Husni
 */
@WebServlet(name = "QuestionDelete", urlPatterns = {"/deletequestion"})
public class QuestionDelete extends HttpServlet {

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
        
        Tools tools = new Tools();
        String access_token = tools.getCookie("access_token", request);
        
        String userIP = request.getHeader("X-FORWARDED-FOR");  
        if (userIP == null) {  
            userIP = request.getRemoteAddr();  
        }
        String userAgent = request.getHeader("User-Agent");
        
        int id_question = Integer.parseInt(request.getParameter("id_question"));
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        
        int ret = deleteQuestion(access_token, userIP, userAgent, id_question, id_user);
                
        switch (ret) {
          case 1:
            response.sendRedirect(request.getContextPath() + "/home");
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

    private int deleteQuestion(java.lang.String token, java.lang.String userIP, java.lang.String userAgent, int id, int uid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.deleteQuestion(token, userIP, userAgent, id, uid);
    }

    
}
