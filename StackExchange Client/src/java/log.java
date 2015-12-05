/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author Raihan
 */
@WebServlet(urlPatterns = {"/log"})
public class log extends HttpServlet {

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
            out.println("<title>Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>");             
            
            
            String url = "http://localhost:8080/StackExchange_IS/Login";
            String charset = "UTF-8";
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String UA =  request.getHeader("User-Agent");
            String address = request.getHeader("X-Forwarded-For");
            if (address == null) {
                address = request.getRemoteAddr();
            }
            String query = String.format("email=%s&password=%s&ua=%s&addr=%s", 
                                    URLEncoder.encode(email, charset), 
                                    URLEncoder.encode(password, charset),
                                    URLEncoder.encode(UA,charset),
                                    URLEncoder.encode(address,charset));
            
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream res = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));            
            JSONParser parser = new JSONParser();             
            
            String output;      
            Object obj;
            JSONObject jobj;
            
            while ((output = br.readLine()) != null) {
            
              if (output.equals("{\"error\":\"invalid email or password\"}")) {
                  out.println("Login failed, invalid email or password");
              } else {       
                  out.println("Login success! Welcome!");                  
                    obj = parser.parse(output);
                    jobj = (JSONObject) obj;                    
                    String acctoken = (String) jobj.get("user_token");                       
                    if (acctoken != null ) {
                      Cookie ck = new Cookie("user_token", acctoken);
                      ck.setMaxAge(120);
                      response.addCookie(ck);
                    }
                  }
            }            

            
            connection.disconnect();            
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
            
            
        }
        catch (Exception e) {
            
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
