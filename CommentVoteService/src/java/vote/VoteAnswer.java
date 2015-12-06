/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import model.VoteAnswerModel;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "VoteAnswer", urlPatterns = {"/voteanswer"})
public class VoteAnswer extends HttpServlet {

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
	if (request.getMethod().equals("POST")) {
	    try (PrintWriter out = response.getWriter()) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		String token = request.getParameter("token");
		int value = Integer.parseInt(request.getParameter("value"));
		int userid = Integer.parseInt(request.getParameter("userid"));

		String tokenStatus = isValidToken(token, this.getUserAgent(request), this.getIP(request)).trim();
		if (null != tokenStatus) {
		    VoteAnswerModel vqm = new VoteAnswerModel(userid, aid, value);
		    if (vqm.addToDatabase().equals("sukses")) {
			System.out.println("HASIL : " + vqm.toXML());
			out.println(vqm.toXML());
		    } else {
			out.println("Error");
		    }
		}
	    }
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
	    Logger.getLogger(VoteAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
	    Logger.getLogger(VoteAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String getUserAgent(HttpServletRequest request) {
	return request.getHeader("User-Agent");
    }

    public String getIP(HttpServletRequest request) {
	return request.getRemoteAddr();
    }

    private String isValidToken(String token, String useragent, String ip) {
	Form form = new Form();
	form.param("token", token);
	form.param("user_agent", useragent);
	form.param("ip", ip);

	Client client = ClientBuilder.newClient();
	String url = "http://localhost:8080/IdentityService/Auth";

	String valid = client.target(url).request(MediaType.TEXT_PLAIN)
		.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
	return valid;
    }
}
