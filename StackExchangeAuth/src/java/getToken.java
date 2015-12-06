/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.DB;
import Token.TokenModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/getToken"})
public class getToken extends HttpServlet {
    
    private static String getCurrentTimeStamp() {
        Calendar cal = Calendar.getInstance();  
	Timestamp now = new Timestamp(cal.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now); 
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
        response.setContentType("application/xml;charset=UTF-8");
        
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String password = "";
        String userAgent = request.getHeader("user-agent");
        String ip = request.getHeader("X-FORWARDED-FOR");
        if(ip==null){
            ip=request.getRemoteAddr();
        }
//        String userAgent = request.getParameter("user-agent");
//        String ip = request.getParameter("ip");
        
        PrintWriter tw = response.getWriter();
        
        DB db = new DB();
        
        if (uname.equals("") || pass.equals("")) {
            tw.println("<tokenModel>" 
                    +   "<token>Failed</token>"        
                    +  "</tokenModel>");
            return;
        }
        try {
            
            Connection conn = db.connect();
            
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT password FROM account where username = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, uname);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                password = rs.getString("password");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
        }
        
        TokenModel token;
        
        if (password.equals(pass)) {
            
            try {
                
                token = new TokenModel(userAgent,ip);
                String tkn = token.getToken();
                
                Connection conn = db.connect();
                
                Statement stmt;
                stmt = conn.createStatement();
            
                String sql;
                sql = "INSERT INTO token(token_string, date_create, username)" + " VALUES (?, ?, ?)";
            
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, tkn); 
                dbStatement.setString(2, getCurrentTimeStamp());
                dbStatement.setString(3, uname);
            
                dbStatement.execute();
            
                stmt.close();
                conn.close();
                
                tw.println("<tokenModel>" 
                    +   "<token>"+ tkn +"</token>"        
                    +  "</tokenModel>");
                tw.close();
            }
            catch(SQLException ex) {
            }
            
            tw.println("<tokenModel>" 
                    +   "<token>Failed</token>"        
                    +  "</tokenModel>");
        }
        else {
            tw.println("<tokenModel>" 
                    +   "<token>Failed</token>"        
                    +  "</tokenModel>");
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
