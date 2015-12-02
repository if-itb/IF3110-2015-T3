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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author nim_13512501
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    private static final int defaultlifetime=600; //harus diganti nanti. ini sedikit cuma buat ngetes
    private static Map<String,AccessToken> tokens = new ConcurrentHashMap<String,AccessToken>();
    private static Map<String,String> emails=new ConcurrentHashMap<String,String>();
    private static Map<String,Calendar> expirations=new ConcurrentHashMap<String,Calendar>();

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
        
            System.out.println("check");
            System.out.println(request.getParameter("user_agent"));
            System.out.println(request.getParameter("user_ip"));
        //preproses
        String access_token=null;
        if (request.getParameterMap().containsKey("access_token")
                && request.getParameterMap().containsKey("user_agent")
                && request.getParameterMap().containsKey("user_ip")){
            access_token=request.getParameter("access_token");
            if (emails.containsKey(access_token) && expirations.containsKey(access_token)
                    &&tokens.containsKey(access_token)){
                AccessToken checkAccessToken = tokens.get(access_token);
                                
                if (Calendar.getInstance().before(expirations.get(access_token))
                        &&request.getParameter("user_agent").equals(checkAccessToken.getUser_agent())
                        &&request.getParameter("user_ip").equals(checkAccessToken.getUser_ip())){
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
        String user_agent = "";
        String user_ip = "";
        if (request.getParameterMap().containsKey("Email")
                && request.getParameterMap().containsKey("AccountPassword")
                && request.getParameterMap().containsKey("user_agent")
                && request.getParameterMap().containsKey("user_ip")){
            Email=request.getParameter("Email");
            AccountPassword=request.getParameter("AccountPassword");
            user_agent = request.getParameter("user_agent");
            user_ip = request.getParameter("user_ip");
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
                    AccessToken this_AccessToken = null;
                    do{
                        this_AccessToken=new AccessToken(user_agent,user_ip);
                    }while (emails.containsKey(this_AccessToken));
                    tokens.put(this_AccessToken.toString(),this_AccessToken);
                    emails.put(this_AccessToken.toString(), Email);
                    Calendar expiration = Calendar.getInstance();
                    expiration.add(Calendar.SECOND, defaultlifetime);
                    expirations.put(this_AccessToken.toString(), expiration);
                    response.setHeader("Location", "?access_token="+this_AccessToken);
                    response.setContentType("application/xml");
                    PrintWriter writer=response.getWriter();
                    writer.println("<?xml version=\"1.0\"?>");
                    writer.println("<response>");
                    writer.println("<access_token>"+this_AccessToken+"</access_token>");
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
    
    /** Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response XML access_token,
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        if (request.getParameterMap().containsKey("access_token")
                && request.getParameterMap().containsKey("user_agent")
                && request.getParameterMap().containsKey("user_ip")){
            String access_token=request.getParameter("access_token");
            AccessToken checkAccessToken = tokens.get(access_token);
            if (checkAccessToken!=null)                    
            if (emails.containsKey(access_token) && expirations.containsKey(access_token)
                        &&request.getParameter("user_agent").equals(checkAccessToken.getUser_agent())
                        &&request.getParameter("user_ip").equals(checkAccessToken.getUser_ip())){
                emails.remove(access_token);
                expirations.remove(access_token);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
