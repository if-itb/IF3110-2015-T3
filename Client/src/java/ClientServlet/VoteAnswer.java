package ClientServlet;

import ClientServlet.helper.RequestHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Exception_Exception;
import service.StackExchangeService_Service;


// Servlet for Voting Question
@WebServlet(name = "VoteAnswer", urlPatterns = {"/VoteAnswer"})
public class VoteAnswer extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    private String useragent;
    private String ip;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
	// Get parameters needed
        int aid = Integer.parseInt(request.getParameter("aid"));
        String operation =  request.getParameter("operation");
        
	// Create new handler for the request to the servlet
	RequestHandler rh = new RequestHandler(request);
	
	// Check if the request has Token cookie
        if(rh.isHasToken()) {
            this.useragent = request.getHeader("User-Agent");
            this.ip = request.getRemoteAddr();
            
	    
	    // Call the web service
            int newVote = voteAnswer(aid, operation, rh.getToken(),rh.getId());
	    response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    
	    // Set the response based on the result above
	    switch (newVote) {
	    	case -9999: // Expired
		    response.getWriter().write("<expired>true</expired>");
		    break;
	    	case 9999: // Invalid
		    response.getWriter().write("<invalid>true</invalid>");
		    break;
	    	case 1234: // Cannot Vote
		    response.getWriter().write("<cantVoteAnswer>true</cantVoteAnswer>");
		    break;
	    	default:   // Can vote
		    response.getWriter().write("<new-vote>" + newVote + "</new-vote>");
		    break;
	    }
        }
        else {
	    // No token cookie found
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
        } catch (Exception_Exception ex) {
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
        } catch (Exception_Exception ex) {
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

    private int voteAnswer(int aid, java.lang.String operation, java.lang.String token, int id) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
        SOAPHeaderHandler.registerHeader(port, this.useragent, this.ip);
	return port.voteAnswer(aid, operation, token, id);
    }

}
