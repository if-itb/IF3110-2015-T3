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
import java.io.FileWriter;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.json.simple.*;
import java.security.SecureRandom;
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
    static String access_token = "";
    static String access_ua = "";
    static String access_ip = "";
    static String browser = "";
    
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
        String encodedUrl = URLEncoder.encode(access_token, "UTF-8");
        System.out.println("MASUK do GET testrestjava");
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
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
            preparedStmt.setString(1, encodedUrl);
        
            ResultSet result = preparedStmt.executeQuery();
   
                result.first();
                java.util.Date lifetime = result.getTimestamp("t_time");
                java.util.Date now = new java.util.Date();
                int comp = lifetime.compareTo(now);
 
            if(comp == -1){
            out.println("expired");
            response.sendRedirect("http://localhost:8082/WBD_IS/login.jsp?value=expired");
            }
            else if (comp == 1){
            out.println("valid");
            query = "UPDATE token SET t_time = now() + INTERVAL 1 MINUTE WHERE t_token = ?";
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, encodedUrl);
            }
            else{
            out.println("kosong");
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
              System.out.println("MASUK do POST testrestjava");
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
            String query = "SELECT u_id,email,name,password FROM user WHERE email = ? AND password = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, request.getParameter("username"));
            preparedStmt.setString(2, request.getParameter("password"));
            ResultSet result = preparedStmt.executeQuery();
            int i = 0;
            if (result.first() == false){
                String login = "http://localhost:8082/WBD_IS/login.jsp";
                request.setAttribute("access_token", access_token);
                //request.getRequestDispatcher(login).forward(request, response);
                access_token = "";
                response.sendRedirect(login);
            }
            else {
                   user.add(result.getString("u_id"));
                   userid = result.getString("u_id");
                   user.add(result.getString("name"));
                   user.add(result.getString("email"));
                   user.add(result.getString("password"));
                  // SecureRandom random = new SecureRandom();
                  // String access_token_full = new BigInteger(130, random).toString(32);
                  // access_token = access_token_full.substring(0, 7);
                   access_token= UUID.randomUUID().toString();
                   access_ua=request.getHeader("user-agent");
                   access_ip=request.getHeader("X-FORWARDED-FOR");
                    if (access_ip == null || access_ip.length() == 0 || "unknown".equalsIgnoreCase(access_ip)){
                        access_ip = request.getHeader("Proxy-Client-IP");
                    }
                    if (access_ip == null || access_ip.length() == 0 || "unknown".equalsIgnoreCase(access_ip)){
                        access_ip = request.getHeader("WL-Proxy-Client-IP");
                    }
                    if (access_ip == null || access_ip.length() == 0 || "unknown".equalsIgnoreCase(access_ip)){
                        access_ip = request.getHeader("HTTP_Client_IP");
                    }
                    if (access_ip == null || access_ip.length() == 0 || "unknown".equalsIgnoreCase(access_ip)){
                        access_ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                    }
                    if (access_ip == null || access_ip.length() == 0 || "unknown".equalsIgnoreCase(access_ip)){
                        access_ip = request.getRemoteAddr();
                    }
                    access_token = access_token + "#" + access_ua + "#" + access_ip;
                    String encodedUrl = URLEncoder.encode(access_token, "UTF-8");
                    System.out.println("Encoded URL = "+encodedUrl);
                   //COOKIE
                   Cookie token = new Cookie("token",encodedUrl);
                   System.out.println("Token gt Value : "+token.getValue());
                   token.setMaxAge(6*60*60);
                   token.setPath("/");
                   response.addCookie(token);
                   Cookie[] cookieArray = request.getCookies();
                     System.out.println(cookieArray.length);
                   if(cookieArray != null){
                    for (int j=0; j<cookieArray.length;j++){
                        if(cookieArray[j].getName().equals("token")){
                            System.out.println(cookieArray[j].getValue());
                        }
                    }
                   }
                   
                    HttpSession session = request.getSession();
                    query = "REPLACE into token (u_id,t_token,t_time) VALUES (? , ?, now() + INTERVAL 1 MINUTE) ";
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1,userid);
                    preparedStmt.setString(2,encodedUrl);
                    response.sendRedirect("http://localhost:8080/StackExchangeFE/homepagelogin.jsp");
                    
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
