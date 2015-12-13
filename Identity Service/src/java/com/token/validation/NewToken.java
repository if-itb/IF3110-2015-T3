package com.token.validation;

import java.util.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;


@WebServlet(name="NewToken" , urlPatterns = {"/NewToken"})
public class NewToken extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String obj = "", token = ""; 
        JSONObject jobj = new JSONObject();
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(exist(email,password)) {
            //create connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            //get id and name from table users
            String sql = "SELECT * FROM users WHERE email='" + email + "'";
            PreparedStatement dbs = conn.prepareStatement(sql);
            ResultSet rs = dbs.executeQuery();
            int userid = 0;
            String username = "";
            while(rs.next()) {
                userid = rs.getInt("id");
                username = rs.getString("name");
            }
            //create token
            String agent = request.getHeader("user-agent");
            String ip_addr = request.getHeader("X-FORWARDED-FOR");
            if(ip_addr==null)
                ip_addr = request.getRemoteAddr();
            token = username + "#"+ agent+ "#"+ ip_addr;
            //create lifetime
            Calendar calobj = Calendar.getInstance();
            long time = calobj.getTimeInMillis()/1000+12000;
            //add token to table tokens in database
            sql = "INSERT INTO tokens(userid,token,produced) VALUES ('"+userid+"','"+token+"','"+time+"')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //create json
            jobj.put("message","valid");
            jobj.put("token",token);
            jobj.put("produced",time);
        }
        else 
            jobj.put("message","error");
        try (PrintWriter out = response.getWriter()) {
            out.println(jobj.toString());   
            out.close();
        }
        catch(Exception e) {}
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NewToken.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NewToken.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean exist(java.lang.String email, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Connection conn = null;        
        try {            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            String sql = "SELECT email,password FROM users WHERE email = '" + email + "' and password = '" + password + "'";
            PreparedStatement dbs = conn.prepareStatement(sql);
            ResultSet rs = dbs.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {} 
        return false;
    }
}