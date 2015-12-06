import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
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

        Cookie[] cookies;
        cookies = request.getCookies();
        
            response.setContentType("application/xml;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String ip = request.getRemoteAddr();
                if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    String ipAddress = inetAddress.getHostAddress();
                    ip = ipAddress;
                }
                String userAgent = request.getHeader("user-agent");
                //out.println("The user is using browser " + userAgent + ip);
                int x = 0;
                while (x < cookies.length && !cookies[x].getName().equals("access_token")) {
                    x++;
                }
                if (x < cookies.length) {
                    try {
                        // Register JDBC driver
                        Class.forName("com.mysql.jdbc.Driver");
                        
                        // Execute SQL query
                        try ( // Open a connection
                            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // Execute SQL query
                            Statement stmt = conn.createStatement()) {
                            String sql = "SELECT AccessToken,UserID from token";
                            try (ResultSet rs = stmt.executeQuery(sql)) {
                                boolean found = false;
                                while (rs.next()) {
                                    String AccessToken = rs.getString("AccessToken");
                                    if (AccessToken.substring(0,4).equals(cookies[x].getValue().substring(0,4)))
                                        found=true;
                                }
                                if (found) {
                                    int i = 5;
                                    String _userAgent = "";
                                    while (cookies[x].getValue().charAt(i) != '#'){
                                        _userAgent = _userAgent + cookies[x].getValue().charAt(i);
                                        i++;
                                    }
                                    i++;
                                    String _ip = "";
                                    while (i < cookies[x].getValue().length()){
                                        _ip = _ip + cookies[x].getValue().charAt(i);
                                        i++;
                                    }
                                    if (!(userAgent.equals(_userAgent))){
                                        out.println("accesstokenusedinotherbrowser");
                                    }else if (!(ip.equals(_ip))){
                                        out.println("accesstokenusedwithotherconnection");
                                    }else{
                                        out.println(rs.getInt("UserID"));
                                    }
                                } else {
                                    out.println("accesstokennotvalid");
                                }
                                
                                // Clean-up environment
                            }
                        }
                    } catch (SQLException | ClassNotFoundException se) {
                    }
                } else {
                    out.println("accesstokenexpired");
                    //expired
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
