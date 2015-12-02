/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acel
 */
public class IdentityChecker extends HttpServlet {
    private int lifetime = 1000*1000; //100 seconds
    private String[] userIdentifier = new String[2];
    
    public void decodeToken(String token){
        byte[] byteDecoded = Base64.getDecoder().decode(token);
        String decoded = new String(byteDecoded, StandardCharsets.UTF_8);
        parseToken(decoded);        
    }
    
    public void parseToken(String token){
        String delim = ";";
        userIdentifier = token.split(delim);
    }
    
    public String getID(){
        return userIdentifier[0];
    }
    
    public String getTime(){
        return userIdentifier[1];
    }
    
    public boolean isNotExpired(){
        return((System.currentTimeMillis() - Long.parseLong(getTime())) <= lifetime);
    }
    
    public boolean isTokenValid(){
        //path and port for the database
        final String path = "jdbc:mysql://localhost:3306/stack_exchange";
        //query for database
        final String query = "SELECT COUNT(*) FROM user WHERE user_id = '" + getID() + "'";
        Database database = new Database();
        database.connect(path);

        int result = 0;
        ResultSet rs = database.fetchData(query);
        try {
            rs.next();
            result = rs.getInt("COUNT(*)");
            rs.close();
        } catch (SQLException ex) {
        }
        database.closeDatabase();
        if(result == 1 && isNotExpired()){
            return true;
        } else {
            return false;
        }
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
            String token = request.getParameter("token");
            String id = request.getParameter("id");
            decodeToken(token);
            if(isTokenValid() && id.equals(getID())){
                out.println("Successful");
            } else {
                out.println("Failed");
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
