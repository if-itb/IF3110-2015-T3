/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stackexchange.ISConnector.IdentityServiceConnector;
import stackexchangews.services.Answer;
import stackexchangews.services.Question;
import stackexchangews.services.SQLException_Exception;

/**
 *
 * @author davidkwan
 */
@WebServlet(name = "ViewQuestion", urlPatterns = {"/ViewQuestion"})
public class ViewQuestion extends HttpServlet {
    
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
        
        //Get userId using token
        String token = request.getParameter("token");
        System.out.println("ViewQuestion Token: " + token);
        int userId = IdentityServiceConnector.getUID(token);
        int questionId = Integer.parseInt(request.getParameter("id"));
        
        Question question = new Question();
        try {
            question = getQuestion(questionId);
        } catch (SQLException_Exception ex) {
            Logger.getLogger(ViewQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Answer> answers = null;
        try {
            answers = getAllAnswers(questionId);
        } catch (SQLException_Exception ex) {
            Logger.getLogger(ViewQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        request.setAttribute("token", URLEncoder.encode(token, "UTF-8"));
        request.getRequestDispatcher("view/question.jsp").forward(request, response);    
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
    
    private static Question getQuestion(int questionId) throws SQLException_Exception {
        stackexchangews.services.QuestionHandler_Service service = new stackexchangews.services.QuestionHandler_Service();
        stackexchangews.services.QuestionHandler port = service.getQuestionHandlerPort();
        return port.getQuestion(questionId);
    }
    
    private static List<Answer> getAllAnswers(int questionId) throws SQLException_Exception {
        stackexchangews.services.AnswerHandler_Service service = new stackexchangews.services.AnswerHandler_Service();
        stackexchangews.services.AnswerHandler port = service.getAnswerHandlerPort();
        return port.getAllAnswers(questionId);
    }
    
}
