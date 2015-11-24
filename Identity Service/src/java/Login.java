/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Calendar;
import java.util.UUID;

/**
 *
 * @author nim_13512501
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    private static final int defaultlifetime=600; //harus diganti nanti. ini sedikit cuma buat ngetes
    private static Map<UUID,String> emails=new ConcurrentHashMap<UUID,String>();
    private static Map<UUID,Calendar> expirations=new ConcurrentHashMap<UUID,Calendar>();

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
        //preproses
        UUID access_token=null;
        if (request.getParameterMap().containsKey("access_token")){
            access_token=UUID.fromString(request.getParameter("access_token"));
            if (emails.containsKey(access_token) && expirations.containsKey(access_token)){
                if (Calendar.getInstance().before(expirations.get(access_token))){
                    response.setContentType("application/xml");
                    PrintWriter writer=response.getWriter();
                    writer.println("<?xml version=\"1.0\"?>");
                    writer.println("<Email>"+emails.get(access_token)+"</Email>");
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response XML access_token,
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //preprocess
        String Email = "";
        String AccountPassword = "";
        if (request.getParameterMap().containsKey("Email")
                && request.getParameterMap().containsKey("AccountPassword")){
            Email=request.getParameter("Email");
            AccountPassword=request.getParameter("AccountPassword");
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
            sql = "SELECT AccountPassword FROM UAccount WHERE Email=?";
            selstmt = conn.prepareStatement(sql);
            selstmt.setString(1, Email);
            ResultSet selRes= selstmt.executeQuery();
            if (selRes.next()){
                if (AccountPassword.equals(selRes.getString("AccountPassword"))){
                    UUID this_UUID = null;
                    do{
                        this_UUID=UUID.randomUUID();
                    }while (emails.containsKey(this_UUID));
                    emails.put(this_UUID, Email);
                    Calendar expiration = Calendar.getInstance();
                    expiration.add(Calendar.SECOND, defaultlifetime);
                    expirations.put(this_UUID, expiration);
                    response.setHeader("Location", "?access_token="+this_UUID);
                    response.setContentType("application/xml");
                    PrintWriter writer=response.getWriter();
                    writer.println("<?xml version=\"1.0\"?>");
                    writer.println("<response>");
                    writer.println("<access_token>"+this_UUID+"</access_token>");
                    writer.println("<lifetime>"+defaultlifetime+"</lifetime>");
                    writer.println("</response>");
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
