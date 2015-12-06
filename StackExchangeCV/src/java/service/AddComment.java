/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;

/**
 *
 * @author User
 */
public class AddComment extends HttpServlet {

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
           
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        
        pw.println("{\"state\": \"no-service\"}");
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
              response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        
        Comment comment = new Comment();
        
        int id_question = Integer.parseInt(request.getParameter("id_question"));
        String content = request.getParameter("content");
        String username = request.getParameter("username");
        String userAgent = request.getParameter("user-agent");
        String ip = request.getParameter("ip");
        Boolean state = comment.addComment(id_question, content, username, userAgent, ip);
        if (state) {
            pw.println("{\"state\": \"failed\"}");
        }
        else {
            pw.println("{\"state\": \"success\"}");
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

}
