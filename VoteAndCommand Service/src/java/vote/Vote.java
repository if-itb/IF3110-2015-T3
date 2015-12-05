/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author William Sentosa
 */
@WebServlet(name = "Vote", urlPatterns = {"/Vote"})
public class Vote extends HttpServlet {
    /* Atribut */
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
    
    private String incrVoteQuestion(int id, int userId) throws SQLException {
        String sql = "SELECT * FROM question_vote WHERE question_id = " + id + " AND user_id = " + userId;
        String result = "not executed";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(sql);
        if(!rs.next()) {
            String query = "UPDATE question SET question_vote = question_vote + 1 WHERE question_id= " + id;
            result = database.changeData(query);
            query = "INSERT INTO question_vote (user_id, question_id) VALUES (" + userId + ", " + id + ")";
            result = database.changeData(query);
        }
        rs.close();
        database.closeDatabase();
        return result;
    }
    
    private String decrVoteQuestion(int id, int userId) throws SQLException {
        String sql = "SELECT * FROM question_vote WHERE question_id = " + id + " AND user_id = " + userId;
        String result = "not executed";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(sql);
        if(!rs.next()) {
            String query = "UPDATE question SET question_vote = question_vote - 1 WHERE question_id= " + id;
            result = database.changeData(query);
            query = "INSERT INTO question_vote (user_id, question_id) VALUES (" + userId + ", " + id + ")";
            result = database.changeData(query);
        }
        rs.close();
        database.closeDatabase();
        return result;
    }
    
    private String incrVoteAnswer(int id, int userId) throws SQLException {
        String sql = "SELECT * FROM answer_vote WHERE answer_id = " + id + " AND user_id = " + userId;
        String result = "not executed";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(sql);
        if(!rs.next()) {
            String query = "UPDATE answer SET answer_vote = answer_vote + 1 WHERE answer_id = " + id;
            result = database.changeData(query);
            query = "INSERT INTO answer_vote (user_id, answer_id) VALUES (" + userId + ", " + id + ")";
            result = database.changeData(query);
        }
        rs.close();
        database.closeDatabase();
        return result;
    }
    
    private String decrVoteAnswer(int id, int userId) throws SQLException {
        String sql = "SELECT * FROM answer_vote WHERE answer_id = " + id + " AND user_id = " + userId;
        String result = "not executed";
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(sql);
        if(!rs.next()) {
            String query = "UPDATE answer SET answer_vote = answer_vote - 1 WHERE answer_id = " + id;
            result = database.changeData(query);
            query = "INSERT INTO answer_vote (user_id, answer_id) VALUES (" + userId + ", " + id + ")";
            result = database.changeData(query);
        }
        rs.close();
        database.closeDatabase();
        return result;
    }
    
    private int getAnswerVoteCount(int id) throws SQLException {
        int result;
        String query = "SELECT * FROM answer WHERE answer_id = " + id;
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        rs.next();
        result = rs.getInt("answer_vote");
        rs.close();
        database.closeDatabase();
        return result;
    }
    
    private int getQuestionVoteCount(int id) throws SQLException {
        int result;
        String query = "SELECT * FROM question WHERE question_id = " + id;
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        rs.next();
        result = rs.getInt("question_vote");
        rs.close();
        database.closeDatabase();
        return result;
    }
    
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
        response.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            String target = request.getParameter("target");
            out.println(target);
            String action = request.getParameter("action");
            out.println(action);
            int id = Integer.parseInt(request.getParameter("id"));
            out.println(id);
            int userId = Integer.parseInt(request.getParameter("userId"));
            out.println(userId);
            try {
                if(target.compareTo("answer") == 0) {
                    switch (action) {
                        case "incr" :
                            incrVoteAnswer(id, userId);
                            break;
                        case "decr" :
                            decrVoteAnswer(id, userId);
                            break;
                        default :
                            response.sendError(1, "Action not valid");
                    }
                    json.put("answer_vote", this.getAnswerVoteCount(id));
                } else if (target.compareTo("question") == 0) {
                    switch (action) {
                        case "incr" :
                            out.println(incrVoteQuestion(id, userId));
                            break;
                        case "decr" :
                            decrVoteQuestion(id, userId);
                            break;
                        default :
                            response.sendError(1, "Action not valid");
                    }
                    out.println("Test2");
                    json.put("question_vote", this.getQuestionVoteCount(id));
                } else {
                    response.sendError(1, "Target not valid");
                }
            } catch (SQLException ex) {
                out.println("gagal");
                Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                out.println("Gagal");
                Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.getWriter().write(json.toString());
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
