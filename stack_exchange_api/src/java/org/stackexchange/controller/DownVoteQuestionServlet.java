package org.stackexchange.controller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.stackexchange.webservice.dao.QuestionDao;
import org.stackexchange.webservice.dao.QuestionVoteDao;
import org.stackexchange.webservice.service.TokenService;


/**
 *
 * @author X450
 */
public class DownVoteQuestionServlet extends HttpServlet {

  

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DownVoteQuestionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownVoteQuestionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String url = request.getServletPath();
        String from = request.getParameter("from");
        String token = request.getParameter("token");
        //request.setAttribute("curr",baseURL);
        if (token != null && !token.isEmpty()) {
            if (from.equals("index")){
                downvote(Long.valueOf(request.getParameter("question_id")),request.getParameter("token"));
                response.sendRedirect("/stack_exchange_netbeans/index?token=" + request.getParameter("token")+ "&from=" +from);
            }
            else{
                downvote(Long.valueOf(request.getParameter("question_id")),request.getParameter("token"));
                response.sendRedirect("/stack_exchange_netbeans/question?question_id=" + request.getParameter("question_id") + "&token=" + request.getParameter("token")+ "&from=" +from);
            }
        }
        else{
            response.sendRedirect("http://localhost:7000/login");
        }
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

    public boolean downvote(long id, String token) {
        TokenService tokenService = new TokenService();
        QuestionDao questionDao = new QuestionDao();
        QuestionVoteDao questionVoteDao = new QuestionVoteDao();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
                    
            if (questionVoteDao.existsByQuestionIdUserId(id, userId)) {
                long vote = questionVoteDao.getVoteCountByQuestionIdUserId(id, userId);
                
                if (vote == 1) {
                    questionDao.downvote(id);
                    questionDao.downvote(id);
                } else if (vote == 0) {
                    questionDao.downvote(id);
                }
                questionVoteDao.update(id, userId, -1);
            } else {
                questionVoteDao.insert(id, userId, -1);
                questionDao.downvote(id);
            }
            
            return true;
        } else {
            return false;
        }
    }

}
