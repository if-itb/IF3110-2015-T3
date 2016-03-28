/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
@WebServlet(name = "DownVoteAnswer", urlPatterns = {"/DownVoteAnswer"})
public class DownVoteAnswer extends HttpServlet {


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
        int answerId = Integer.parseInt(request.getParameter("id"));
        
        // Get User ID from token
        String token = request.getParameter("token");
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("user-agent");
        int userId = IdentityServiceConnector.getUID(token, ip, userAgent);
        
        if(userId>=0){
            try {
                votesDownAnswer(answerId, userId);
            } catch (SQLException_Exception ex) {
                Logger.getLogger(DownVoteQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int questionId = getQuestionId(answerId);
            response.sendRedirect("ViewQuestion?id=" + questionId + "&token=" + URLEncoder.encode(token, "UTF-8"));
        }
        else
            response.sendRedirect("");
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

    }
    
    private static int votesDownAnswer(int answerId, int answererId) throws SQLException_Exception {
        stackexchangews.services.AnswerHandler_Service service = new stackexchangews.services.AnswerHandler_Service();
        stackexchangews.services.AnswerHandler port = service.getAnswerHandlerPort();
        return port.votesDownAnswer(answerId, answererId);
    }
    
    private static int getQuestionId(int answerId) {
        stackexchangews.services.AnswerHandler_Service service = new stackexchangews.services.AnswerHandler_Service();
        stackexchangews.services.AnswerHandler port = service.getAnswerHandlerPort();
        return port.getQuestionId(answerId);
    }
    
}
