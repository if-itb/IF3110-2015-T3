package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
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



@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	Form form = new Form();
	form.param("email", email);
	form.param("password", password);

	Client client = ClientBuilder.newClient();
	String url = "http://localhost:8080/IdentityService/login";

	try
	{
	    String result = client.target(url).request(MediaType.APPLICATION_XML)
		    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
	    String token = xmlParse(result, "token");
	    String username = xmlParse(result, "username");
	    String expirationDate = xmlParse(result, "expirationDate");
	    int lifetime =  Integer.parseInt(xmlParse(result, "lifetime")) ;
	    System.out.println("Token : "+ xmlParse(result, "token"));
	    
	    response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();

	    Cookie tokenCookie = new Cookie("token", token);
	    tokenCookie.setMaxAge(lifetime); 
	    response.addCookie(tokenCookie);
	    
	    Cookie usernameCookie = new Cookie("username", username);
	    usernameCookie.setMaxAge(lifetime); 
	    response.addCookie(usernameCookie);
	    
	    Cookie exDateCookie = new Cookie("expirationDate", expirationDate);
	    exDateCookie.setMaxAge(lifetime); 
	    response.addCookie(exDateCookie);

	    pw.println("Cookies created");
	    response.sendRedirect("Home");
	    
	}
	catch(Exception ex){
	    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    request.setAttribute("errorMessage", "Invalid Email or Password !");
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
