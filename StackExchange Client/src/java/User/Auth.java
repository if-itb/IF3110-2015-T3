/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Asus
 */
public class Auth extends HttpServlet {

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
    System.setProperty("java.net.preferIPv6Addresses", "false");
    
    JSONParser parser = new JSONParser();    
    try {
      String charset = "UTF-8";
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      
      String userIP = request.getHeader("X-FORWARDED-FOR");  
      if (userIP == null) {  
          userIP = request.getRemoteAddr();  
      }
      String userAgent = request.getHeader("User-Agent");
      
      URL url = new URL("http://localhost:8082/Identity_Service/login");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Accept-Charset", charset);
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
     
      String query = String.format("email=%s&password=%s&userIP=%s&userAgent=%s", 
                                    URLEncoder.encode(email, charset), 
                                    URLEncoder.encode(password, charset),
                                    URLEncoder.encode(userIP, charset),
                                    URLEncoder.encode(userAgent, charset));
      
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
        String atoken = (String) jobj.get("access_token");
        if ( atoken != null ) {
          Cookie ck = new Cookie("access_token", atoken);
          response.addCookie(ck);
        }
      }
      
      conn.disconnect();

	  } catch (MalformedURLException e) {
      e.printStackTrace();
	  } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException ex) {
      Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
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
