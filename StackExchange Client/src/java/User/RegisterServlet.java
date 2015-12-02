package User;

import UserWS.UserWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author M. Fauzan Naufan
 */
public class RegisterServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service;

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Simple StackExchange</title>");
            out.println("<link rel=\"stylesheet\" href=\"main.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"navbar-up\">\n"
                    + "            <a href=\"index.jsp\">\n"
                    + "                <h1 class=\"white\">Simple StackExchange</h1>\n"
                    + "            </a>\n"
                    + "        </div>\n"
                    + "        <div class=\"navbar-down\">\n"
                    + "            <b class=\"navbar-down\">\n"
                    + "                <a class=\"white\" href=\"register.jsp\">Register</a> |\n"
                    + "                <a class=\"white\" href=\"login.jsp\">Login</a>\n"
                    + "            </b>\n"
                    + "        </div>");
            out.println("<div class=\"main\">");
            out.println("<br>");
            String nama = request.getParameter("nama");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String result = register(nama, email, password);
            if (result.equals("Sukses!")) {
                out.println("<p class=\"blue\">Akun dengan nama " + nama + " berhasil diregistrasi</p>");
            } else {
                out.println("<p class=\"blue\">Registrasi akun gagal</p>");
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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

    private String register(java.lang.String nama, java.lang.String email, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        UserWS.UserWS port = service.getUserWSPort();
        return port.register(nama, email, password);
    }

}
