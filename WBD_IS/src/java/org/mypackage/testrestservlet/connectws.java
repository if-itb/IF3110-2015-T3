/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.testrestservlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import static org.mypackage.testrestservlet.testrestservlet.access_token;

/**
 *
 * @author user
 */
@WebServlet(name = "connectws", urlPatterns = {"/connectws"})
public class connectws extends HttpServlet {
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
        out.println();
        out.println("GET method (retrieving data) was invoked!");
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
      System.out.println("MASUK do POST connectws");
        //processRequest(request, response);
        final PrintWriter out = response.getWriter();
         ArrayList<String> user = new ArrayList<String>();
        String MyURL = "jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull";
        try {
            String temp_token = access_token;
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(MyURL,userName,password);
            String query = "SELECT t_time FROM token WHERE t_token = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, temp_token);
        
            ResultSet result = preparedStmt.executeQuery();
   
                result.first();
                java.util.Date lifetime = result.getTimestamp("t_time");
                java.util.Date now = new java.util.Date();
                out.println("Token:"+temp_token);
                out.println("lifetime:" +lifetime );
                out.println("now:" + now);
                int comp = lifetime.compareTo(now);
                out.println("Comparison:"+comp);
            if(comp == -1){
            out.println("Expired");
            JSONObject json = new JSONObject();
            json.put("message","expired");
            json.put("token",temp_token);
            out.println(json.toString());
            try{
                FileWriter file = new FileWriter("C:\\xampp\\htdocs\\validation.json");
                file.write(json.toJSONString());
                file.flush();
                file.close();
                out.println("MSUK SINI");
            }
            catch (IOException e){
                e.printStackTrace();
            }
            
            response.sendRedirect("http://localhost:8082/WBD_IS/login.jsp");
            }
            else{
            out.println("Valid");
            query = "UPDATE token SET t_time = now() + INTERVAL 1 MINUTE WHERE t_token = ?";
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, temp_token);
            out.println(temp_token);
            out.println("Masuk Refresh Token");
            JSONObject json = new JSONObject();
            json.put("message","valid");
            json.put("token",temp_token);
            out.println(json.toString());
            try{
                FileWriter file = new FileWriter("C:\\xampp\\htdocs\\validation.json");
                file.write(json.toJSONString());
                file.flush();
                file.close();
                out.println("MSUK SINI");
            }
            catch (IOException e){
                e.printStackTrace();
            }
            
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
