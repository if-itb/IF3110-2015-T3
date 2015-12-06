/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 *
 * @author Asus
 */
public class LoginRSServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM sessions WHERE AccessToken = ?"; // Login query validation

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            out.println(URLEncoder.encode(request.getParameter("token"), "UTF-8"));
            String finalToken = URLEncoder.encode(request.getParameter("token"), "UTF-8");
            dbStatement.setString(1, finalToken);
            int res = dbStatement.executeUpdate();
            out.println(res);
            Cookie ctoken = new Cookie("access_token","");
            Cookie cfrontend = new Cookie("access_token_frontend","");
            ctoken.setPath("/Identity/");
            cfrontend.setPath("/FrontEnd/");
            response.addCookie(ctoken);
            response.addCookie(cfrontend);
            if (request.getParameter("logout") == null)
                response.sendRedirect("http://localhost:8000/FrontEnd/login.jsp?relog=1");
            else
                response.sendRedirect("http://localhost:8000/FrontEnd/login.jsp");
        } catch (SQLException ex) {

            Logger.getLogger(LoginRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(LoginRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {}
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            /*final PrintWriter out = response.getWriter();
             StringBuffer jb = new StringBuffer();
             String line = null;
             try {
             BufferedReader reader = request.getReader();
             while ((line = reader.readLine()) != null)
             jb.append(line);
             } catch (Exception e) {}
             out.println(jb.toString());*/
            response.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE Email = ? AND Password = ?"; // Login query validation

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, request.getParameter("email"));
            dbStatement.setString(2, request.getParameter("password"));

            ResultSet rs = dbStatement.executeQuery();
            if (rs.next()) { // If the query returns a row (login succeeded)
                String token = null;
                String uAgent = request.getHeader("User-Agent");
                System.out.println(uAgent);
                //String userAgent = uAgent.replaceAll(";", "%3B");
//            ctoken.setPath("/Identity/");
//            response.addCookie(ctoken);
//                Cookie cookies[] = request.getCookies();
//                    if (cookies != null) {
//                        out.println(cookies.length);
//                        for (int i=0;i<cookies.length;i++) {
//                            if (cookies[i].getName().equals("access_token")) {
//                                lastToken = cookies[i].getValue();
//                                out.println(cookies[i].getName() + " " + token);
//                                break;
//                            }
//                        }
//                    }
                token = UUID.randomUUID().toString(); 
                String finalToken = token + "#"+uAgent+"#"+request.getRemoteAddr(); //generate token
                finalToken = URLEncoder.encode(finalToken, "UTF-8");
                
                Cookie ctoken = new Cookie("access_token",finalToken);
                Cookie cfrontend = new Cookie("access_token_frontend",finalToken);
                ctoken.setPath("/Identity/");
                cfrontend.setPath("/FrontEnd/");
                
                sql = "INSERT INTO sessions (Email, AccessToken,ExpiredDate) VALUES (?,?,NOW()+INTERVAL 5 MINUTE)";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, request.getParameter("email"));
                dbStatement.setString(2, finalToken);
                dbStatement.executeUpdate();

                response.addCookie(ctoken);
                response.addCookie(cfrontend);
                //response.setHeader("access_token", token);
                //response.addHeader("token", token);
                response.sendRedirect("http://localhost:8000/FrontEnd/login.jsp?valid=1");
            } else {
                response.sendRedirect("http://localhost:8000/FrontEnd/login.jsp?valid=0&isi=" + request.getParameter("Email"));
            }
        } catch (SQLException ex) {

            Logger.getLogger(LoginRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(LoginRSServlet.class.getName()).log(Level.SEVERE, null, ex);
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
