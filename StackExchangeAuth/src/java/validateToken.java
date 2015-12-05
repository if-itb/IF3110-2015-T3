/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/validateToken"})
public class validateToken extends HttpServlet {
    
    private static String getCurrentTimeStamp() {
        Calendar cal = Calendar.getInstance();  
	Timestamp now = new Timestamp(cal.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now); 
    }
    
    private Boolean isValid(String date_create, int valid_hour) {
        
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(date_create);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            long msDC = timestamp.getTime();
            
            Date parsedDateNow = dateFormat.parse(getCurrentTimeStamp());
            Timestamp timestampNow = new java.sql.Timestamp(parsedDateNow.getTime());
            long msNow = timestampNow.getTime();
            
            long diffHours = ((msNow - msDC) / (1000));       
            if (diffHours < (long) (valid_hour * 60 * 60)) 
                return true;
            
        }catch(Exception e){   
             
        }
        
        return false;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String token_string = request.getParameter("token_string");
        
        PrintWriter tw = response.getWriter();
        
        DB db = new DB();
        
        int valid_hour = 0;
        String date_create = null;
        String username = null;
        
        try {
            Connection conn = db.connect();
            
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT date_create,valid_hour,username FROM token where token_string = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token_string);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                valid_hour = rs.getInt("valid_hour");
                date_create = rs.getString("date_create");
                username = rs.getString("username");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
        }
        
        if (valid_hour != 0 && token_string != null) {
            tw.println("<tokenModel>");
            tw.println("<date_create>" + date_create + "</date_create>");
            tw.println("<valid_hour>" + valid_hour + "</valid_hour>");
            tw.println("<status>" + isValid(date_create, valid_hour) + "</status>");
            tw.println("<username>" + username + "</username>");
            tw.println("</tokenModel>");
        }
        else {
            tw.println("<tokenModel><status>false</status></tokenModel>");
        }
        tw.close();
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
