package User;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author User
 */
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
        String UserAgent = request.getHeader("User-Agent");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Get the parameters needed
            String email = request.getParameter("email");
            String password = request.getParameter("password");
	
            // Creating a new Form Object, and set the parameters
            Form form = new Form();
            form.param("email", email);
            form.param("password", password);
            form.param("user-agent", UserAgent);
	
            // Create a new Client Object
            Client client = ClientBuilder.newClient();
	
            // URL/Endpoint of the resource provider
            String url = "http://localhost:8082/Identity_Service/Login";

            try {
               // Call the REST API and save the result string
               String result = client.target(url).request(MediaType.APPLICATION_XML)
            	    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
                // The result is User not found or the login failed
                if("authenticationerror".equals(result.trim())){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
                    // Redirect to the login page with error message
                    request.setAttribute("errorMessage", "Invalid Email / Password !");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    // Redirect it to the home page
                    Cookie accessToken = new Cookie("access_token", result.trim());
                    accessToken.setMaxAge(60 * 60 * 24);
                    response.addCookie( accessToken );
                    response.setContentType("text/html");
                    response.sendRedirect("index.jsp");
                }
            } catch(ServletException | IOException ex) {
                request.setAttribute("errorMessage", ex.getMessage());
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
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

}
