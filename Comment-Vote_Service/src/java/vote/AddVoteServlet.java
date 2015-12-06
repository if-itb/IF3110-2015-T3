/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import ConnectionIS.ConnectionIS;
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
@WebServlet(name = "AddVoteServlet", urlPatterns = {"/addVote"})
public class AddVoteServlet extends HttpServlet {
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
        int vote = Integer.parseInt(request.getParameter("vote")); //jumlah vote
        String db = request.getParameter("db"); //question atau answer
        String token = request.getParameter("token"); //token_id
        
        // cek token ke IS untuk dapetin user ID
        JSONObject auth = ConnectionIS.requestAuth(token);
        int uid = (int)(long)auth.get("id");
        int status = (int)(long)auth.get("status");
        
        try(PrintWriter out = response.getWriter()){
            if (status == 1) {
                if (db.equals("question")){ // vote question
                    if (hasVotedQuestion(id,uid)){ // user telah vote question sebelumnya
                        jo.put("ERROR", "User have already voted");
                    } else {
                        try {
                            // update jml vote di tabel question
                            String sql = "UPDATE question SET vote = vote+? WHERE question_id = ?";
                            PreparedStatement stmt1 = conn.prepareStatement(sql);
                            stmt1.setInt(1, vote);
                            stmt1.setInt(2, id);
                            int status1 = stmt1.executeUpdate();
                            stmt1.close();
                            if (status1>0) {
                                // insert id & uid ke tabel vote_question
                                sql = "INSERT INTO vote_question (question_id, user_id) VALUES (?,?)";
                                PreparedStatement stmt2 = conn.prepareStatement(sql);
                                stmt2.setInt(1, id);
                                stmt2.setInt(2, uid);
                                int status2 = stmt2.executeUpdate();
                                stmt2.close();
                                if (status2>0) {
                                    int numvote = getQuestionVotes(id);
                                    jo.put("vote", numvote);
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AddVoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (db.equals("answer")){ // vote answer
                    if (hasVotedAnswer(id,uid)){ // user telah vote answer sebelumnya
                        jo.put("ERROR", "User have already voted");
                    } else {
                        try {
                            // undate jml vote di tabel answer
                            String sql = "UPDATE answer SET vote = vote+? WHERE answer_id = ?";
                            PreparedStatement stmt1 = conn.prepareStatement(sql);
                            stmt1.setInt(1, vote);
                            stmt1.setInt(2, id);
                            int status1 = stmt1.executeUpdate();
                            stmt1.close();
                            if (status1>0) {
                                // insert id & uid ke tabel vote_answer
                                sql = "INSERT INTO vote_answer (answer_id, user_id) VALUES (?,?)";
                                PreparedStatement stmt2 = conn.prepareStatement(sql);
                                stmt2.setInt(1, id);
                                stmt2.setInt(2, uid);
                                int status2 = stmt2.executeUpdate();
                                stmt2.close();
                                if (status2>0){
                                    int numvote = getAnswerVotes(id);
                                    jo.put("vote",numvote);
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AddVoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                jo.put("ERROR","Token expired");
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
    
    private boolean hasVotedQuestion (int question_id, int user_id){
        boolean voted = false;
        try {
            String sql = "SELECT count(*) AS vote FROM vote_question WHERE question_id = ? AND user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, question_id);
            stmt.setInt(2, user_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.getInt("vote")!=0) voted = true;
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddVoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voted;
    }
    
    private boolean hasVotedAnswer(int answer_id, int user_id){
        boolean voted = false;
        try {
            String sql = "SELECT count(*) AS vote FROM vote_answer WHERE answer_id = ? AND user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, answer_id);
            stmt.setInt(2, user_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.getInt("vote")!=0) voted = true;
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddVoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voted;
    }
    
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
