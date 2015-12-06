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

// Check the status of the token passed to this servlet
@WebServlet(name = "Auth", urlPatterns = {"/Auth"})
public class Auth extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException, SQLException {
	response.setContentType("text/html;charset=UTF-8");
	
	// Get the tken
	String token = request.getParameter("token");
	String useragent = request.getParameter("user_agent");
        String ip = request.getParameter("ip");
        System.out.println("Auth: " + token + " " + useragent + " " + ip);
	try {
	    Connection conn = ConnectDb.connect();
	    
	    // Try to get the token from the database
	    Statement stmt = null;
	    String sql = "select * from access_token where access_token=? and user_agent = ? and ip = ?";
	    PreparedStatement dbStatement;
	    try {
		dbStatement = conn.prepareStatement(sql);
	    } catch (SQLException ex) {
		Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, token);
            dbStatement.setString(2, useragent);
            dbStatement.setString(3, ip);
	    ResultSet rs = dbStatement.executeQuery();
	    
	    
	    try (PrintWriter out = response.getWriter()) {
		long expirationDate = 0;
		
		// Check if the token exsist in database
		if(rs.next()){
		    // Check if the token has already expired
		    expirationDate = rs.getLong("expiration_date");
		    if (System.currentTimeMillis() / 1000 <= expirationDate) {
			out.println("valid");     //  Not expired yet == Valid
		    }
		    else{
			// Token expired
			
			// Delete the expired token from database
			sql = "delete from access_token where access_token=? and user_agent=? and ip=?";
			dbStatement = conn.prepareStatement(sql);
			dbStatement.setString(1, token);
                        dbStatement.setString(2, useragent);
                        dbStatement.setString(3, ip);
			dbStatement.executeUpdate();
			out.println("expired");
		    }
		}
		else {
		    // The token is not exist in database
		    out.println("invalid");
		}
	    }
	} catch (IOException | SQLException ex) {
	    System.out.println("Error add Token to Database : " + ex);
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
