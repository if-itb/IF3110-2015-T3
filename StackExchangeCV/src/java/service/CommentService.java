/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;

/**
 *
 * @author User
 */
public class CommentService extends HttpServlet {
    
    private class RestRequest {
        // Accommodate two requests, one for all resources, another for a specific resource
        private Pattern regExAllPattern = Pattern.compile("/resource");
        private Pattern regExIdPattern = Pattern.compile("/resource/([0-9]*)");
        private Integer id;

        public RestRequest(String pathInfo) throws ServletException {
            // regex parse pathInfo
            Matcher matcher;

            // Check for ID case first, since the All pattern would also match
            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                return;
            }

            throw new ServletException("Invalid URI");
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

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
        
        Comment comment = new Comment();
        
        try {
            RestRequest resourceValues = new RestRequest(request.getPathInfo());
            if (resourceValues.getId() == null) {
                pw.println(comment.getComments());
            }
            else {
                pw.println(comment.getCommentByQID(resourceValues.getId()));
            }
            
        } catch (ServletException e) {
            response.setStatus(400);
            response.resetBuffer();
            pw.println("{\"state\": \"exception\", \"what\": \"" + e.toString() + "\"}");
        }
        
        pw.close();
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
        
        pw.println("{\"state\": \"no-service\"}");
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
