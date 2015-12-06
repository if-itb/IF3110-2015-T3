/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.commentvote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import stackexchange.webservice.auth.Auth;
import stackexchange.webservice.util.Database;

/**
 *
 * @author fauzanrifqy
 */
@WebServlet(name = "initComment", urlPatterns = {"/initComment"})
public class initComment extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("application/json");
            //Get Data
            String qid = request.getParameter("qid");
            //Akses database menggunakan query DB
            Database db = new Database();
            Connection conn = db.getConnection();
            try{
                String sql="select * from qcomments where questionId=" + qid;

                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                JSONObject json = new JSONObject();
                JSONArray commentsJSON = new JSONArray();

                while(rs.next()){
                    JSONObject commentJSON = new JSONObject();

                    commentJSON.put("id", rs.getInt("id"));
                    commentJSON.put("name", rs.getString("name"));
                    commentJSON.put("email", rs.getString("email"));
                    commentJSON.put("content", rs.getString("content"));
                    commentJSON.put("dateMade", "'"+rs.getTimestamp("dateMade")+"'");

                    commentsJSON.add(commentJSON);
                }
                json.put("status", "success");
                json.put("comments", commentsJSON);
                out.print(json);
                out.flush();
            }catch(Exception e){
                JSONObject json = new JSONObject();

                json.put("status", e.getMessage());
                out.print(json);
                out.flush();
            }finally{
                db.closeConnection();
                db = null;
            }
        } finally {
            out.close();
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
