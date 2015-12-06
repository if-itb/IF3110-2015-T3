/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ClientValidate.ClientValidate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Bimo
 */
public class votequestion extends HttpServlet {

    private static final String URL_VOTE_QUESTION = "http://localhost:8083/StackExchangeAJS/VoteQuestion";
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
        int qid = Integer.parseInt(request.getParameter("qid"));
        try {
            // establish a connection with the identity service that handles login
            URL url = new URL(URL_VOTE_QUESTION);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           // set the request property
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("charset", "utf-8");

            String params = String.format("qid=%d", qid);

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
            
            try (PrintWriter out = response.getWriter()) {
                out.print(object);
            }
            
            conn.disconnect();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(votequestion.class.getName()).log(Level.SEVERE, null, ex);
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
        Cookie[] cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent").replace(';', '%');// Ambil user agent dari client
        useragent = useragent.replace(',', '$');
        String ipAddress = request.getHeader("X-FORWARDED-FOR");    // ** Ambil IP Address Client
        if (ipAddress == null)
           ipAddress = request.getRemoteAddr();  
        
        String token = ClientValidate.tokenExtract(cookies);
        
        if (token == null) {
            request.setAttribute("error", "You have to log in first!");
            response.sendRedirect("login.jsp");
        } else {
            int qid = Integer.parseInt(request.getParameter("qid"));
            int value = Integer.parseInt(request.getParameter("jlhvote"));
            int ins = votequestion(token, ipAddress, useragent, qid, value);
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

    private int votequestion(String token, String ipAddress, String useragent, int qid, int value) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        int res = -3;

        try {
            // establish a connection with the identity service that handles login
            URL url = new URL(URL_VOTE_QUESTION);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           // set the request property
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            String params = String.format("token=%s&uagent=%s&ipaddress=%s&qid=%d&value=%d",
                                            URLEncoder.encode(token, "UTF-8"), 
                                            URLEncoder.encode(ipAddress, "UTF-8"),
                                            URLEncoder.encode(useragent, "UTF-8"), 
                                            qid, value);

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

            res = (int) object.get("success");

            conn.disconnect();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(votequestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

}
