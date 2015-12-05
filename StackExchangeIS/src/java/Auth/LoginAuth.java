/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Ahmad Naufal Farhan
 */
public class LoginAuth extends HttpServlet {

    Connection connAuth = DB.getConnection();
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
        
        JSONObject tokenobj = new JSONObject();
    
        try (PrintWriter out = response.getWriter()) {
      
            String email = request.getParameter("email");
            String password = request.getParameter("password");
    
            // prepare the SQL query and the parameters
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pst = connAuth.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet res = pst.executeQuery();
            
            if (res.next()) {
                // generate token by randomizing big integer
                Random random = new SecureRandom();
                String token = new BigInteger(80, random).toString(32);
                
                String useragent = request.getHeader("User-Agent");
                String ipAddress = request.getHeader("X-FORWARDED-FOR");  
                if (ipAddress == null) {  
                    ipAddress = request.getRemoteAddr();  
                }
                
                token = token + "#" + useragent + "#" + ipAddress;
                
                int uid = res.getInt("uid");
                Timestamp tst = new Timestamp(new Date().getTime());
                tst.setTime(tst.getTime() + ((5 * 60) * 1000));
                
                // delete the older token, replace it with a new one
                query = "DELETE FROM tokens WHERE uid = ?";
                pst = connAuth.prepareStatement(query);
                pst.setInt(1, uid);
                pst.executeUpdate();
          
                // insert the newly created token into database
                query = "INSERT INTO tokens (uid, token_str, lifetime) VALUES (?, ?, ?)";
                pst = connAuth.prepareStatement(query);
                pst.setInt(1, uid);
                pst.setString(2, token);
                pst.setString(3, tst.toString());

                pst.executeUpdate();

                tokenobj.put("success", 1);
                tokenobj.put("user_id", uid);
                tokenobj.put("token_str", token);
                tokenobj.put("lifetime", tst.toString());

            } else {
                tokenobj.put("success", 0);
                tokenobj.put("error", "Invalid credentials!");  
            }
            
            out.print(tokenobj);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(LoginAuth.class.getName()).log(Level.SEVERE, null, ex);
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
        //processRequest(request, response);
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
