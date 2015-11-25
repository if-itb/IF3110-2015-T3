package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Exception_Exception;
import service.StackExchangeService_Service;


@WebServlet(name = "VoteQuestion", urlPatterns = {"/VoteQuestion"})
public class VoteQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        int qid = Integer.parseInt(request.getParameter("qid"));
        String operation =  request.getParameter("operation");
        
	RequestHandler rh = new RequestHandler(request);
        if(rh.isHasToken()) {
            int newVote = voteQuestion(qid, operation, rh.getToken(),rh.getId());
	    System.out.println("RESULT : " + newVote);
	    response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    if(newVote==9999 ) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("<expired>true</expired>");
	    }
	    else if(newVote==-9999 ){
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("<invalid>true</invalid>");
	    }
	    else if(newVote==1234) {
		response.getWriter().write("<cantVote>true</cantVote>");
	    }
	    else {
		    response.getWriter().write("<new-vote>" + newVote + "</new-vote>");
	    }
        }
        else {
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(VoteQuestion.class.getName()).log(Level.SEVERE, null, ex);
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

    private int voteQuestion(int qid, java.lang.String operation, java.lang.String token, int id) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
	return port.voteQuestion(qid, operation, token, id);
    }




}
