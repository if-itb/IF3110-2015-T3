/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.testrestservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mypackage.testrestservlet.testrestservlet.access_token;
import org.json.simple.*;

/**
 *
 * @author User
 */
@WebServlet(name = "comment", urlPatterns = {"/comment"})
public class comment extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet comment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet comment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        System.out.println("Masuk Get");
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
        JSONObject obj = new JSONObject();
        obj.put("name","Steven");
        obj.put("age","20");
        obj.put("comment","KOMENTAR");
        System.out.println("HALO");
        System.out.println(obj);
    
        /*request.getParameter("komentar");
                    System.out.println("MASUK do POST comment");
        //processRequest(request, response);
      final PrintWriter out = response.getWriter();
      ArrayList<String> user = new ArrayList<String>();
      String userid;
      String MyURL = "jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(MyURL,userName,password);
                   access_token= UUID.randomUUID().toString();
                    HttpSession session = request.getSession();                   
                    String query = "REPLACE into comment (q_id,u_id,comment) VALUES ( ?, ?, ?)";                   
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, 1);
                    preparedStmt.setInt(2, 1);
                    preparedStmt.setString(3,"Ben");
                    
                    //response.sendRedirect("http://localhost:8080/StackExchangeFE/homepagelogin.jsp?token="+access_token);
            
            
            //execute prepared statement
            preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(testrestservlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testrestservlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
