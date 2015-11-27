package ClientServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceRef;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import service.Exception_Exception;
import service.StackExchangeService_Service;


// Servlet for Registering a new User
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
	// Get the parameters needed
        String username = request.getParameter("username");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	try{
	    // Call the web service
	    String result = registerUser(username, email, password);
	    
	    // Parse The XML result  
	    String token = xmlParse(result, "token");
	    String usr = xmlParse(result, "username");
	    String id = xmlParse(result, "id");
	    
	    // Add the parsed result as  cookies to the Response of this servlet
	    Cookie tokenCookie = new Cookie("token", token);
	    response.addCookie(tokenCookie);

	    Cookie usernameCookie = new Cookie("username", usr);
	    response.addCookie(usernameCookie);

	    Cookie idCookie = new Cookie("id", id);
	    response.addCookie(idCookie);
	    
	    
	    // Redirect to home page
	    response.setContentType("text/html");
	    response.sendRedirect("Home");
	    
	}
	catch(Exception_Exception ex){
	    // Exception as the user not found 
	    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    request.setAttribute("errorMessage", "Email Already Used !");
	    request.getRequestDispatcher("/register.jsp").forward(request, response);
	}
	catch (IOException ex) {
	    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    request.setAttribute("errorMessage", ex.getMessage());
	    request.getRequestDispatcher("/register.jsp").forward(request, response);
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
	    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
	    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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

    private String registerUser(java.lang.String userName, java.lang.String userEmail, java.lang.String userPassword) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
	return port.registerUser(userName, userEmail, userPassword);
    }
    
    
    
    private String xmlParse(String txt, String node){
	String xml = txt ;
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	InputSource is;
	try {
	    builder = factory.newDocumentBuilder();
	    is = new InputSource(new StringReader(xml));
	    Document doc = builder.parse(is);
	    NodeList list = doc.getElementsByTagName(node);
	    return (list.item(0).getTextContent());
	} catch (ParserConfigurationException | SAXException | IOException e) {
	}
	return null;
    }

    private String registerUser_1(java.lang.String userName, java.lang.String userEmail, java.lang.String userPassword) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
	return port.registerUser(userName, userEmail, userPassword);
    }

}
