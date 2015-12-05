/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.Database;
import connector.IdentityService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author visat
 */
public class CommentServlet extends HttpServlet {

    private final Connection conn = Database.connection();
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

        String token = request.getParameter("auth");
        String content = request.getParameter("content");
        String sIdQuestion = request.getParameter("id_question");

        if (token == null || content == null || sIdQuestion == null)
            return;

        int idQuestion;
        try {
            idQuestion = Integer.parseInt(sIdQuestion);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return;
        }

        JSONObject result = new JSONObject();
        JSONObject identity = IdentityService.requestAuth(token);
        if (identity != null && identity.containsKey("status")) {
            Long status = (Long) identity.get("status");
            // token has expired
            if (status == -1) {
                result.put("status", -1);
                result.put("detail", "Token has expired");
            }
            // token found
            else if (status == 1 && result.containsKey("id")) {
                boolean success = false;
                long idUser = (long) identity.get("id");
                String query = "INSERT INTO comment (id_question, id_user, content) VALUES (?, ?, ?)";
                try (PreparedStatement insertStatement = conn.prepareStatement(query)) {
                    insertStatement.setInt(1, idQuestion);
                    insertStatement.setInt(2, (int)idUser);
                    insertStatement.setString(3, content);
                    success = insertStatement.executeUpdate() > 0;
                }
                catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                if (success)
                    result.put("status", 1);
                else {
                    result.put("status", 0);
                    result.put("detail", "Failed to create comment");
                }
            }
            // invalid token
            else {
                result.put("status", -1);
                result.put("detail", "Invalid token");
            }
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println(result.toString());
            }
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
