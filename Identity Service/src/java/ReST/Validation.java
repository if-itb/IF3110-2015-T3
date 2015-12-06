/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReST;

import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus
 */
public class Validation extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
    
  DB db = new DB();
  Connection conn = db.connect();
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("application/json");
    JSONObject obj = new JSONObject();
    
    try (PrintWriter out = response.getWriter()) {
      String token = request.getParameter("token");
      String userIP = URLEncoder.encode(request.getParameter("userIP"), "UTF-8");
      String userAgent = URLEncoder.encode(request.getParameter("userAgent"), "UTF-8");
      
      String userIdentifier = userIP + userAgent;//URLEncoder.encode(userIP + userAgent, "UTF-8");

      if (!token.equals(null) && token.length() > userIdentifier.length()) {
          String checker = token.substring(token.length()-userIdentifier.length(), token.length());
          if (checker.equals(userIdentifier)) {
            try {
                Statement stmt = conn.createStatement();
                String sql;

                sql = "SELECT * FROM access_token WHERE token = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, token);

                ResultSet rs = dbStatement.executeQuery();

                if(rs.next()) {
                    Date expirationDate = null;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        expirationDate = df.parse(rs.getString("expire_date"));
                    }
                    catch ( Exception ex ){
                        System.out.println(ex);
                    }

                    Date currentDate = new Date();

                    if (currentDate.after(expirationDate)) {
                        obj.put("message", "expired");
                    } else {
                        obj.put("message", "valid");
                    }

                    out.print(obj);
                } else {
                    obj.put("message", "invalid");
                    out.print(obj);
                }

            } catch(SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);
            }
          }
          else {
              String message = "";
              if (checker.substring(checker.length()-userIP.length(), checker.length()).equals(userIP)) {
                  message += "IP";
              }
              if (checker.substring(checker.length()-userAgent.length(), checker.length()).equals(userAgent)) {
                  message += "UA";
              }
              obj.put("message", message);
              out.print(obj);
          }
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
    //processRequest(request, response);
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
