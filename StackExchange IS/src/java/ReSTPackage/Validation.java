/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReSTPackage;

import database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author adar
 */
public class Validation extends HttpServlet {
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        JSONObject obj = new JSONObject();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            String token = request.getParameter("token");
            
            try {
                Statement stat = conn.createStatement();
                String sql = "SELECT * FROM user_token WHERE token = ?";
                PreparedStatement queryStat = conn.prepareStatement(sql);
                queryStat.setString(1, token);
          
                ResultSet rs = queryStat.executeQuery();
          
                if(rs.next()) {
                    DateFormat dForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date expiredTime = dForm.parse(rs.getString("time_expire"));
                    Date nowTime = new Date();

                    String useragent = request.getHeader("user-agent");
                    String address = request.getHeader("X-Forwarded-For");
                    if (address == null) {
                        address = request.getRemoteAddr();
                    }
                    
                    String tokenReal = rs.getString("token");
                    String[] tokenParse = tokenReal.split("#");
                    
                    // Cek Expired Token
                    if (nowTime.after(expiredTime)) {
                        obj.put("time", "expired");
                    } else {
                        obj.put("time", "valid");
                    }
              

                    // Cek User Agent
                    if (!tokenParse[1].equals(useragent)) {
                        obj.put("useragent", "invalid");
                    }
                    else {
                        obj.put("useragent", "valid");
                    }
                    
                    // Cek IP Address
                    if (!tokenParse[2].equals(address)) {
                        obj.put("address", "invalid");
                    }
                    else {
                        obj.put("address", "valid");
                    }
                    
                    out.print(obj);
                } else {
                    obj.put("token", "invalid");
                    out.print(obj);
                }
          
            } catch(SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
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
