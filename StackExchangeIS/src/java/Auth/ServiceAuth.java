/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author Ahmad Naufal Farhan
 */
public class ServiceAuth extends HttpServlet {
    
    Connection connAuth = DB.getConnection();

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
        // get the token string
        JSONObject object = new JSONObject();
        String tokenstr = request.getParameter("token_str");
        String ipaddress = request.getParameter("ipaddress");
        String uagent = request.getParameter("uagent");
        
        try (PrintWriter out = response.getWriter()) {
            String query = "SELECT * FROM tokens WHERE token_str = ?";
            PreparedStatement pst = connAuth.prepareStatement(query);
            pst.setString(1, tokenstr);
            
            // execute the query
            ResultSet res = pst.executeQuery();
            
            if (res.next()) {
                DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dt = (Date) dformat.parse(res.getString("lifetime"));
                System.out.println(dt);
                // check if the token lifetime has expired
                Date cur = new Date();
                System.out.println(cur);
                if (cur.before(dt)) {
                    object.put("auth", 1);
                    object.put("messsage", "Token Valid");
                    object.put("user_id", res.getInt("uid"));
                    out.print(object);
                } else {
                    object.put("auth", 0);
                    object.put("message", "Token Expired");
                    out.print(object);
                }
                String tokendb = res.getString("token_str");
                String[] parts = tokendb.split("#");
                if (parts[1].equals(ipaddress)){
                    object.put("auth", 1);
                    object.put("messsage", "Token Valid");
                    object.put("user_id", res.getInt("uid"));
                    out.print(object);
                }else{
                    object.put("auth", -1);
                    object.put("message", "Token From Different IP");
                    out.print(object);
                }
                if (parts[2].equals(uagent)){
                    object.put("auth", 1);
                    object.put("messsage", "Token Valid");
                    object.put("user_id", res.getInt("uid"));
                    out.print(object);
                }else{
                    object.put("auth", -2);
                    object.put("message", "Token From Different Browser");
                    out.print(object);
                }
            } else {
                object.put("auth", -3);
                object.put("message", "Token Invalid");
                out.print(object);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ServiceAuth.class.getName()).log(Level.SEVERE, null, ex);
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
