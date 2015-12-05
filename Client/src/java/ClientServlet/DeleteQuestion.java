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


// Servlet for Deleting Question 
@WebServlet(name = "DeleteQuestion", urlPatterns = {"/delete"})
public class DeleteQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;
    
    private String useragent;
    private String ip;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        // Create new handler for the request to this servlet
	RequestHandler rh = new RequestHandler(request);
	
	// Check wheter the request has token cookie
	boolean hasToken = rh.isHasToken();
        if (hasToken) {
            this.useragent = request.getHeader("User-Agent");
            this.ip = request.getRemoteAddr();
            
	    String idDeleted = request.getParameter("idDeleted"); // get id of the deleted question
	    
	    // Call the web service
            String res = deleteQuestion(Integer.parseInt(idDeleted), rh.getToken());
	    
	    // Set the response based on the result above
	    if(res.trim().equals("expired")){
		response.sendRedirect("expired");
	    }
	    else if (res.trim().equals("invalid")) {
		response.sendRedirect("invalid");
	    }
        }
	else {
	    // The request has no token cookie. Redirect to auth page
	    response.sendRedirect("auth");
	}
	    

	response.sendRedirect("Home");
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
            Logger.getLogger(DeleteQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IllegalStateException e){
	    
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
            Logger.getLogger(DeleteQuestion.class.getName()).log(Level.SEVERE, null, ex);
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

    private String deleteQuestion(int qid, java.lang.String token) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
        SOAPHeaderHandler.registerHeader(port, this.useragent, this.ip);
	return port.deleteQuestion(qid, token);
    }


}
