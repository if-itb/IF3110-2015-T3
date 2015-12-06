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

// Servlet for Editing Question
@WebServlet(name = "editQuestion", urlPatterns = {"/editQuestion"})
public class EditQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    private String useragent;
    private String ip;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
	
	// Create a new Handler for the request to this servlet
	RequestHandler rh = new RequestHandler(request);
	
	// Check if the request has token cookie
        if (rh.isHasToken()) {
            this.useragent = request.getHeader("User-Agent");
            this.ip = request.getRemoteAddr();
            
	    // Call the webservice with the parameters needed
            String res = editQuestion(
                                Integer.parseInt(request.getParameter("idEdited")),
                                rh.getUsername(),
				request.getParameter("qtopic"),
                                request.getParameter("qcontent"),
                                rh.getToken(),
                                rh.getExpirationDate()
                            );
	    
	    // Set the response based on the result above
	    switch (res.trim()) {
	    	case "invalid":
		    response.sendRedirect("invalid");
		    break;
	    	case "expired":
		    response.sendRedirect("expired");
		    break;
	    	default:
		    // Check the page that call this servlet. Than redirect to it.
		    if ("1".equals(request.getParameter("fromDetail"))) {
			response.sendRedirect("detail?idDetail=" + request.getParameter("idEdited")); // Called from detail page
		    } else {
			response.sendRedirect("Home"); // Called from home page
		    }   break;
	    }
        }
        else {
	    // No token cookie found
            System.out.println("NYASAR WOY");
            response.sendRedirect("auth");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(EditQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(EditQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String editQuestion(int qid, java.lang.String name, java.lang.String qtopic, java.lang.String qcontent, java.lang.String token, long expirationDate) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
        SOAPHeaderHandler.registerHeader(port, this.useragent, this.ip);
	return port.editQuestion(qid, name, qtopic, qcontent, token, expirationDate);
    }

}
