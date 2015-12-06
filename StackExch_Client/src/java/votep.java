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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tama
 */
@WebServlet(urlPatterns = {"/votep"})
public class votep extends HttpServlet {

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
             String cipa = request.getHeader("X-FORWARDED-FOR");  
            if (cipa == null) {  
                cipa = request.getRemoteAddr();  
            }
            String cbrowser = request.getHeader("user-agent");
            String action = request.getParameter("action");
            String id = request.getParameter("id");
            HttpSession ss= request.getSession();
            String t = ss.getAttribute("token").toString();
            boolean x= true ;           
            if (t!=null&&t.length()>0) {
             
            } else {
                x=false;
                System.out.println("votep : Token tidak valid");
            }
            if (x) {
                try { 
                    String val="";
                    if (action.contains("up")) val="1";
                    else val="-1";
                    System.out.println("votep : "+action+"|"+id+"|"+cipa+"|"+val);
                    System.out.println("votep "+t);
                    System.out.println("votep "+cbrowser);

                    URL url = new URL("http://localhost:35476/CommentVote/vote");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);

                    JSONParser parser = new JSONParser();  
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Accept-Charset","UTF-8");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    String query = String.format("token=%s&id=%s&value=%s&cbrowser=%s&cipa=%s",URLEncoder.encode(t, "UTF-8"),URLEncoder.encode(id, "UTF-8"), URLEncoder.encode(val, "UTF-8"),URLEncoder.encode(cbrowser, "UTF-8"),URLEncoder.encode(cipa, "UTF-8"));
                     System.out.println("votep : aa");
                    try (OutputStream output = conn.getOutputStream()) {
                        output.write(query.getBytes("UTF-8"));
                    }
                    InputStream istream = conn.getInputStream();
                    System.out.println(istream);
                    System.out.println("votep : bb");
                    BufferedReader bufread = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                    String output;
                    Object obj;
                    JSONObject jsonobj;
                    int status=0;
                    System.out.println("votep : cc");
                    while ((output = bufread.readLine()) != null) {
                      obj = parser.parse(output);
                      jsonobj = (JSONObject) obj;
                       t = String.valueOf(jsonobj.get("status"));
                       status = Integer.valueOf(t);
                       System.out.println(t+"|"+status+"|");
                    }      
                    conn.disconnect();
                     if (status==-2) System.out.println("votep : token tidak valid");
                     else if (status==-3) System.out.println("votep : you are accessing from different ip");
                     else if (status==-4) System.out.println("You are using different browser");
                     else if (status==-999) System.out.println("You are already vote this one");
                    } catch (MalformedURLException e) {
                          e.printStackTrace();
                    } catch (IOException e) {
                          e.printStackTrace();
                   } catch (ParseException ex) {
                        Logger.getLogger(commentp.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
             System.out.println("votep : dd");
             int id_ = Integer.valueOf(id);
             if (id_>1000) id_= id_/1000;
             response.sendRedirect("open.jsp?id="+id_);
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
