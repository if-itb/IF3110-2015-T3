/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vote;

import Auth.Auth;
import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus
 */
public class Answer extends HttpServlet {

  /* Connecting to Database */
  /* MANDATORY */
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
  protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("application/json");
    JSONObject obj = new JSONObject();
    
    try (PrintWriter out = response.getWriter()) {
      
      String aid = request.getParameter("aid");
      
      int vote_count = 0;
      try {
          Statement stmt = conn.createStatement();
          String sql;

          sql = "SELECT SUM(value) vote_count FROM `vote_answer` WHERE id_answer = ?";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, Integer.parseInt(aid));

          ResultSet rs = dbStatement.executeQuery();

          while(rs.next()) {
              vote_count = rs.getInt("vote_count");
          }
          
          obj.put("aid", aid);
          obj.put("vote_count", vote_count);   
          out.print(obj);   
          
          stmt.close();
      } catch(SQLException ex) {
          obj.put("error", ex);  
          out.print(obj);   
      }
    }
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
  protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("application/json");
    JSONObject obj = new JSONObject();
    
    try (PrintWriter out = response.getWriter()) {
      
      String aid = request.getParameter("aid");
      String uid = request.getParameter("uid");
      String value = request.getParameter("value");
      String token = request.getParameter("token");
      
      Auth auth = new Auth();
      int ret = auth.check(token);
      
      if ( ret == 1 ) {
        try {
            int count = 0;

            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;

            sql = "SELECT * FROM vote_answer WHERE id_user = ? AND id_answer = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, Integer.parseInt(uid));
            dbStatement.setInt(2, Integer.parseInt(aid));

            ResultSet rs = dbStatement.executeQuery();

            // Extract data from result set
            while(rs.next()){        
              ++count;
            }

            if ( count == 0 ) {
              sql = "INSERT INTO vote_answer (id_user, id_answer, value) VALUES (?, ?, ?)";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, Integer.parseInt(uid));
              dbStatement.setInt(2, Integer.parseInt(aid));
              dbStatement.setInt(3, Integer.parseInt(value));

              dbStatement.executeUpdate();
            } else {
              sql = "UPDATE vote_answer SET value = ? WHERE id_user = ? AND id_answer = ?";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, Integer.parseInt(value));
              dbStatement.setInt(2, Integer.parseInt(uid));
              dbStatement.setInt(3, Integer.parseInt(aid));

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
    processGetRequest(request, response);
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
    processPostRequest(request, response);
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
