/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.DB;
import java.sql.Connection;
import org.json.*;
import java.io.*;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fitra Rahmamuliani
 */
@WebServlet(name="auth", urlPatterns = "/auth")
public class auth extends HttpServlet {

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
        /* KAMUS */
        String token, sql;
        JSONObject jo = new JSONObject();
        ResultSet result;
        Date date, token_expired;  
        DateFormat date_format;
        /* ALGORITMA */
        /* Connect to database */
        Connection conn = DB.connect();
        
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            token = request.getParameter("auth");
            String[] tokeninfo = token.split("#");
            String userAgent = request.getHeader("User-Agent");
            if (!userAgent.equals(tokeninfo[1])) {
                jo.put("status",-2);
            } else {
                String ip = request.getRemoteAddr();
                if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
                {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    String ipAddress = inetAddress.getHostAddress();
                    ip = ipAddress;
                }
                if (!ip.equals(tokeninfo[2])) {
                    jo.put("status",-1);
                } else {
                    sql = "SELECT * FROM token WHERE token_id=?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql))
                    {
                        stmt.setString(1, token);
                        result = stmt.executeQuery();
                        if (result.next())
                        {
                            int uid = result.getInt("user_id");
                            jo.put("id",uid);
                            date = new Date();
                            date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try{
                                token_expired = date_format.parse(result.getString("token_expired"));
                                if (date.after(token_expired))
                                {
                                    jo.put("status",0);
                                }
                                else
                                {
                                    jo.put("status", 1);
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            out.println(jo.toString());
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
