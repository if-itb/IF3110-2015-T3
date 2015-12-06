/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comment;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "GetComment", urlPatterns = {"/GetComment"})
public class GetComment extends HttpServlet {
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
    
    private String getCommentByAnswerId(int id) {
        String result = null;
        String query = "SELECT * FROM answer_comment WHERE answer_id = " + id;
        JSONObject json = new JSONObject();
        List<JSONObject> list = new ArrayList<JSONObject>();
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            while(rs.next()) {
                JSONObject temp = new JSONObject();
                temp.put("content", rs.getString("comment_content"));
                temp.put("user_name",rs.getString("user_name"));
                list.add(temp);
            }
            rs.close();
            json.put("comment", list);
        } catch (SQLException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeDatabase();
        result = json.toString();
        return result;
    }
    
    private String getCommentByQuestionId(int id) {
        String result = null;
        String query = "SELECT * FROM question_comment WHERE question_id = " + id;
        JSONObject json = new JSONObject();
        List<JSONObject> list = new ArrayList<JSONObject>();
        Database database = new Database();
        database.connect(path);
        ResultSet rs = database.fetchData(query);
        try {
            while(rs.next()) {
                JSONObject temp = new JSONObject();
                temp.put("content", rs.getString("comment_content"));
                temp.put("user_name",rs.getString("user_name"));
                list.add(temp);
            }
            rs.close();
            json.put("comment", list);
        } catch (SQLException ex) {
            Logger.getLogger(AddComment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeDatabase();
        result = json.toString();
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
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String target = request.getParameter("target");
            String result = null;
            switch (target) {
                case "question":
                    result = getCommentByQuestionId(id);
                    break;
                case "answer":
                    result = getCommentByAnswerId(id);
                    break;
                default :
                    result = "target not found";
            }
            response.getWriter().write(result);
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
