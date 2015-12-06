/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.sql.*;
import java.security.*;

/**
 *
 * @author vickonovianto
 */
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
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/wbd2";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";    
    
    public static final int LIFETIME = 300;
    
    class User {
        private String email;
        private String password;
        
        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }
    
    class Status {
        private boolean success;
        private String error_cause;
        private String access_token;
        private Integer lifetime;
        public void setSuccess(boolean newSuccess) { success = newSuccess; };
        public void setErrorCause(String newErrorCause) { error_cause = newErrorCause; };
        public void setAccessToken(String newAccessToken) { access_token = newAccessToken; };
        public void setLifetime(int newLifetime) { lifetime = new Integer(newLifetime); };
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
            
        try {
            //parsing json
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }
            
            User user = (User) gson.fromJson(sb.toString(), User.class);
 
            Connection conn = null;
            Statement stmt = null;
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT userID, password FROM account WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            
            Status status = new Status();
            
            if (rs.next()) {
                String password = rs.getString("password");
                if (password.equals(user.getPassword())) {
                    
                    String seed = "";
                    java.util.Date date = new java.util.Date();
                    long ms = date.getTime();
                    seed = seed.concat(user.getEmail() + user.getPassword() + ms);
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(seed.getBytes());
                    byte[] digest = md.digest();
                    StringBuilder access_token = new StringBuilder();
                    for (byte b : digest) {
                        access_token.append(String.format("%02x", b & 0xff));
                    }
                    
                    ms = ms + LIFETIME * 1000;
                    java.sql.Timestamp tokenexpired = new java.sql.Timestamp(ms);
                    
                    int userid = rs.getInt("userID");
                    sql = "UPDATE account SET token=?, tokenexpired=? WHERE userID = ?";
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, access_token.toString());
                    preparedStatement.setTimestamp(2, tokenexpired);
                    preparedStatement.setInt(3, userid);
                    preparedStatement.executeUpdate();
                    
                    status.setSuccess(true);
                    status.setAccessToken(access_token.toString());
                    status.setLifetime(LIFETIME);
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();
                }
                else {
                    status.setSuccess(false);
                    status.setErrorCause("password");
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();
                }
            }
            else {
                status.setSuccess(false);
                status.setErrorCause("email");
                response.getOutputStream().print(gson.toJson(status));
                response.getOutputStream().flush();
            }
            
            rs.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
           
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
        //processRequest(request, response);
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
}
