/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.identity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import stackexchange.identity.security.SessionIdentifierGenerator;
import stackexchange.webservice.util.Database;

/**
 *
 * @author fauzanrifqy
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("application/json");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String browser;
            if (request.getParameterMap().containsKey("browser")){
                browser = request.getParameter("browser");
            }else{
                browser ="nothinghere";
            }
            String ipAddress = request.getHeader("X-FORWARDED-FOR");  
            if (ipAddress == null) {  
                ipAddress = request.getRemoteAddr();  
            }
            
            //Akses database menggunakan query DB
            Database db = new Database();
            Connection conn = db.getConnection();
            try{
                String sql="select * from users where email='" + email + "' and password='" + password + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    sql= "delete from tokens where email='" + email + "'";
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                    
                    SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
                    String token = sig.nextSessionId()+"|-|"+browser+"|-|"+ipAddress;
                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    
                    sql = "insert into tokens (email,token,expdate) values (?,?,? + interval '30' minute)"; 
                    ps = conn.prepareStatement(sql);
                    
                    ps.setString(1, email);
                    ps.setString(2, token);
                    ps.setTimestamp(3, sqlDate);
                    
                    ps.executeUpdate();
                    JSONObject json = new JSONObject();
                    json.put("status", "success");
                    json.put("token", token);
                    out.print(json);
                    out.flush();
                }else{
                    JSONObject json = new JSONObject();
                    
                    json.put("status", "denied");
                    out.print(json);
                    out.flush();
                }
            }catch(Exception e){
                JSONObject json = new JSONObject();
                    
                json.put("status", e.getMessage());
                out.print(json);
                out.flush();
            }finally{
                db.closeConnection();
                db = null;
            }
            
        } finally {
            out.close();
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
