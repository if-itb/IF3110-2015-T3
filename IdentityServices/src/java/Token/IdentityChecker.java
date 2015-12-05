/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acel
 */
@WebServlet(name = "IdentityChecker", urlPatterns = {"/IdentityChecker"})
public class IdentityChecker extends HttpServlet {
    private String token = "";
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
        
    public boolean isTokenValid(int user_id, String user_agent, String ip_address){
        //query for database
        final String query = "SELECT COUNT(*), expires, user_id FROM token WHERE uuid = '" + token + "' "
                + "AND user_agent = '" + user_agent + "' AND ip_address = '" + ip_address + "'";
        Database database = new Database();
        database.connect(path);

        int result = 0;
        String expires = "";
        ResultSet rs = database.fetchData(query);
        int uid = 0;
        try {
            rs.next();
            result = rs.getInt("COUNT(*)");
            expires = rs.getString("expires");
            uid = rs.getInt("user_id");
            rs.close();
        } catch (SQLException ex) {
        }
        database.closeDatabase();
        
        //check expiry
        Date date = new Date(System.currentTimeMillis());
        Timestamp t = Timestamp.valueOf(expires);
        return (result == 1 && t.compareTo(date) > 0 && user_id == uid);
    }
    
    public String deleteToken(String uuid){
        //query for database
        final String query = "DELETE FROM token WHERE uuid = '" + uuid + "'";
        Database database = new Database();
        database.connect(path);
        database.changeData(query);
        database.closeDatabase();
        return "Token deleted";
    }
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
            Cookie[] cookies = request.getCookies();
            for(Cookie temp : cookies){
                if(temp.getName().equals("token")){
                    token = temp.getValue();
                }
            }
            int uid = Integer.parseInt(request.getParameter("id"));
            String user_agent = request.getHeader("User-Agent");
            String ip_address = request.getRemoteHost();
            if(request.getParameter("action") == null   ){
                if(isTokenValid(uid, user_agent, ip_address)){
                    out.println("Successful");
                } else {
                    out.println("Failed");
                }
            } else if(request.getParameter("action").equals("logout")){
                out.println(deleteToken(token));
                response.sendRedirect("http://localhost:8080/StackExchange_Client/login.jsp");
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
