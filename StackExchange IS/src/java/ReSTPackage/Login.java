/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReSTPackage;

import database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author adar
 */
public class Login extends HttpServlet {
    
    DB db = new DB();
    Connection conn = DB.connect();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();
        
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement queryStat = conn.prepareStatement(sql);
            queryStat.setString(1, email);
            queryStat.setString(2, password);
            
            ResultSet rs = queryStat.executeQuery();
            if (rs.next()) {
                //Generate Token
                Random rand = new Random();
                int randnum = rand.nextInt(1000);
                String useragent = request.getParameter("ua");
                useragent = URLEncoder.encode(useragent, "UTF-8");
                String address = request.getParameter("addr");
                
                String token = randnum + "#" + useragent + "#" + address;
                
                //Generate Expired Time
                Calendar nowTime = Calendar.getInstance();
                long limitTime = 5 * 60 * 1000; // 5 minutes
                Date expiredTime = new Date(nowTime.getTimeInMillis() + limitTime);
                DateFormat dForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                //Update token to DB
                sql = "INSERT INTO user_token (id_user, token, time_expire) VALUES (?, ?, ?)";
                queryStat = conn.prepareStatement(sql);
                queryStat.setInt(1, rs.getInt("id"));
                queryStat.setString(2, token);
                queryStat.setString(3, dForm.format(expiredTime));
                queryStat.executeUpdate();
                stat.close();
                
                obj.put("user_token", token);
                obj.put("time_expire", dForm.format(expiredTime));
            }
            else {
                obj.put("error", "invalid email or password");
            }
            out.print(obj);
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
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
