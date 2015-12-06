/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.comment;

import com.dazzlesquad.DBConnect.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ryanyonata
 */
public class GetAllCommentsByQID extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DBConnect db = new DBConnect();
    Connection conn = db.connect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int question_id = Integer.parseInt(request.getParameter("qid"));
        response.setContentType("application/json");
        JSONArray comments = new JSONArray();
        
        try (PrintWriter out = response.getWriter()) {
            //ArrayList<JSONObject> comments = new ArrayList<JSONObject>();
        
            try {
                Statement statement = conn.createStatement();
                String sql;
                sql = "SELECT * FROM comment WHERE question_id = ? ORDER BY id DESC";
                
                PreparedStatement dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                dbStatement.setInt(1,question_id);
                ResultSet result = dbStatement.executeQuery();

                while(result.next()) {
                    JSONObject each_comment = new JSONObject();
                    each_comment.put("id", result.getInt("id"));
                    each_comment.put("user_id", result.getInt("user_id"));
                    each_comment.put("question_id", result.getInt("question_id"));
                    each_comment.put("content", result.getString("content"));
                    each_comment.put("date", result.getString("date"));

                    comments.add(each_comment);
                }

                result.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(GetAllCommentsByQID.class.getName()).log
                (Level.SEVERE, null, ex);
            }
        }
        
        request.setAttribute("comments", comments);
        String location= "/Stack_Exchange_Client/QuestionPage?id=" + question_id;
        response.sendRedirect(location);
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
    
}
