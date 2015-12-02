/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 *
 * @author nim_13512501
 */
public class UserDetails extends HttpServlet {

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
            out.println("<title>Servlet UserDetails</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetails at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request: with Email Field
     * @param response servlet response: if found, XML Email, AuthorName, and an empty AccountPassword Field
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //preprocess
        String Email = "";
        String AuthorName = "";
        String AccountPassword ="";
        if (request.getParameterMap().containsKey("Email")){
            Email=request.getParameter("Email");
        }else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        // JDBC driver name and database URL
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
        final String DB_URL="jdbc:mysql://localhost/stackexchange_db";

        //  Database credentials
        final String USER = "idservices";
        final String PASS = "idservicespwd";
        
        PreparedStatement selstmt = null;
        Connection conn = null;
        
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            String sql;
            
            //insert
            sql = "SELECT * FROM UAccount WHERE Email=?";
            selstmt = conn.prepareStatement(sql);
            selstmt.setString(1, Email);
            ResultSet selRes= selstmt.executeQuery();
            if (selRes.next()){
                Email=selRes.getString("Email");
                AuthorName=selRes.getString("AuthorName");
                AccountPassword="";
                
                response.setContentType("application/xml");
                PrintWriter writer = response.getWriter();
                writer.println("<?xml version=\"1.0\"?>");
                writer.println("<UAccount>");
                writer.println("\t<Email>"+Email+"</Email>");
                writer.println("\t<AuthorName>"+AuthorName+"</AuthorName>");
                writer.println("\t<AccountPassword>"+AccountPassword+"</AccountPassword>");
                writer.println("</UAccount>");
            }else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        }catch (SQLException se){
            se.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }finally{
            
            try{
                //close connection
                if (selstmt != null) {
                    selstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                //nothing we can do
            }

        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request: Email, AuthorName, AccountPassword
     * @param response servlet response: 201 on creation success, 409 on email already existed, 400 on bad request
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //preprocess request
        String Email = "";
        String AuthorName = "";
        String AccountPassword ="";
        if (request.getParameterMap().containsKey("Email")
                && request.getParameterMap().containsKey("AuthorName")
                && request.getParameterMap().containsKey("AccountPassword")){
            Email=request.getParameter("Email");
            AuthorName = request.getParameter("AuthorName");
            AccountPassword = request.getParameter("AccountPassword");
        }else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        // JDBC driver name and database URL
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
        final String DB_URL="jdbc:mysql://localhost/stackexchange_db";

        //  Database credentials
        final String USER = "idservices";
        final String PASS = "idservicespwd";
        
        PreparedStatement selstmt = null;
        PreparedStatement insstmt = null;
        Connection conn = null;
        
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            String sql;
            
            //insert
            sql = "SELECT * FROM UAccount WHERE Email=?";
            selstmt = conn.prepareStatement(sql);
            selstmt.setString(1, Email);
            ResultSet selRes= selstmt.executeQuery();
            if (!selRes.next()){
                //insert
                sql = "INSERT INTO UAccount(Email,AuthorName,AccountPassword) Values"
                        + "(?,?,?)";
                insstmt = conn.prepareStatement(sql);
                insstmt.setString(1, Email);
                insstmt.setString(2, AuthorName);
                insstmt.setString(3, AccountPassword);
                insstmt.executeUpdate();
                response.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        }catch (SQLException se){
            se.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }finally{
            
            try{
                //close connection
                if (selstmt != null) {
                    selstmt.close();
                }

                if (insstmt != null){
                    insstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                //nothing we can do
            }

        }

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
