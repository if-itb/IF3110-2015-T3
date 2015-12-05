/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
/**
 *
 * @author mfikria
 */
@WebServlet(name = "QuestionEdit", urlPatterns = {"/edit"})
public class QuestionEdit extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/Question_WS.wsdl")
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
        int save = Integer.parseInt(request.getParameter("save"));
        int qid = Integer.parseInt(request.getParameter("qid"));
//        int uid = Integer.parseInt(request.getParameter("uid"));
//        if(tool.Util.isAuthUser(request, uid))
        if(save == 0) {
            question.Question q = getQuestion(qid);
            request.setAttribute("question", q);
            request.getRequestDispatcher("questionedit.jsp").forward(request, response);
        }
        else {
            
            
             Integer res = updateQuestion(
                    tool.Util.getTokenCookie(request), 
                    qid, 
                    request.getParameter("topic"), 
                    request.getParameter("content"));
            
            // Pass token and object question to web service
            String url = "question?qid="+qid;
            response.addHeader("statustoken", res.toString());
            response.sendRedirect(url);
            
            
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

  
    private Question getQuestion(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestion(qid);
    }

    private Integer updateQuestion(java.lang.String token, int qid, java.lang.String topic, java.lang.String content) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionWS port = service.getQuestionWSPort();
        return port.updateQuestion(token, qid, topic, content);
    }



}
