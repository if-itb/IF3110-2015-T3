package ClientServlet;

import ClientServlet.helper.RequestHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import service.Exception_Exception;
import service.StackExchangeService_Service;


@WebServlet(name = "CreateAnswer", urlPatterns = {"/answer"})
public class CreateAnswer extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;
    
    private String useragent;
    private String ip;
    
    // Servlet for Creating Answer
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
	// Create Request handler for the request to this servlet
	RequestHandler rh = new RequestHandler(request);
	
	if(rh.isHasToken()){
	    // Request Has token cookie
	    // Get request parameters
	    this.useragent = request.getHeader("User-Agent");
            this.ip = request.getRemoteAddr();
            
            String content = request.getParameter("content");
	    int qid = Integer.parseInt(request.getParameter("qid"));
	    
	    // Call the createAnswer WebService
	    String res = createAnswer(qid, rh.getUsername(), content, rh.getToken());
	    
	    // Handle the response based on the result above
	    if (null != res) switch (res) {
	    	case "success":
		    response.sendRedirect("detail?idDetail=" + qid);
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
	    // Request has no token cookie found. Redirect to Auth page
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            Logger.getLogger(CreateAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateAnswer.class.getName()).log(Level.SEVERE, null, ex);
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

    private String createAnswer(int qid, java.lang.String name, java.lang.String content, java.lang.String token) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
        SOAPHeaderHandler.registerHeader(port, this.useragent, this.ip);
	return port.createAnswer(qid, name, content, token);
    }

    

}
