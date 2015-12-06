/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.VCConnector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {
    
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
        String questionId = request.getParameter("id");
        if (questionId == null) {
            return;
        }
        
        int id;
        try {
            id = Integer.parseInt(questionId);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return;
        }
        
        try (PrintWriter out = response.getWriter()) {            
            response.setContentType("application/json");            
            out.println(VCConnector.getComments(questionId));
        }
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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonResponse = new JSONObject();
            String questionId = request.getParameter("id");
            String content = request.getParameter("content");
            if (questionId != null && content!= null) {                
                try {
                    Cookie cookies[] = request.getCookies();
                    String auth = null;
                    for (Cookie cookie: cookies) {
                        if (cookie.getName().equals("auth")) {
                            auth = cookie.getValue();
                            break;
                        }
                    }
                    if (auth != null) {
                        String userAgent = request.getHeader("User-Agent");
                        String remoteAddr = request.getRemoteAddr();
                        if (userAgent == null) userAgent = "";
                        if (remoteAddr == null) remoteAddr = "";
                        content = content.toLowerCase();
                        auth = auth + "#" + userAgent + "#" + remoteAddr;
                        jsonResponse = VCConnector.requestComment(auth, questionId, content);
                    }
                }
                catch (NumberFormatException ex) {
                }
            }
            out.write(jsonResponse.toString());
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
