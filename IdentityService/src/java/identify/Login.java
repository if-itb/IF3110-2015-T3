
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



@WebServlet(name = "Login", urlPatterns = {"/login"})
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
        
        if ("POST".equals(request.getMethod())) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/xml;charset=UTF-8");
                User user = User.getUser(email, password);
                if (user !=null) {
                    AccessToken accessToken = new AccessToken(user.getEmail(), user.getName(), user.getId());
		    System.out.println(accessToken.toXML());
		    out.println(accessToken.toXML().trim());
		    accessToken.addToDatabase();
                    out.close();
                }
                else {
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
