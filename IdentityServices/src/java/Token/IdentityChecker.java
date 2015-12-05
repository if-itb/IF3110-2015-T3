/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acel
 */
public class IdentityChecker extends HttpServlet {
    private String token = "";
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
        
    public boolean isTokenValid(){
        //query for database
        final String query = "SELECT COUNT(*) FROM token WHERE token = '" + token + "'";
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
        return (result == 1);
    }
    
    public String deleteToken(){
        //query for database
        final String query = "DELETE FROM token WHERE id = '" + token + "'";
        Database database = new Database();
        database.connect(path);
        database.changeData(query);
        database.closeDatabase();
        return "executed";
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
            int expiry = -999;
            for(Cookie temp : cookies){
                if(temp.getName().equals("token")){
                    token = temp.getValue();
                    expiry = temp.getMaxAge();
                }
            }
            
            if(isTokenValid() && expiry != 0){
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
