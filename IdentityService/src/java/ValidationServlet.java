/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import ua_parser.Client;
import ua_parser.Parser;

/**
 *
 * @author Asus
 */
public class ValidationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DBConnection db = new DBConnection();
    Connection conn = db.getConn();
    Statement statement;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();

        try (PrintWriter out = response.getWriter()) {
          String token = request.getParameter("token");
          
          String userAgent = request.getHeader("User-Agent");
          Parser uaParser = new Parser();
          Client c = uaParser.parse(userAgent);
          String ua = c.userAgent.family + "_" + c.os.family + "_" + c.device.family;
                    
          String ipAddress = request.getHeader("X-Forwarded-For");
          if (ipAddress == null) 
            ipAddress = request.getRemoteAddr();
          
          System.out.println(token);
          String[] tokenSplit = token.split("|");
          try {
              Statement stmt = conn.createStatement();
              String sql;
              
              sql = "SELECT * FROM token WHERE value = ?";
              PreparedStatement dbStatement = conn.prepareStatement(sql);
              dbStatement.setString(1, token);

              ResultSet rs = dbStatement.executeQuery();

              if(rs.next()) {
                  Date expirationDate = null;
                  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  try{
                      expirationDate = df.parse(rs.getString("expiration_date"));
                  }
                  catch ( Exception ex ){
                      System.out.println(ex);
                  }

                  Date currentDate = new Date();

                  if (currentDate.after(expirationDate)) {
                      try {
                          obj.put("message", "expired");
                      } catch (JSONException ex) {
                          Logger.getLogger(ValidationServlet.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } else {
                      try {
                          obj.put("message", "valid");
                          String query = "SELECT * from user natural join (SELECT * from token where value ='" + token + "') as t WHERE user.Email = t.user_Email";
                          statement = conn.createStatement();
                          ResultSet rd = statement.executeQuery(query);
                          rd.next();
                          int uid = rd.getInt("User_ID");
                          obj.put("User_ID", uid);
                      
                      } catch (JSONException ex) {
                          Logger.getLogger(ValidationServlet.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }

                  out.print(obj);
              } else if (!tokenSplit[1].equals(ua)) {
                  try {
                      obj.put("message", "invalidagent");
                  } catch (JSONException ex) {
                      Logger.getLogger(ValidationServlet.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  out.print(obj);
              } else if (!tokenSplit[2].equals(ipAddress)) {
                  try {
                      obj.put("message", "invalidip");
                  } catch (JSONException ex) {
                      Logger.getLogger(ValidationServlet.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  out.print(obj);
              } else {
                 try {
                      obj.put("message", "invalid");
                  } catch (JSONException ex) {
                      Logger.getLogger(ValidationServlet.class.getName()).log(Level.SEVERE, null, ex);
                  } 
              }

          } catch(SQLException ex) {  
               ex.printStackTrace();   
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
