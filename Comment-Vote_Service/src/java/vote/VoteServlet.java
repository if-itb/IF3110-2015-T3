/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.json.simple.JSONObject;

/**
 *
 * @author Venny
 */
@WebServlet(name = "VoteServlet", urlPatterns = {"/vote"})
public class VoteServlet extends HttpServlet {
    Connection conn = DB.connect();
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
        response.setContentType("application/json");
        JSONObject jo = new JSONObject();
        
        int id = Integer.parseInt(request.getParameter("id")); //question_id atau answer_id
        String db = request.getParameter("db"); //question atau answer
        
        try(PrintWriter out = response.getWriter()){
            if (db.equals("question")){
                int numvote = getQuestionVotes(id);
                jo.put("vote", numvote);   
            } else if (db.equals("answer")){
                int numvote = getAnswerVotes(id);
                jo.put("vote", numvote);
            }
            out.println(jo.toString());
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
    
    private int getQuestionVotes(int question_id){
        int votecount = 0;
        try {
            String sql = "SELECT vote FROM question WHERE question_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, question_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            votecount = rs.getInt("vote");
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddVoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votecount;
    }
    
    private int getAnswerVotes(int answer_id){
        int votecount = 0;
        try {
            String sql = "SELECT vote FROM answer WHERE answer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, answer_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            votecount = rs.getInt("vote");
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddVoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votecount;
    }
}
