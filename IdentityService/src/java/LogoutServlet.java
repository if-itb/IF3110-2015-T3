/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class LogoutServlet extends HttpServlet {

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
    
    class Status {
        private boolean success;
        private String description;
        public void setSuccess(boolean newSuccess) { success = newSuccess; };
        public void setDescription(String newDescription) { description = newDescription; };
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder token = new StringBuilder();
        token.append(request.getParameter("token"));
        token.append("_" + request.getHeader("User-Agent"));
        String ipaddress = request.getHeader("X-FORWARDED-FOR");
        if (ipaddress == null) {
            ipaddress = request.getRemoteAddr();
        }
        token.append("_" + ipaddress);
        
        System.out.println(token.toString());
        response.setContentType("application/json");
        Gson gson = new Gson();
        
        try { 
            Connection conn = null;
            //PreparedStatement preparedStatement = null;
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT userID FROM account WHERE token=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, token.toString());
            ResultSet rs = preparedStatement.executeQuery();
            
            Status status = new Status();
            
            if (rs.next()) {
                sql = "UPDATE account SET token=null, tokenexpired=null WHERE token=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, token.toString());
                preparedStatement.executeUpdate();
                
                status.setSuccess(true);
                status.setDescription("token deleted");
                response.getOutputStream().print(gson.toJson(status));
                response.getOutputStream().flush();
            } else {
                sql = "SELECT token FROM account";
                preparedStatement = conn.prepareStatement(sql);
                rs = preparedStatement.executeQuery();
                String tokenParts[] = token.toString().split("#");
                String tokenSavedParts[] = new String[3];
                boolean found = false;

                if (tokenParts.length == 3) {
                    while (rs.next() && !found) {
                        tokenSavedParts = rs.getString("token").split("#");
                        if (tokenParts[0].equals(tokenSavedParts[0])) {
                            if (!tokenParts[1].equals(tokenSavedParts[1])) {
                                 found = true;
                                 status.setSuccess(false);
                                 status.setDescription("different browser");
                                 response.getOutputStream().print(gson.toJson(status));
                                 response.getOutputStream().flush();
                            }
                            else {
                                 found = true;
                                 status.setSuccess(false);
                                 status.setDescription("different ip address");
                                 response.getOutputStream().print(gson.toJson(status));
                                 response.getOutputStream().flush();
                            }
                        }
                    }
                }
                
                status.setSuccess(false);
                status.setDescription("token invalid");                
                response.getOutputStream().print(gson.toJson(status));
                response.getOutputStream().flush();                
            }
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
