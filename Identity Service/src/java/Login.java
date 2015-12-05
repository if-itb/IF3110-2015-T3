
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author i-ONe
 */
@WebServlet(urlPatterns = {"/Login"})
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
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/stackexchange";

        //  Database credentials
        String USER = "root";
        String PASS = "";
        // Set response content type
        //response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        response.setContentType("application/xml;charset=UTF-8");
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Execute SQL query
            try ( // Open a connection
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // Execute SQL query
                    Statement stmt = conn.createStatement()) {
                String sql;
                sql = "SELECT COUNT(*) as rowcount from user where Email = '" + request.getParameter("email") + "' and Password = '" + request.getParameter("password") + "'";
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                if (rs.getInt("rowcount") != 0) {
                    sql = "SELECT UserID from user where Email = '" + request.getParameter("email") + "'";
                    rs = stmt.executeQuery(sql);
                    rs.next();
                    int userid = rs.getInt("UserID");

                    sql = "delete from token where UserID = " + userid;
                    int i = stmt.executeUpdate(sql);

                    String token = "";
                    do {
                        token = generateToken();
                        sql = "SELECT COUNT(*) as rowcount from token where AccessToken = '" + token + "'";
                        rs = stmt.executeQuery(sql);
                        rs.next();
                    } while (rs.getInt("rowcount") != 0);

                    java.util.Date date = new java.util.Date();
                    Timestamp expiredDate = new Timestamp(date.getTime() + (1000 * 60 * 60 * 24));
                    sql = "INSERT INTO token (AccessToken,UserID,ExpiredDate) VALUES ('" + token + "'," + userid + ",'" + expiredDate + "')";
                    i = stmt.executeUpdate(sql);
                    if (i > 0) {
                        //Cookie accessToken = new Cookie("access_token", token);
                        //accessToken.setMaxAge(60 * 60 * 24);
                        //response.addCookie(accessToken);
                        out.println(token);
                    }
                } else {
                    out.println("authenticationerror");
                }
                // Clean-up environment
                rs.close();
            }
        } catch (SQLException | ClassNotFoundException se) {
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

    private String generateToken() {
        String sAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String CAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";
        String token = "";

        Random r = new Random();
        token = token + sAlphabet.charAt(r.nextInt(26));
        token = token + number.charAt(r.nextInt(10));
        token = token + number.charAt(r.nextInt(10));
        token = token + CAlphabet.charAt(r.nextInt(26));

        return token;
    }
}
