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

/**
 *
 * @author vickonovianto
 */
public class AuthServlet extends HttpServlet {

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
    
    class Token {
        private String access_token;
        
        public String getAccessToken() { return access_token; }
    }
    
    class Status {
        private boolean success;
        private String description;
        private int userID;
        public void setSuccess(boolean newSuccess) { success = newSuccess; };
        public void setDescription(String newDescription) { description = newDescription; };
        public void setUserID(int newUserID) { userID = newUserID; };
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
 
            Token token = (Token) gson.fromJson(sb.toString(), Token.class);
 
            Connection conn = null;
            //PreparedStatement preparedStatement = null;
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT userID, tokenexpired FROM account WHERE token=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, token.getAccessToken());
            ResultSet rs = preparedStatement.executeQuery();
            
            Status status = new Status();
            
            if (rs.next()) {
                java.sql.Timestamp tokenexpired = rs.getTimestamp("tokenexpired");
                java.util.Date now = new java.util.Date();
                long nowms = now.getTime();
                long expms = tokenexpired.getTime();
                
                if (nowms < expms) {
                    //token valid
                    int userid = rs.getInt("userID");
                    status.setSuccess(true);
                    status.setDescription("valid");
                    status.setUserID(userid);
                    
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();
                }
                else {
                    status.setSuccess(false);
                    status.setDescription("expired");
                    response.getOutputStream().print(gson.toJson(status));
                    response.getOutputStream().flush();
                }
            }
            else {
                status.setSuccess(false);
                status.setDescription("invalid");
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
