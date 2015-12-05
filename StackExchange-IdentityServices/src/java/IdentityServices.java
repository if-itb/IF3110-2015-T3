/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import stackexchange.identity.security.SessionIdentifierGenerator;
import stackexchange.webservice.util.Database;

/**
 *
 * @author Khalil Ambiya
 */
public class IdentityServices extends HttpServlet {

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
            
            //Akses database menggunakan query DB
            Database db = new Database();
            try{
                String sql="select * from users where email=" + email + " password=" + password;
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
                    String token = sig.nextSessionId();
                    sql = "insert into tokens (email,token) values ('"+ email +"'" + token + "')"; 
                    ps = db.getConnection().prepareStatement(sql);
                    ps.executeUpdate();
                    JSONObject json = new JSONObject();
                    json.put("status :", "success");
                    json.put("token :", token);
                    out.print(json);
                    out.flush();
                }else{
                    JSONObject json = new JSONObject();
                    
                    json.put("status :", "denied");
                    out.print(json);
                    out.flush();
                }
            }catch(Exception e){
                JSONObject json = new JSONObject();
                    
                json.put("status :", "error");
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
}
