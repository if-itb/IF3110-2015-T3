/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stackexchange.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;
import model.Question;
import org.stackexchange.webservice.dao.CommentDao;
import org.stackexchange.webservice.dao.QuestionDao;
import org.stackexchange.webservice.dao.UserDao;
import org.stackexchange.webservice.service.TokenService;

/**
 *
 * @author Alex
 */
public class CommentServlet extends HttpServlet {

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
            out.println("<title>Servlet CommentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String questionId = request.getParameter("question_id");
        String token = request.getParameter("token");
        List<Comment> commentList = getByQuestionId(Long.valueOf(questionId));
       
        request.setAttribute("CList",commentList);
        request.setAttribute("token",token);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        //processRequest(request, response);
        long questionId = Long.valueOf(request.getParameter("question_id"));
        String content = request.getParameter("content");
        System.out.println(content);
        String token = request.getParameter("token");
        if (token != null && !token.isEmpty()) {
            insert(content,token,questionId);
            response.sendRedirect("http://localhost:8080/stack_exchange_netbeans/index?token="+token);
            //request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        } else {
            request.setAttribute("flash", "You Need To Login First");
            request.getRequestDispatcher("http://localhost:7000/login").forward(request, response);
        }
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
    
    public boolean insert(String content, String token, long questionId) {
        TokenService tokenService = new TokenService();
        if (tokenService.isTokenValid(token)) {
            long userId = tokenService.getUserId(token);
            CommentDao commentDao = new CommentDao();
            Comment comment = commentDao.insert(userId, questionId, content);
            return true;
        } else {
            return false;
        }
    }
    
    public List<Comment> getByQuestionId(long questionId) {
        CommentDao commentDao = new CommentDao();
        UserDao userDao = new UserDao();

        ArrayList<Comment> commentList = (ArrayList) commentDao.getByQuestionId(questionId);
        for (Comment comment: commentList) {
            comment.setName(userDao.getById(comment.getUserId()).getName());
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(commentList);
        
        
        Type listType = new TypeToken<List<Comment>>() {}.getType();
        List<Comment> commentListFromJson = gson.fromJson(json, listType);
        
        return commentListFromJson;
    }
}
