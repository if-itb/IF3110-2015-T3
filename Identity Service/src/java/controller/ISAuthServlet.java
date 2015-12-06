/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Acer
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class ISAuthServlet extends HttpServlet {

    private final Connection conn = DB.connect();
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
        response.setContentType("application/json");        
        try (PrintWriter out = response.getWriter()) {            
            String token = request.getParameter("auth");
            String query = "SELECT * FROM token WHERE access_token = ?";
            JSONObject obj = new JSONObject();
            if (token != null) try (PreparedStatement statement = conn.prepareStatement(query)) {
                String userAgent = request.getHeader("User-Agent");
                String remoteAddr = request.getHeader("Remote-Address");
                token = token + "#" + (userAgent == null? "": userAgent) + "#" +
                        (remoteAddr == null? "" : remoteAddr);
                System.out.println("From Auth: " + token);
                statement.setString(1, token);

                ResultSet result = statement.executeQuery();
                if(result.next()) {
                    Date current_date = new Date();                    
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date expire_date = format.parse(result.getString("expire_date"));
                        if (current_date.after(expire_date)) {
                            obj.put("status", -1);
                            query = "DELETE FROM token WHERE access_token = ?";
                            try (PreparedStatement deleteStatement = conn.prepareStatement(query)) {
                                deleteStatement.setString(1, token);
                                deleteStatement.executeUpdate();
                            }
                        }
                        else {
                            obj.put("status", 1);
                            obj.put("id", result.getInt("user_id"));
                        }
                    }
                    catch ( SQLException | ParseException ex ){
                        System.err.println(ex.getMessage());
                    }                                                            
                }
                else {
                    obj.put("status", 0);
                }
            }
            out.println(obj);            
        }
        catch(SQLException ex) {               
            System.err.println(ex.getMessage());
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
