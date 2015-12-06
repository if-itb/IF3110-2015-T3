/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.token.validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

public class CheckToken extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String token = request.getParameter("token");
        String[] datadb = null,data = token.split("#");
        Long produced = 0l;
        String tokenFlag = "invalid"; //initiate tokenFlag with invalid
        try (PrintWriter out = response.getWriter()) {
            //create connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            String sql = "SELECT * FROM tokens";
            java.sql.PreparedStatement dbs = conn.prepareStatement(sql);
            ResultSet rs = dbs.executeQuery();
            //search same token in database
            while(rs.next() && tokenFlag.equals("invalid")) {
                //assumption : delete token in token table when logout
                String tokendb = rs.getString("token");
                datadb = tokendb.split("#");
                if(data[0].equals(datadb[0])) {
                    produced = rs.getLong("produced");
                    tokenFlag = "valid";
                }
            }
            if(tokenFlag.equals("valid")) {
                Calendar calobj = Calendar.getInstance();
                long time = calobj.getTimeInMillis()/1000;
                //check user-agent, ip_addr, lifetime
                if(!data[1].equals(datadb[1])) 
                    tokenFlag = "false-agent"; 
                else if(!data[2].equals(datadb[2]))
                    tokenFlag = "false-ipaddr"; 
                else if(time>produced)
                    tokenFlag = "expired";
            }
            JSONObject jobj = new JSONObject();
            jobj.put("message", tokenFlag);
            out.println(jobj.toString());
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckToken.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckToken.class.getName()).log(Level.SEVERE, null, ex);
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
