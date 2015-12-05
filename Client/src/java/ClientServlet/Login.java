package ClientServlet;

import java.io.IOException;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


// Servlet for login user
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	// Get the parameters needed
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	// Creating a new Form Object, and set the parameters
	Form form = new Form();
	form.param("email", email);
	form.param("password", password);
	
	// Create a new Client Object
	Client client = ClientBuilder.newClient();
	
	// URL/Endpoint of the resource provider
	String url = "http://localhost:8080/IdentityService/login";

	try
	{
	    // Call the REST API and save the result string
	    String result = client.target(url).request(MediaType.APPLICATION_XML)
		    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
	    
	    // The result is User not found or the loign failed
	    if("UserNotFound".equals(result.trim())){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		// Redirect to the login page with error message
		request.setAttribute("errorMessage", "Invalid Email / Password !");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	    }
	    
            System.out.println(result);
	    // Login Success, then parse the XML string
	    String token = xmlParse(result, "token");	    // Parse the token from the XML document
	    String username = xmlParse(result, "username"); // Parse the username from the XML document
	    String id = xmlParse(result,"id");		    // Parse the user_id from the XML document
	    
	    
	    // Add the parsed result as  cookies to the Response of this servlet
	    Cookie tokenCookie = new Cookie("token", token);
	    response.addCookie(tokenCookie);
	    
	    Cookie usernameCookie = new Cookie("username", username);
	    response.addCookie(usernameCookie);
	    
	    Cookie idCookie = new Cookie("id", id);
	    response.addCookie(idCookie);
	    
	    // Redirect it to the home page
	    response.setContentType("text/html");
	    response.sendRedirect("Home");
	}
	catch(ServletException | IOException ex){
	    request.setAttribute("errorMessage", ex.getMessage());
	    request.getRequestDispatcher("/login.jsp").forward(request, response);
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
	processRequest(request, response);
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
	processRequest(request, response);
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
    
}
