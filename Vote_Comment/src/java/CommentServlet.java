/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Scemo
 */
public class CommentServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("application/json");
        JSONArray ret = new JSONArray();
    
        try (PrintWriter out = response.getWriter()) {
            String qid = request.getParameter("qid");
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dadakandb?zeroDateTimeBehavior=convertToNull","root","");
            String sql = "SELECT comments.id, id_question, comment, name FROM (SELECT * FROM comments WHERE id_question='"+qid+"') AS comments"
                    +"LEFT JOIN (SELECT id, name FROM users) AS user ON comments.id_user = user.id";
            java.sql.Statement stmt = conn.createStatement();
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            ResultSet rs = dbStatement.executeQuery();
            if(rs.next()){
                do {
                    JSONObject obj = new JSONObject();
                    obj.put("id", rs.getInt("id"));
                    obj.put("q_id", rs.getInt("id_question"));
                    obj.put("user", rs.getString("id_user"));
                    obj.put("comment", rs.getString("comment"));

                    ret.add(obj);
                } while(rs.next());
           
                out.print(ret); 
            } else {
                JSONObject obj = new JSONObject();
                obj.put("error", "no result");
                ret.add(obj);
                out.print(ret);        
            }
            stmt.close();
        } catch (SQLException ex) {
                   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setContentType("application/json");
        JSONObject res = new JSONObject();
            String token = "";
            Cookie[] cookies = request.getCookies();
            if(cookies==null) {      
                System.out.println("COOKIES NULL");
            }
            else {                
                for(Cookie cookie : cookies) {
                    if("token".equals(cookie.getName())) { 
                        token = cookie.getValue();
                        System.out.println(token);
                        break;
                    }   
                }
            }
        
        int qid = Integer.valueOf(request.getParameter("qid"));
        String comment = request.getParameter("comment");
        Form form = new Form();
        form.param("token",token);
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8082/Identity_Service/CheckToken"; 
        String result = "";
    
        try (PrintWriter out = response.getWriter()) {
            result = client.target(url).request(MediaType.APPLICATION_JSON).
                  post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(result);
            JSONObject jobj = (JSONObject) obj;
            String message = (String) jobj.get("message");
            System.out.println(message);
            if(message.equals("valid")) {
                try {         
                    System.out.println("success!!");
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dadakandb?zeroDateTimeBehavior=convertToNull","root","");
                    String sql = "INSERT INTO comments(id_question,id_user,comment) VALUES ('"+qid+"',(select userid from tokens where token='"+token+"'),'"+comment;
                    java.sql.Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                    res.put("error", "success");  
                    out.print(res);
                } catch(Exception e) {}   
            }
            else if(message.equals("expired")) {
                System.out.println("expred!");
                res.put("error", "expired");  
                out.print(res);
            }
            else if(message.equals("invalid")) {
                System.out.println("invalid!");
                res.put("error", "invalid");  
                out.print(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
