package ReST;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Database.Database;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author erickchandra
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Database db = new Database();
    Connection conn = db.connect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();

        try (PrintWriter out = response.getWriter()) {

          String email = request.getParameter("email");
          String password = request.getParameter("password");

          try {      
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, email);
            dbStatement.setString(2, password);

            ResultSet rs = dbStatement.executeQuery();
            if(rs.next()){
              Random rand = new Random();
              int randomNum = rand.nextInt((1000 - 1) + 1) + 1;

              String token = email + Integer.toString(randomNum);

              Calendar date = Calendar.getInstance();
              long t = date.getTimeInMillis();
              Date expirationDate = new Date(t + (5 * 60000));

              DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

              sql = "REPLACE INTO tokenlist (userId, token, expdate, ipaddr, uagent) VALUES (?, ?, ?, ?, ?)";
              dbStatement = conn.prepareStatement(sql);
              dbStatement.setInt(1, rs.getInt("userId"));
              dbStatement.setString(2, token);
              dbStatement.setString(3, df.format(expirationDate));
              dbStatement.setString(4, request.getRemoteAddr());
              dbStatement.setString(5, request.getHeader("user-agent"));

              dbStatement.executeUpdate();

              stmt.close();
              
              System.out.println(token);
              obj.put("token", token);
              out.print(obj);

            } else {
              obj.put("token", "");  
              out.print(obj);        
            }
          } catch (SQLException ex) {
              obj.put("error", ex);  
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
