package Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Token.Token;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import useragentutils.UserAgent;

@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException,ServletException {
        response.setContentType("text/html");
        UserService user = new UserService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String browser = userAgent.getBrowser().getName();
        String address = user.getClientIpAddr(request); 
        PrintWriter out = response.getWriter();
        out.println(email);
        out.println(password);
        
        try {
            if((user.emailExist(email)) && (user.passwordValid(email, password))){
                String token = user.getTokenFromUserID(user.getUserIDFromEmail(email));
                out.println(token);
                if (token == null){
                    UUID tokenGenerator = UUID.randomUUID();
                    token = tokenGenerator.toString();
                    java.util.Date dt = new java.util.Date();
                    java.text.SimpleDateFormat sdf = 
                                     new java.text.SimpleDateFormat("yyyyMMddHHmmss");

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dt);
                    cal.add(Calendar.MINUTE, 2);
                    String lifetime = sdf.format(cal.getTime());
                    String query = "INSERT INTO token (value,user_id,lifetime,browser,address) "
                            + "VALUES ('"+token+"','"+user.getUserIDFromEmail(email)+"','"+lifetime+
                            "','"+browser+"','"+address+"')";
                    user.executeQuery(query);
                }
                else{
                    UUID tokenGenerator = UUID.randomUUID();
                    token = tokenGenerator.toString();
                    java.util.Date dt = new java.util.Date();
                    java.text.SimpleDateFormat sdf = 
                                     new java.text.SimpleDateFormat("yyyyMMddHHmmss");

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dt);
                    cal.add(Calendar.MINUTE, 2);
                    String lifetime = sdf.format(cal.getTime());
                    String query = "UPDATE token SET value='"+token+"', lifetime='"+lifetime+
                            "', browser='"+browser+"', address='"+address+"' "
                            + "WHERE user_id="+user.getUserIDFromEmail(email);
                    user.executeQuery(query);
                }
                
                response.sendRedirect("http://localhost:8080/StackExchangeClient/login.jsp?valid=1&token="+token);
            }
            else
                response.sendRedirect("http://localhost:8080/StackExchangeClient/login.jsp?valid=0");
        }catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
