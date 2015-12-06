/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Post;

import Database.DB;
import Validation.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class VoteAnswers extends HttpServlet {

    Connection conn = DB.getConnection();
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VoteAnswers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoteAnswers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int res = 0;
        JSONObject obj = new JSONObject();
        
        try (PrintWriter out = response.getWriter()) { 
            
            int aid = Integer.parseInt(request.getParameter("aid"));
            String query = "SELECT value FROM vote_answers WHERE aid = ?";
            
            // set the prepared statement by the query and enter the value of where clause
            try (PreparedStatement pst = conn.prepareStatement(query)){
                pst.setInt(1, aid);
                // execuResultSette select
                ResultSet ret = pst.executeQuery();
                while (ret.next()) {
                    res += ret.getInt("value");
                }
            }
            
            // put the results
            obj.put("success", 1);
            obj.put("aid", aid);
            obj.put("value", res);
            
            out.print(obj);
                
        } catch (SQLException ex) {
            Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex);
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
        //TODO write your implementation code here:
        String token = request.getParameter("token");
        int aid = Integer.parseInt(request.getParameter("aid"));
        int value = Integer.parseInt(request.getParameter("value"));
        String ipAddress = request.getParameter("ipaddress");
        String useragent = request.getParameter("uagent");
        
        JSONObject obj = new JSONObject();
        
        int res = Validation.AUTH_INVALID;       // initialize result with error first (assumption)
        long user_id = Validation.validateToken(token, ipAddress, useragent); // validate token and get the user id
        
        try (PrintWriter out = response.getWriter()) {
            // token is valid if user_id value is not -1
            if (user_id != -1) {

                try (Statement st = conn.createStatement()) {
                    String query = "SELECT * FROM `vote_answers` WHERE uid = ? AND aid = ?";
                    try (PreparedStatement pstselect = conn.prepareStatement(query)) {
                        pstselect.setLong(1, user_id);
                        pstselect.setInt(2, aid);
                        // execute select
                        ResultSet ret = pstselect.executeQuery();
                        if (!ret.next()) {
                            query = "INSERT INTO `vote_answers` (uid, aid, value) VALUES (?, ?, ?)";

                            // set the prepared statement by the query and enter the value of where clause
                            try (PreparedStatement pst = conn.prepareStatement(query)) {
                                pst.setLong(1, user_id);
                                pst.setInt(2, aid);
                                pst.setInt(3, value);
                                // execute update
                                res = pst.executeUpdate();
                                if (res > 0)
                                    res = Validation.AUTH_VALID;
                            }
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(VoteAnswers.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // else: token is invalid, deny request
                res = Validation.AUTH_INVALID;
            }
            
            obj.put("success", res);
            out.print(obj);
               
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
