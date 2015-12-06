/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua_parser.Parser;
import ua_parser.Client;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        PrintWriter out = response.getWriter();
        
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
        Statement statement;
        
        DBConnection connection = new DBConnection();
        Connection conn = connection.getConn();
        if (request.getParameter("email") != null && request.getParameter("password") != null) {
                
            String query1 = "SELECT * from user WHERE email = '" + request.getParameter("email") + "' AND password = '" + request.getParameter("password") + "'";
            String query2 = "SELECT * from user WHERE email = '" + request.getParameter("email") + "'";
            String query3 = "SELECT * from token WHERE user_Email = '" + request.getParameter("email") + "'";

            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(query3);

                if (rs.next()) {
                    String DeleteQuery = "DELETE FROM token WHERE user_Email = '" + request.getParameter("email") + "'";
                    statement.execute(DeleteQuery);
                }

                rs = statement.executeQuery(query1);

                if (rs.next()) {
                    UUID tokenGenerator = UUID.randomUUID();                    
                    String token = tokenGenerator.toString();
                    
                    String userAgent = request.getHeader("User-Agent");
                    Parser uaParser = new Parser();
                    Client c = uaParser.parse(userAgent);
                    String ua = c.userAgent.family + "_" + c.os.family + "_" + c.device.family;
                    
                    String ipAddress = request.getHeader("X-Forwarded-For");
                    if (ipAddress == null) 
                        ipAddress = request.getRemoteAddr();                    
                                        
                    String fulltoken = token + "|" + ua + "|" + ipAddress;
                    
                    Calendar date = Calendar.getInstance();
                    long t = date.getTimeInMillis();
                    Date expirationDate = new Date(t + (15 * 60000));
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
                    String updateQuery = "INSERT INTO token(value, user_Email, expiration_date) values('" + fulltoken + "', '" + request.getParameter("email") + "', '" + df.format(expirationDate) + "')";
                    statement.execute(updateQuery);
                    
                    Cookie tokenCookie = new Cookie("token", fulltoken);
                    tokenCookie.setPath("/Front-End");
                    response.addCookie(tokenCookie);
                    response.sendRedirect("http://localhost:8082/Front-End/index");
                    
                    
                } else {
                    rs = statement.executeQuery(query2);
                    if (rs.next()) {
                        response.sendRedirect("http://localhost:8082/Front-End/login.jsp?fail");
                    }
                    else {
                        response.sendRedirect("http://localhost:8082/Front-End/login.jsp?fail");
                    }
                }

                rs.close();
                statement.close();

            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        else {
            response.sendRedirect("http://localhost:8082/Front-End/login.jsp");
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

}