/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.ISConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Comment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Tifani
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {
    
    Connection conn = DatabaseController.connect();

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
        response.setContentType("application/json");
        
        int qId = Integer.parseInt(request.getParameter("q_id"));
        
        ArrayList<Comment> commentList = getComments(qId);
        
        JSONArray comments = new JSONArray();
        for (Comment comment: commentList) {
            JSONObject object = new JSONObject();
            object.put("c_id", comment.getcId());
            object.put("q_id", comment.getqId());
            object.put("u_id", comment.getuId());
            object.put("content", comment.getContent());
            object.put("date_created", comment.getDateCreated());
            comments.add(object);
        }
        
        try(PrintWriter out = response.getWriter()) {
            out.print(comments.toString());
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
        
        int qId = Integer.parseInt(request.getParameter("q_id"));
        String content = request.getParameter("content");
        String token = request.getParameter("token");
        
        if (content != null && token != null) {
            JSONObject result = new JSONObject();
            int uId = ISConnector.validateToken(token);
            // Auth failed
            if (uId==-1) {
                result.put("status", -1);
                result.put("message", "Invalid token");
            } else {
                int status = addComment(qId, uId, content);
                result.put("status", status);
                if (status==0) result.put("message", "Error");
                else result.put("message", "Success");
            }
            try(PrintWriter out = response.getWriter()) {
                out.print(result.toString());
            }
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
    
    private ArrayList<Comment> getComments(int qId) {
        ArrayList<Comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comment WHERE q_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, qId);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                comments.add(new Comment(
                        result.getInt("c_id"),
                        result.getInt("q_id"),
                        result.getInt("u_id"),
                        result.getString("content"),
                        result.getString("date_created")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    private int addComment(int qId, int uId, String content) {
        int status = 0;
        String query = "INSERT INTO comment (q_id, u_id, content, date_created) VALUES (?, ?, ?, now())";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, qId);
            stmt.setInt(1, uId);
            stmt.setString(3, content);
            stmt.executeUpdate();
            status = 1;
        } catch (SQLException e) {
            
        }
        return status;
    }

}
