/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author Asus
 */
public class AuthRSServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            /*final PrintWriter out = response.getWriter();
             StringBuffer jb = new StringBuffer();
             String line = null;
             try {
             BufferedReader reader = request.getReader();
             while ((line = reader.readLine()) != null)
             jb.append(line);
             } catch (Exception e) {}
             out.println(jb.toString());*/
            response.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE sessions SET ExpiredDate=NOW()+INTERVAL 5 MINUTE WHERE AccessToken = ?"; // Login query validation

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, request.getParameter("token"));
            dbStatement.executeUpdate();
            //response.sendRedirect(request.getHeader("referer")+"&ret=true");
        } catch (SQLException ex) {

            Logger.getLogger(AuthRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(AuthRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {}
    
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
        Connection conn = null;
        String currentEmail = null;
        Timestamp exp = null;
        String Name = null;
        StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) {}
            //System.out.println(jb.toString());
        try {
            
            JSONParser parser = new JSONParser();
            
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            String token = (String) obj.get("access_token");
            
            response.setContentType("application/json;charset=UTF-8");
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            sql = "SELECT Email, ExpiredDate FROM sessions WHERE AccessToken = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);
            ResultSet rsEmail = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan get email
            if(rsEmail.next()) {
                //returnExecution = returnExecution + 1;
                currentEmail = rsEmail.getString("Email");
                exp = rsEmail.getTimestamp("ExpiredDate");
            }
            
            sql = "SELECT Name FROM users WHERE Email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, currentEmail);
            ResultSet rsName = dbStatement.executeQuery();
            if(rsName.next()) {
                Name = rsName.getString("Name");
            }
            PrintWriter out = response.getWriter();
            JSONObject objOut = new JSONObject();
            objOut.put("name", Name);
            objOut.put("email", currentEmail);
            objOut.put("expireddate", exp.toString());
            out.println(objOut);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AuthRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AuthRSServlet.class.getName()).log(Level.SEVERE, null, ex);
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
