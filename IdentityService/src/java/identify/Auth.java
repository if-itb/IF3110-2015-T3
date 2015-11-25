/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identify;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.ConnectDb;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "Auth", urlPatterns = {"/Auth"})
public class Auth extends HttpServlet {

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
	    throws ServletException, IOException, SQLException {
	response.setContentType("text/html;charset=UTF-8");
	
	String token = request.getParameter("token");
	
	try {
	    Connection conn = ConnectDb.connect();
	    Statement stmt = null;
	    String sql = "select * from access_token where access_token=?";
	    PreparedStatement dbStatement;
	    try {
		dbStatement = conn.prepareStatement(sql);
	    } catch (SQLException ex) {
		Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, token);
	    ResultSet rs = dbStatement.executeQuery();
	    try (PrintWriter out = response.getWriter()) {
		long expirationDate = 0;
		if(rs.next()){
		    System.out.println("In Database");
		    expirationDate = rs.getLong("expiration_date");
		    if (System.currentTimeMillis() / 1000 <= expirationDate) {
			System.out.println("Auth : Valid");
			out.println("valid");
		    }
		    else{
			System.out.println("Auth : Expire");
			sql = "delete  from access_token where access_token=?";

			dbStatement = conn.prepareStatement(sql);

			dbStatement = conn.prepareStatement(sql);
			dbStatement.setString(1, token);

			dbStatement.executeUpdate();
			out.println("Expired");
		    }
		}
		else {
		    System.out.println("Auth : Invalid");
		    out.println("invalid");
		}
	    
		
	    }
	} catch (IOException | SQLException ex) {
	    System.out.println("Error add Token : " + ex);
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
	    Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
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
	    Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
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
