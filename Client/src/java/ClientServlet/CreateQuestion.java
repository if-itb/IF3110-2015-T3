
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

// Servlet for creating question
@WebServlet(name = "CreateQuestion", urlPatterns = {"/question"})
public class CreateQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;
    
    private String useragent;
    private String ip;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {

	response.setContentType("text/html;charset=UTF-8");
	
	// Create new handler for the Request to this servlet
	RequestHandler rh = new RequestHandler(request);
	
	// Check if the request has Token cookie
	boolean hasToken = rh.isHasToken();
	
	if(hasToken){
            this.useragent = request.getHeader("User-Agent");
            this.ip = request.getRemoteAddr();
            
	    String qtopic = request.getParameter("qtopic");      // Get Topic parameter
	    String qcontent = request.getParameter("qcontent");  // Get content parameter
	    
	    // Call the createQuestion webservice, set the variables
	    String res = createQuestion(rh.getUsername(), qtopic, qcontent, rh.getToken());
	    
	    if(null != res) // Set the response based on the result above
	    switch (res) {
	    	case "success":
		    response.sendRedirect("Home");
		    break;
	    	case "invalid":
		    response.sendRedirect("invalid");
		    break;
	    	default:
		    response.sendRedirect("expired");
		    break;
	    }
	}
	else
	{
	    // Request has no token cookie. Let the user to login/register
	    response.sendRedirect("auth");
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
            Logger.getLogger(CreateQuestion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateQuestion.class.getName()).log(Level.SEVERE, null, ex);
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

    private String createQuestion(java.lang.String name, java.lang.String qtopic, java.lang.String qcontent, java.lang.String token) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
        SOAPHeaderHandler.registerHeader(port, this.useragent, this.ip);
        
	return port.createQuestion(name, qtopic, qcontent, token);
    }


}
