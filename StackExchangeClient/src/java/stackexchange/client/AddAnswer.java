/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stackexchange.ISConnector.IdentityServiceConnector;
import stackexchangews.services.SQLException_Exception;

/**
 *
 * @author davidkwan
 */
@WebServlet(name = "AddAnswer", urlPatterns = {"/AddAnswer"})
public class AddAnswer extends HttpServlet {

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
    
        //Get userId using token
        String token = request.getParameter("token");
        int userId = IdentityServiceConnector.getUID(token);
        
        if(userId>=0){
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            String content = request.getParameter("content");

            try {
                answerQuestion(questionId, userId, content);
            } catch (SQLException_Exception ex) {
                Logger.getLogger(AskQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.sendRedirect("ViewQuestion?id=" + questionId + "&token=" + token);
        }
        else
            response.sendRedirect("");
    }
    
    private static int answerQuestion(int questionId, int answererId, String content) throws SQLException_Exception {
        stackexchangews.services.AnswerHandler_Service service = new stackexchangews.services.AnswerHandler_Service();
        stackexchangews.services.AnswerHandler port = service.getAnswerHandlerPort();
        return port.answerQuestion(questionId, answererId, content);
    }
    
}
