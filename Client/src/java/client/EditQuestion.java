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


@WebServlet(name = "editQuestion", urlPatterns = {"/editQuestion"})
public class EditQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
	RequestHandler rh = new RequestHandler(request);
        if (rh.isHasToken()) {
	    System.out.println("Call Edit");
            String res = editQuestion(
                                Integer.parseInt(request.getParameter("idEdited")),
                                rh.getUsername(),
				request.getParameter("qtopic"),
                                request.getParameter("qcontent"),
                                rh.getToken(),
                                rh.getExpirationDate()
                            );
	    if (!res.trim().equals("success")) {
		System.out.println("Fail Edit");
		response.sendRedirect("InvalidateCookie");
	    }
	    else{
		if ("1".equals(request.getParameter("fromDetail"))) {
		    response.sendRedirect("detail?idDetail=" + request.getParameter("idEdited"));
		} else {
		    response.sendRedirect("Home");
		}
	    }
        }
        else {
            response.sendRedirect("Home");
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
	return port.editQuestion(qid, name, qtopic, qcontent, token, expirationDate);
    }

}
