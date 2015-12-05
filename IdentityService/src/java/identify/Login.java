
package identify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccessToken;
import model.User;


// Servlet that provide the access token to the user that login
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if ("POST".equals(request.getMethod())) {
	    // Get the email, and password
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/xml;charset=UTF-8");
		// Get user from database
                User user = User.getUser(email, password);
                if (user !=null) {
		    // User exist in database
		    // Create new accessToken
                    AccessToken accessToken = new AccessToken(user.getEmail(), user.getName(), user.getId(), 
                            request.getHeader("User-Agent"), request.getHeader("Remote-Origin"));
                    
		    // Return the XML of the access token object
		    out.println(accessToken.toXML().trim());
		    
		    // Add the access token to database
		    accessToken.addToDatabase();
		    
                    out.close();
                }
                else {
		    // User not exist in database
		    response.getWriter().println("UserNotFound");
		    response.getWriter().close();
                }
            }
            catch (Exception exception) {
                response.getWriter().println("Authentication failure.");
                response.getWriter().close();
            }
        }
        else {
	    // Call without method POST
            try (PrintWriter out = response.getWriter()) {
                out.println("Method not supported.");
                out.close();
            }
        }    
    }

    // <editld defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
	} catch (ServletException | IOException ex) {
	    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
