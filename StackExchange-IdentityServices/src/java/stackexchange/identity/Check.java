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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import stackexchange.identity.security.SessionIdentifierGenerator;
import stackexchange.identity.security.Validate;
import stackexchange.webservice.util.Database;

/**
 *
 * @author fauzanrifqy
 */
public class Check extends HttpServlet {

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
            String token = request.getParameter("token");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");  
            if (ipAddress == null) {  
                ipAddress = request.getRemoteAddr();  
            }
            
            //Akses database menggunakan query DB
            Database db = new Database();
            Connection conn = db.getConnection();
            JSONObject json = new JSONObject();
            try{
               
                String sql="select * from tokens where email='" + email + "' and token='" + token + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    if(rs.getTimestamp("expdate").after(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()))&&token.contains(ipAddress)){
                        json.put("status", "accept");
                    }else{
                        json.put("status", "expire");
                        SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
                        String newToken = sig.nextSessionId();

                        sql = "delete from tokens where email='"+email+"'";
                        ps = conn.prepareStatement(sql);
                        ps.executeUpdate();

                        java.sql.Timestamp currentTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                        sql = "insert into tokens (email,token,expdate) values (?,?,? + interval '30' minute)"; 
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, email);
                        ps.setString(2, newToken);
                        ps.setTimestamp(3, currentTime);
                        ps.executeUpdate();

                        json.put("token", newToken);
                    }
                }else{
                    json.put("status", "denied");
                }
            }catch(Exception e){
                json.put("status", "error");
            }finally{
                out.print(json);
                out.flush();
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
