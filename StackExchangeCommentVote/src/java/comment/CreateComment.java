/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comment;

import database.ConnectionManager;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import stackexchange.ISConnector.IdentityServiceConnector;

/**
 *
 * @author davidkwan
 */
@WebServlet(name = "CreateComment", urlPatterns = {"/CreateComment"})
public class CreateComment extends HttpServlet {
    
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

        String token = request.getParameter("token");
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String comment = request.getParameter("comment");
        
        System.out.println(token);
        System.out.println(questionId);
        System.out.println(comment);
        
        // int userId = IdentityServiceConnector.getUID(token);
        int userId = 1;
        
        if(userId >= 0){
            try {
                String sql = "INSERT INTO comment(questionId, commenterId, content) VALUES(?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                pstmt.setInt(1, questionId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, comment);
                pstmt.executeUpdate();
                
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreateComment.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            obj.put("status", "success");
            
        }
        else{
            obj.put("status", "unauthorized");
        }
        out.print(obj);
            
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
