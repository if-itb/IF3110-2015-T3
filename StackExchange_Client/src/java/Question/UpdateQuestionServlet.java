/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.question.QuestionWS_Service;

/**
 *
 * @author ASUS X202E
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/updateQuestion"})
public class UpdateQuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service service;

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
        Cookie cookies[] = request.getCookies();

        String token_id = "";
        boolean found = false;
        int i=0;
        while (i<cookies.length && !found) {
            if ("stackexchange_token".equals(cookies[i].getName())) {
                token_id = cookies[i].getValue();
                found = true;
            } else {
                i++;
            }
        }
        int success = -1;
        if (found) {
            int question_id = Integer.parseInt(request.getParameter("question_id"));
            String topic = request.getParameter("topic");
            String content = request.getParameter("content");
            success = editQuestion(token_id,question_id,topic,content);
            if (success > 0) {
                response.sendRedirect("view?id="+question_id);
            }
        }
        
        if (!found || success==-1) {
            request.setAttribute("message","Session expired. please login again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request,response);
        }  
    }

    private int editQuestion(java.lang.String token, int questionId, java.lang.String topic, java.lang.String content) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.question.QuestionWS port = service.getQuestionWSPort();
        return port.editQuestion(token, questionId, topic, content);
    }

}
