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
import java.util.UUID;
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
@WebServlet(name = "Token", urlPatterns = {"/Token"})
public class Token extends HttpServlet {
    public static int lifetime = 100; //100 seconds
    private String token = "";
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
    
    public long generateTime(){
        return System.currentTimeMillis();
    }
    
    public boolean isInDB(String username, String password){
        //query for database
        System.out.println("masuk");
        final String query = "SELECT COUNT(*) FROM user WHERE email = '" + username + "' AND password = '" + password + "'";
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
        System.out.println("keluar");
        database.closeDatabase();
        return (result == 1);
    }
    
    public int getUserID(String username, String password){
        //query for database
        final String query = "SELECT * FROM user WHERE email = '" + username + "' AND password = '" + password + "'";
        Database database = new Database();
        database.connect(path);
        
        int userID = -1;
        
        ResultSet rs = database.fetchData(query);
        try {
            rs.next();
            userID = rs.getInt("user_id");
            rs.close();
        } catch (SQLException ex) {
        }
        database.closeDatabase();
        return userID;
    }
    
    public void generateToken(String username, int user_id, String user_agent, String ip_address){
        token = UUID.randomUUID().toString();
        Date now = new Date(System.currentTimeMillis() + lifetime);
        String expires = new Timestamp(now.getTime()).toString();
        String query = "INSERT INTO token (user_id, uuid, expires, user_agent, ip_address) "
                + "VALUES (" + user_id + ", '" + token + "', '" + expires + "', " + user_agent + "', '" + ip_address + "')";
        Database database = new Database();
        database.connect(path);
        database.changeData(query);
        database.closeDatabase();
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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            //Variable
            String username = request.getParameter("uname");
            String password = request.getParameter("pword");
            String user_agent = request.getHeader("User-Agent");
            String ip_address = request.getRemoteHost();
            out.println(ip_address);
            if(isInDB(username,password)){
                out.println("A" + username);
                int user_id = getUserID(username, password);
                generateToken(username, user_id, user_agent, ip_address);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(lifetime);
                response.addCookie(cookie);
                response.sendRedirect("http://localhost:8080/StackExchange_Client/index.jsp");
            } else {
                out.println("C");
                token = "";
                response.sendError(1, "Login error!");
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
