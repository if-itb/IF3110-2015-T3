package comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import model.CommentModel;
import mysql.ConnectDb;

@WebServlet(urlPatterns = {"/comment"})
public class Comment extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException, SQLException {	
	String token = request.getParameter("token");
	String content = request.getParameter("content");
	String name = request.getParameter("name");
	String qid = request.getParameter("qid");

	System.out.println("Token : " + token);
	System.out.println("content : " + content);
	System.out.println("Name : " + name);
	System.out.println("qid : " + qid);
	
	if(request.getMethod().equals("POST")){
	    System.out.println("POST COMMENT");
	    try {
		PrintWriter out = response.getWriter();

		String result = isValidToken(token, this.getUserAgent(request), this.getIP(request));
		if ("UserNotFound".equals(result.trim())) {
		    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    // Redirect to the login page with error message
		    request.setAttribute("errorMessage", "Invalid Email / Password !");
		    request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		String tokenStatus = isValidToken(token, this.getUserAgent(request), this.getIP(request)).trim();
		if (null != tokenStatus) {
		    switch (tokenStatus) {
			case "valid":
			    System.out.println("VALID TOKEN");
			    CommentModel cm = new CommentModel(name, content, Integer.parseInt(qid));
			    cm.addToDatabase();
			    out.println(cm.toXML());
			    break;
			case "invalid":
			    System.out.println("INVALID TOKEN");
			    out.println("invalidtoken");
			    break;
			default:
			    System.out.println("EXPIRED_TOKEN");
			    out.println("expiredtoken");
		    }
		}
	    } catch (ServletException | IOException ex) {
		request.setAttribute("errorMessage", ex.getMessage());
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	    }
	} else if(request.getMethod().equals("GET")) {
	    System.out.println("GET COMMENT");
	    Connection conn = ConnectDb.connect();
	    String sql = "select * from comments where qid = ? ";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement.setInt(1, Integer.parseInt(qid));
	    ResultSet res = dbStatement.executeQuery();
	    
	    String hasil = "";
	    String nama = "";
	    String konten = "";
	    String tanggal= "";
	    int qId = 0;
	    
	    if (res.next()) {
		nama = res.getString("name");
		konten = res.getString("content");
		tanggal = res.getString("created_at");
		qId = res.getInt("created_at");
		CommentModel c = new CommentModel(name, content, qId, Long.parseLong(tanggal));
		System.out.println("HAsil1 : " + c.toXML());
		hasil += c.toXML();
	    }
	    
	    PrintWriter o = response.getWriter();
	    System.out.println("HAsil : " + hasil);
	    o.println(hasil);
	    
	}
    }
    

    
    public String getUserAgent(HttpServletRequest request) {
	return request.getHeader("User-Agent");
    }
    
    public String getIP(HttpServletRequest request) {
	return request.getRemoteAddr();
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
	    Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
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
	    Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
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
