/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReST;

import Database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author erickchandra
 */
@WebServlet(name = "Validation", urlPatterns = {"/Validation"})
public class Validation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Database db = new Database();
    Connection conn = Database.connect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();
        
        try (PrintWriter out = response.getWriter()) {
            String token = request.getParameter("token");
            String[] strSplit;
            
            try {
                Statement stmt = conn.createStatement();
                String sql;
                
                // Prepare for SQL statement
                sql = "SELECT * FROM tokenlist WHERE token = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, token);
                
                // Execute the SQL
                ResultSet rs = dbStatement.executeQuery();
                
                if (rs.next()) {
                    int userId = rs.getInt("userId");
                     
                    Date expDate = null;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        expDate = df.parse(rs.getString("expdate"));
                    }
                    catch (SQLException | ParseException e) {
                        
                    }
                    
                    Date currentDate = new Date();
                    
                    // Additional check for token
                    strSplit = token.split("#");
                    
                    if (strSplit[1].equals(request.getRemoteAddr()) && strSplit[2].equals(request.getHeader("user-agent")) && currentDate.after(expDate)) {
                        obj.put("result", "expired");
                    }
                    else {
                        obj.put("result", "valid");
                        obj.put("userId", userId);
                    }
                    
                    out.print(obj);
                }
                else {
                    obj.put("message", "invalid");
                    out.print(obj);
                }
            }
            catch (SQLException e) {
                obj.put("error", e);
                out.print(obj);
            }
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
