/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author jessica
 */
public class ISAuth extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection conn = DatabaseController.connect();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject object = new JSONObject();
        
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String token = request.getParameter("token");
            String sql = "SELECT * FROM token WHERE access_token = ?";
            String ipAddress = request.getHeader("X-FORWARDED-FOR");  
            if (ipAddress == null) {  
                ipAddress = request.getRemoteAddr();  
            }
            String userAgent = request.getHeader("User-Agent");
            String[] parsedToken = token.split("[#]");
            
            
            try (PreparedStatement statement = conn.prepareStatement(sql)){
               statement.setString(1, token);
               
               ResultSet result = statement.executeQuery();
               
               if(result.next()){
                   Date now = new Date();
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   
                   try {
                       
                       Date expiry_date = format.parse(result.getString("expiry_date"));
                       // Mengecek user agent dan ip address token
                       if(!userAgent.equals(parsedToken[1]) || !ipAddress.equals(parsedToken[2])){
                           object.put("error", "User agent and/or IP Address is incorrect");
                       }
                       //Mengecek apakah token masih berlaku
                       else if (now.after(expiry_date)){
                           object.put("error", "Expired Token");
                           String deleteQuery = "DELETE FROM token WHERE access_token = ?";
                           try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)){
                               deleteStatement.setString(1, token);
                               deleteStatement.execute();
                           }
                       }
                       else {
                           object.put("id", result.getInt("u_id"));
                           
                       }
                   }
                   catch(SQLException | ParseException e){
                       object.put("error", "SQL Exception");
                   }
                   
               }
            }
            catch (SQLException e){
                object.put("error", "SQL Exception");
            }
            out.println(object.toString());
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
