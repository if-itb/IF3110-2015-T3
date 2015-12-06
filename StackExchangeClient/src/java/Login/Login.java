/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Security.MD5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
 * @author Ahmad Naufal Farhan
 */
public class Login extends HttpServlet {

    private static final String URL_LOGIN = "http://localhost:8082/StackExchangeIS/LoginAuth";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String redirectUrl = request.getContextPath();

        String email = request.getParameter("email");
        String password = MD5.crypt(request.getParameter("password")); 

        try {
            // establish a connection with the identity service that handles login
            URL url = new URL(URL_LOGIN);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           // set the request property
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            String params = String.format("email=%s&password=%s",
                                            URLEncoder.encode(email, "UTF-8"),
                                            URLEncoder.encode(password, "UTF-8"));

            try (OutputStream output = conn.getOutputStream()) {
                output.write(params.getBytes("UTF-8"));
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                                                    conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String temp;
            while ((temp = in.readLine()) != null)
                sb.append(temp);

            // json parser needed to parse the string
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(sb.toString());

                // get the attributes and add the cookie
            String strToken = (String) object.get("token_str");
            String useragent = request.getHeader("User-Agent");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");  
            if (ipAddress == null) {  
                ipAddress = request.getRemoteAddr();  
            }

            if (strToken != null) {
                strToken = strToken + "#" + useragent + "#" + ipAddress;
                Cookie tokenCookie;
                tokenCookie = new Cookie("token_cookie", strToken);
                tokenCookie.setPath(request.getContextPath());
                tokenCookie.setMaxAge(5 * 60);
                response.addCookie(tokenCookie);
                redirectUrl += "/home";
                response.sendRedirect(redirectUrl);
            } else {
                String error = (String) object.get("error");
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp"); 
                dispatcher.forward(request, response); 
                
            }

            conn.disconnect();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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