/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comment;

import database.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author davidkwan
 */
@WebServlet(name = "GetComments", urlPatterns = {"/GetComments"})
public class GetComments extends HttpServlet {
    private static Connection conn = null;
    
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
        
        conn = ConnectionManager.getInstance().getConnection();
        
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();

        int questionId = Integer.parseInt(request.getParameter("questionId"));
        
        try {
            String sql = "SELECT * FROM comment, user WHERE questionId = ? AND commenterId = userId;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, questionId);
            ResultSet rs = pstmt.executeQuery();
            
            JSONArray ja = new JSONArray();
            while(rs.next()){
                JSONObject comment = new JSONObject();
                comment.put("email", rs.getString("email"));
                comment.put("content", rs.getString("content"));
                ja.add(comment);
            }
            obj.put("comments", ja);
            
            try (PrintWriter out = response.getWriter()){
                out.print(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GetComments.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConnectionManager.getInstance().close();
        
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


}
