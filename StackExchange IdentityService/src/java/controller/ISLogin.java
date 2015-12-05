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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author jessica
 */
public class ISLogin extends HttpServlet {

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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");  
            if (ipAddress == null) {  
                ipAddress = request.getRemoteAddr();  
            }
            String userAgent = request.getHeader("User-Agent");
            
            JSONObject object = new JSONObject();
            
            if (email != null && password != null){
                String sql = "SELECT * FROM user WHERE email = ? AND password = SHA1(?)";
                try (PreparedStatement statement = conn.prepareStatement(sql)){
                    statement.setString(1, email);
                    statement.setString(2, password);
                    ResultSet result = statement.executeQuery();
                    
                    if(result.next()){
                        int u_id = result.getInt("u_id");
                        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                        String token = uuid + "#" + userAgent + "#" + ipAddress;
                        Calendar time = Calendar.getInstance();
                        time.setTime(new Date());
                        time.add(Calendar.HOUR, 2);
                        
                        
                        conn.setAutoCommit(false);
                        
                        String delete = "DELETE from token WHERE u_id = ?";
                        String insert = "INSERT INTO token (access_token, u_id, expiry_date)"
                                + "VALUES (?, ?, ?)";
                        
                        try (
                            PreparedStatement deleteStatement = conn.prepareStatement(delete);
                            PreparedStatement insertStatement = conn.prepareStatement(insert);){
                            
                            deleteStatement.setInt(1, u_id);
                            
                            Timestamp timestamp = new Timestamp(time.getTimeInMillis());
                            insertStatement.setString(1, uuid);
                            insertStatement.setInt(2, u_id);
                            insertStatement.setTimestamp(3, timestamp);
                            
                            deleteStatement.execute();
                            insertStatement.execute();
                            
                            object.put("token", token);
                            object.put("expiry_date", timestamp.getTime());
                            conn.commit();
                        }   
                        finally {
                            conn.setAutoCommit(true);
                        }
                    }
                    
                    else {
                        object.put("error", "Invalid email or password");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                object.put("error", "Empty email or password");
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
