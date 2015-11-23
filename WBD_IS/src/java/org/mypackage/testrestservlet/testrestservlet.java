/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.testrestservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpSession;
/**
 *
 * @author user
 */
@WebServlet(name = "testrestservlet", urlPatterns = {"/testrestservlet"})
public class testrestservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String access_token = "";
    
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
        final PrintWriter out = response.getWriter();
         ArrayList<String> user = new ArrayList<String>();
         Timestamp ts = null;
        out.println("GET method (retrieving data) was invoked!");
        String MyURL = "jdbc:mysql://localhost:3306/wbd?zeroDateTimeBehavior=convertToNull";
        try {
            String temp_token = "2c57b801-105e-4c11-a983-5811bf08d00b";
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(MyURL,userName,password);
            String query = "SELECT t_time FROM token WHERE t_token = ?";
            out.println("GET method (retrieving data) was invoked 1!");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "2c57b801-105e-4c11-a983-5811bf08d00b");
            out.println("GET method (retrieving data) was invoked 2!");
            ResultSet result = preparedStmt.executeQuery();
            out.println("GET method (retrieving data) was invoked 3!");
           // out.println("RESULT = "+result);
            Date date = result.getTimestamp("t_time");
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            out.println("TIMESTAMP = "+dateString);
            //out.println("TIMESTAMP = "+ts);
            //result.getDate("t_time");
            //out.println(result.getDate("t_time").toString());
           //user.add(result.getTimestamp("t_time").toString());
            out.println("GET method (retrieving data) was invoked 4!");
            out.println("Waktu Token "+temp_token);
            out.println("GET method (retrieving data) was invoked 5!");
            out.println("= "+user.toString());
            out.println("GET method (retrieving data) was invoked 6!");
            /*
            if (result.first() == false){
                String login = "http://localhost:8082/WBD_IS/login.jsp";
                request.setAttribute("access_token", access_token);
                //request.getRequestDispatcher(login).forward(request, response);
                out.println("Username / Password tidak match");
                access_token = "";
                response.sendRedirect(login);
            }
            else {
                   
                   user.add(result.getString("name"));
                   user.add(result.getString("email"));
                   user.add(result.getString("password"));
                   access_token= UUID.randomUUID().toString();
                    out.println("Berhasil Login = "+user.toString());
                    out.println("Token = "+access_token);
                    HttpSession session = request.getSession();
                    out.println("MASUK SINI 1");
                    query = "INSERT INTO token (u_email,t_token,t_time) VALUES (? , ?, now() + INTERVAL 1 MINUTE)";
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1,request.getParameter("username"));
                    preparedStmt.setString(2,access_token);
                    out.println("MASUK SINI 1");
                    out.println("Waktu Pembuatan Sesi : "+session.getCreationTime());
                    
               
            }
            */
            
            //execute prepared statement
            preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(testrestservlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testrestservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        //processRequest(request, response);
      final PrintWriter out = response.getWriter();
      ArrayList<String> user = new ArrayList<String>();
      Random rand = new Random();
      
      out.println(request.getParameter("username"));
      out.println(request.getParameter("password"));
      String MyURL = "jdbc:mysql://localhost:3306/wbd?zeroDateTimeBehavior=convertToNull";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(MyURL,userName,password);
            String query = "SELECT email,name,password FROM user WHERE email = ? AND password = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, request.getParameter("username"));
            preparedStmt.setString(2, request.getParameter("password"));
            ResultSet result = preparedStmt.executeQuery();
            int i = 0;
            if (result.first() == false){
                String login = "http://localhost:8082/WBD_IS/login.jsp";
                request.setAttribute("access_token", access_token);
                //request.getRequestDispatcher(login).forward(request, response);
                out.println("Username / Password tidak match");
                access_token = "";
                response.sendRedirect(login);
            }
            else {
                   
                   user.add(result.getString("name"));
                   user.add(result.getString("email"));
                   user.add(result.getString("password"));
                   access_token= UUID.randomUUID().toString();
                    out.println("Berhasil Login = "+user.toString());
                    out.println("Token = "+access_token);
                    HttpSession session = request.getSession();
                    out.println("MASUK SINI 1");
                    query = "INSERT INTO token (u_email,t_token,t_time) VALUES (? , ?, now() + INTERVAL 1 MINUTE)";
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1,request.getParameter("username"));
                    preparedStmt.setString(2,access_token);
                    out.println("MASUK SINI 1");
                    out.println("Waktu Pembuatan Sesi : "+session.getCreationTime());
                    
                    
            }
            
            
            //execute prepared statement
            preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(testrestservlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testrestservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
    * Servlet method responding to HTTP PUT methods calls.
    *
    * @param request HTTP request.
    * @param response HTTP response.
    */
   @Override
   public void doPut( HttpServletRequest request,
                      HttpServletResponse response ) throws IOException
   {
      final PrintWriter out = response.getWriter();
      out.write("PUT method (inserting data) was invoked!");
   }
   
   /**
    * Servlet method responding to HTTP DELETE methods calls.
    *
    * @param request HTTP request.
    * @param response HTTP response.
    */
   @Override
   public void doDelete( HttpServletRequest request,
                         HttpServletResponse response ) throws IOException
   {
      final PrintWriter out = response.getWriter();
      out.write("DELETE method (removing data) was invoked!");
   }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Server-side application demonstrating HTTP methods.";
    }// </editor-fold>

}
