/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReSTful;

import DBConnect.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author ryanyonata
 */
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
    DBConnect db = new DBConnect();
    Connection conn = db.connect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();
    
        try (PrintWriter out = response.getWriter()) {      
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            String user_agent = request.getHeader("User-Agent");
            String ip_address = request.getHeader("X-FORWARDED-FOR");
            if (ip_address == null){
                ip_address = request.getRemoteAddr();
            }
            
            try {
                /* Delete Expired Token */
                Statement statement = conn.createStatement();
                String sql = "DELETE FROM tokenlist WHERE exp_date < now()";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                int query_success = dbStatement.executeUpdate();
                
                /* Check User Existance */
                sql = "SELECT * FROM user WHERE email = ? AND password = SHA1(?) LIMIT 1";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, email);
                dbStatement.setString(2, password);
                ResultSet result = dbStatement.executeQuery();
                //String name = result.getString("name");

                /* Add in Token List */
                if(result.next()){
                    /* Set Token Expired Time */
                    Calendar date = Calendar.getInstance();
                    long delta = date.getTimeInMillis();
                    Date exp_date = new Date(delta + (3 * 60000));
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   
                    /* Insert New Token */
                    String token = URLEncoder.encode("ryanokebanget#" + user_agent + "#" + ip_address + "#" + df.format(exp_date).toString());
                    sql = "INSERT INTO tokenlist (user_id, token, user_agent, ip_address, exp_date) VALUES (?, ?, ?, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, result.getInt("id"));
                    dbStatement.setString(2, token);
                    dbStatement.setString(3, user_agent);
                    dbStatement.setString(4, ip_address);
                    dbStatement.setString(5, df.format(exp_date));
                    dbStatement.executeUpdate();
                    statement.close();

                    obj.put("token", token);
                    //obj.put("user", name);
                    obj.put("exp_date", df.format(exp_date));

                    out.print(obj);
                    Cookie cookie = new Cookie("token", token);
                    cookie.setMaxAge(60*5); //5 minutes
                    cookie.setPath("/");
                    response.addCookie(cookie);

                    request.setAttribute("loginsuccessful", 1);
                    response.sendRedirect("http://localhost:8083/Stack_Exchange_Client/QuestionServlet");
                } else {
                    response.sendRedirect("http://localhost:8083/Stack_Exchange_Client/login.jsp?success=0");                    
                    obj.put("error", "invalid email or password");  
                    out.print(obj);
                }
                                
            } catch (SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);        
            }
            //response.sendRedirect("http://localhost:8081/Stack_Exchange_Client/QuestionServlet");
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
