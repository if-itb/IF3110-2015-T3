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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Adz
 */
public class ISLoginServlet extends HttpServlet {

    Connection conn = DB.connect();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {                                
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            // get parameter from client
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            JSONObject object = new JSONObject();
            if (email != null && password != null) {                
                // is user exists in database
                String sql = "SELECT * FROM user WHERE email = ? AND password = MD5(?)";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, email);  
                    statement.setString(2, password); 
                    ResultSet result = statement.executeQuery();                    
                    if (result.next()) {                                
                        int user_id = result.getInt("id");                                                
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.DATE, 1);
                        Timestamp expire_date = new Timestamp(calendar.getTime().getTime());
                        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                        conn.setAutoCommit(false);
                        
                        // only 1 token from 1 user is allowed
                        String deleteQuery = "DELETE from token WHERE user_id = ?";
                        String insertQuery = "INSERT INTO token (access_token, user_id, expire_date) VALUES (?, ?, ?)";
                        try (
                            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                            PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
                            String userAgent = request.getHeader("User-Agent");
                            String clientIP = request.getHeader("Remote-Address");
                            String auth = uuid + "#" +
                                    (userAgent == null? "" : userAgent) + "#" +
                                    (clientIP == null? "" : clientIP);
                            deleteStatement.setInt(1, user_id);                            
                            insertStatement.setString(1, auth);
                            insertStatement.setInt(2, user_id);
                            insertStatement.setTimestamp(3, expire_date);                            
                            deleteStatement.execute();
                            insertStatement.execute();                                                                                
                            object.put("auth", uuid);
                            object.put("expire_date", expire_date.getTime());
                            conn.commit();                            
                        }
                        finally {
                            conn.setAutoCommit(true);
                        }                        
                    }        
                    else {
                        object.put("error", "Invalid username or password");
                    }
                }                        
            }                                         
            out.println(object.toString());
        }
        catch(SQLException ex){
            Logger.getLogger(ISLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ISLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ISLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
