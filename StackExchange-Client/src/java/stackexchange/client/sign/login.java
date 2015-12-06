/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.client.sign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author fauzanrifqy
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    private final String loginURL = "http://localhost:8082/StackExchange-IdentityServices/Login";
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
        JSONParser parser = new JSONParser();    
        try {
          String charset = "UTF-8";
          String email = request.getParameter("email");
          String password = request.getParameter("password");

          URL url = new URL("http://localhost:8082/StackExchange-IdentityServices/Login");
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setDoOutput(true);
          conn.setRequestMethod("POST");
          conn.setRequestProperty("Accept-Charset", charset);
          conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

          String query = String.format("email=%s&password=%s", 
                                        URLEncoder.encode(email, charset), 
                                        URLEncoder.encode(password, charset));

          try (OutputStream output = conn.getOutputStream()) {
              output.write(query.getBytes(charset));
          }

          InputStream res = conn.getInputStream();      
          BufferedReader br = new BufferedReader(new InputStreamReader(
              (conn.getInputStream())));

          String output;      
          Object obj;
          JSONObject jobj;

          while ((output = br.readLine()) != null) {
            obj = parser.parse(output);
            jobj = (JSONObject) obj;

            /* Save the token as Cookie, so browser have the identification */
            String atoken = (String) jobj.get("token");
            if ( atoken != null ) {
              Cookie ck = new Cookie("token", atoken);
              Cookie emc = new Cookie("email", email);
              ck.setMaxAge(60*35);
              emc.setMaxAge(60*35);
              response.addCookie(ck);
              response.addCookie(emc);
            }
          }

          conn.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (ParseException ex) { 
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }


        response.sendRedirect(request.getContextPath() + "/home");


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
