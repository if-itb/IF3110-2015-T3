
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

/**
 *
 * @author i-ONe
 */
@WebServlet(urlPatterns = {"/TokenValidator"})
public class TokenValidator extends HttpServlet {

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
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/stackexchange";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Cookie cookie;
        Cookie[] cookies;
        cookies = request.getCookies();
        response.setContentType("application/xml;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (cookies != null) {
                cookie = cookies[0];

                try {
                    // Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    // Execute SQL query
                    try ( // Open a connection
                            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // Execute SQL query
                            Statement stmt = conn.createStatement()) {
                        String sql = "SELECT UserID from token where AccessToken = '" + cookie.getValue() + "'";
                        try (ResultSet rs = stmt.executeQuery(sql)) {
                            int userid = 0;
                            while (rs.next()) {
                                userid = rs.getInt("UserID");
                            }
                            if (userid != 0) {
                                out.println(userid);
                            } else {
                                out.println("access token not valid");
                            }
                            
                            // Clean-up environment
                        }
                    }
                } catch (SQLException | ClassNotFoundException se) {
                }
            } else {
                out.println("access token error");
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
