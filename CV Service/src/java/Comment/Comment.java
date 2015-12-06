/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comment;

import Auth.Auth;
import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus
 */
public class Comment extends HttpServlet {

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
    JSONArray ret = new JSONArray();
    
    try (PrintWriter out = response.getWriter()) {
      
      String qid = request.getParameter("qid");
      
      try {      
        Statement stmt = conn.createStatement();
        String sql;

        sql = "SELECT comments.id, id_question, content, name FROM "
          + "  (SELECT * FROM comment_question WHERE id_question = ?) AS comments "
          + "   LEFT JOIN "
          + "  (SELECT id, name FROM users) AS user "
          + "   ON comments.id_user = user.id";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setString(1, qid);

        ResultSet rs = dbStatement.executeQuery();
        if(rs.next()){

          do {
            JSONObject obj = new JSONObject();
            obj.put("id", rs.getInt("id"));
            obj.put("q_id", rs.getInt("id_question"));
            obj.put("user", rs.getString("name"));
            obj.put("content", rs.getString("content"));

            ret.add(obj);
          } while(rs.next());
           
          out.print(ret); 
        } else {
          JSONObject obj = new JSONObject();
          obj.put("error", "no result");
          ret.add(obj);
          out.print(ret);        
        }
        stmt.close();
      } catch (SQLException ex) {
        JSONObject obj = new JSONObject();
        obj.put("error", ex);  
        ret.add(obj);
        out.print(ret);        
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
      
      String qid = request.getParameter("qid");
      String uid = request.getParameter("uid");
      String content = request.getParameter("content");
      String token = request.getParameter("token");
      String userIP = request.getParameter("userIP");
      String userAgent = request.getParameter("userAgent");
      
      Auth auth = new Auth();
      int ret = auth.check(token,userIP,userAgent);
      
      if ( ret == 1 ) {
        try {      
          Statement stmt = conn.createStatement();
          String sql;

          sql = "INSERT INTO comment_question (id_question, id_user, content) VALUES (?, ?, ?)";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, Integer.valueOf(qid));
          dbStatement.setInt(2, Integer.valueOf(uid));
          dbStatement.setString(3, content);

          dbStatement.executeUpdate();

          stmt.close();
          obj.put("error", "success"); 
          out.print(obj);       

        } catch (SQLException ex) {
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
