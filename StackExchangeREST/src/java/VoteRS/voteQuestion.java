/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VoteRS;

import Auth.Auth;
import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Vanji
 */
public class voteQuestion extends HttpServlet {

    
    DB db = new DB();
    Connection conn = db.connect();
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
        response.setContentType("text/html;charset=UTF-8");
        JSONObject obj = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            String qid = request.getParameter("qid");            
            int v_count = 0;
            try {
                Statement stmt = conn.createStatement();
                String sql;

                sql = "SELECT SUM(v_count) v_count FROM `vote` WHERE q_id = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, Integer.parseInt(qid));

                ResultSet rs = dbStatement.executeQuery();

                while(rs.next()) {
                    v_count = rs.getInt("v_count");
                }

                obj.put("qid", qid);
                obj.put("v_count", v_count);   
                out.print(obj);   

                stmt.close();
            } catch(SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);   
            }
        }
        
    }
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();
    
        try (PrintWriter out = response.getWriter()) {
      
        String qid = request.getParameter("qid");
        String uid = request.getParameter("uid");
        String vcount = request.getParameter("v_count");
        String token = request.getParameter("token");

        Auth auth = new Auth();
        int ret = auth.validation(token);

        if ( ret == 1 ) {
            try {
                int count = 0;
                int v_count = 0;

                Statement stmt = conn.createStatement();
                String sql;
                PreparedStatement dbStatement;

                sql = "SELECT * FROM vote WHERE u_id = ? AND q_id = ?";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, Integer.parseInt(uid));
                dbStatement.setInt(2, Integer.parseInt(qid));

                ResultSet rs = dbStatement.executeQuery();

                // Extract data from result set
                while(rs.next()){        
                  ++count;
                }

                if ( count == 0 ) {
                    // Insert Vote Baru
                    sql = "INSERT INTO vote (u_id, q_id, a_id, v_count) VALUES (?, ?, 0, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(uid));
                    dbStatement.setInt(2, Integer.parseInt(qid));
                    dbStatement.setInt(3, Integer.parseInt(vcount));

                    dbStatement.executeUpdate();
                    
                    // Dapatkan Jumlah Vote Question Setelah Insert
                    sql = "SELECT SUM(v_count) v_count FROM `vote` WHERE q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(qid));

                    rs = dbStatement.executeQuery();

                    while(rs.next()) {
                        v_count = rs.getInt("v_count");
                    }
                    
                    // Update Jumlah Vote Question
                    sql = "UPDATE answer SET q_vote = ? WHERE q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, v_count);
                    dbStatement.setInt(2, Integer.parseInt(qid));
                    
                    dbStatement.executeUpdate();
                    
                    
                } else {
                    // Update Vote
                    sql = "UPDATE vote SET v_count = ? WHERE u_id = ? AND q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(vcount));
                    dbStatement.setInt(2, Integer.parseInt(uid));
                    dbStatement.setInt(3, Integer.parseInt(qid));

                    dbStatement.executeUpdate();
                    
                    // Dapatkan Jumlah Vote Question Setelah Update
                    sql = "SELECT SUM(v_count) v_count FROM `vote` WHERE q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(qid));

                    rs = dbStatement.executeQuery();

                    while(rs.next()) {
                        v_count = rs.getInt("v_count");
                    }
                    
                    // Update Jumlah Vote Question
                    sql = "UPDATE answer SET q_vote = ? WHERE q_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, v_count);
                    dbStatement.setInt(2, Integer.parseInt(qid));
                    
                    dbStatement.executeUpdate();
                    
                }

                obj.put("error", "success");  
                out.print(obj);   

                stmt.close();
            } catch(SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);    
            }
          } else {
            obj.put("error", "validation");  
            out.print(obj);  
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
