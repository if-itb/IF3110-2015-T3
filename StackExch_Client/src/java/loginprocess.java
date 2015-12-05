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
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.annotation.WebServlet;



@WebServlet(urlPatterns = {"/loginprocess"})
public class loginprocess extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        JSONParser parser = new JSONParser();  
        String lusername = request.getParameter("lusername");
        String lpass = request.getParameter("lpass");
        String lipa = request.getParameter("lipa");
        lipa =lipa.substring(0, lipa.length()-2);
        String lbrowser = request.getParameter("lbrowser");
        lbrowser=lbrowser.substring(0,lbrowser.length()-2);
        String t="",idd="",ee="",us="";
        try { 
            URL url = new URL("http://localhost:39854/IdentityService/ISLogin");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset","UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            String query = String.format("lusername=%s&lpass=%s&lbrowser=%s&lipa=%s",URLEncoder.encode(lusername, "UTF-8"), URLEncoder.encode(lpass, "UTF-8"),URLEncoder.encode(lbrowser, "UTF-8"),URLEncoder.encode(lipa, "UTF-8"));
            
            try (OutputStream output = conn.getOutputStream()) {
                output.write(query.getBytes("UTF-8"));
            }
            InputStream istream = conn.getInputStream();
            System.out.println(istream);
      
            BufferedReader bufread = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            Object obj;
            JSONObject jsonobj;
      
            while ((output = bufread.readLine()) != null) {
              obj = parser.parse(output);
              jsonobj = (JSONObject) obj;
               t = String.valueOf(jsonobj.get("token"));
               ee = String.valueOf(jsonobj.get("expire"));
               idd = String.valueOf(jsonobj.get("id"));
               us =  String.valueOf(jsonobj.get("username"));
               System.out.println(t+"|"+ee+"|"+idd+"|"+us);
            }      
            conn.disconnect();
            } catch (MalformedURLException e) {
                  e.printStackTrace();
            } catch (IOException e) {
                  e.printStackTrace();
           }
          
           int id = Integer.valueOf(idd);
           if (id!=-1) {
               HttpSession s =request.getSession();
               s.setAttribute("token",t);
               s.setAttribute("expire",ee);
               s.setAttribute("id",id);
               s.setAttribute("username",us);
           }
           response.sendRedirect("logininfo.jsp?status="+id);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(loginprocess.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(loginprocess.class.getName()).log(Level.SEVERE, null, ex);
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
