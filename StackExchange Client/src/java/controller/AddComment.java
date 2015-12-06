/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
 * @author Scemo
 */
public class AddComment extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
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

            /*String userIP = request.getHeader("X-FORWARDED-FOR");
            if (userIP == null) {
                userIP = request.getRemoteAddr();
            }
            String userAgent = request.getHeader("User-Agent");*/
            
            String charset = "UTF-8";
            String qid = request.getParameter("qid");
            String content = request.getParameter("content");
                   
            URL url = new URL("http://localhost:8083/Vote_Comment/CommentServlet");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            String query = String.format("qid=%s&comment=%s&token=%s",
                    URLEncoder.encode(qid, charset),
                    URLEncoder.encode(content, charset),
                    URLEncoder.encode(token, charset));
            
            try (OutputStream output = conn.getOutputStream()) {
                output.write(query.getBytes(charset));
            }
            System.out.println("outputed!!");
            InputStream res = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            
            String output;
            
            while ((output = br.readLine()) != null) {
                out.println(output);
            }
            
            conn.disconnect();
            
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
