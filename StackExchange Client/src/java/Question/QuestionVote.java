/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import QuestionWS.QuestionWS_Service;
import Tools.Tools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Asus
 */
@WebServlet(name = "QuestionVote", urlPatterns = {"/questionvote"})
public class QuestionVote extends HttpServlet {
  @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
  private QuestionWS_Service service;

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
            Tools tools = new Tools();
            String access_token = tools.getCookie("access_token", request);

            String userIP = request.getHeader("X-FORWARDED-FOR");
            if (userIP == null) {
                userIP = request.getRemoteAddr();
            }
            String userAgent = request.getHeader("User-Agent");
            
            String charset = "UTF-8";
            String uid = request.getParameter("uid");
            String qid = request.getParameter("qid");
            String value = request.getParameter("value");
            System.out.println(">>>> " + uid + "|" + qid + "|" + value);
            URL url = new URL("http://localhost:8083/CV_Service/vote/question");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            String query = String.format("uid=%s&qid=%s&value=%s&token=%s&userIP=%s&userAgent=%s",
                    URLEncoder.encode(uid, charset),
                    URLEncoder.encode(qid, charset),
                    URLEncoder.encode(value, charset),
                    URLEncoder.encode(access_token, charset),
                    URLEncoder.encode(userIP, charset),
                    URLEncoder.encode(userAgent, charset));
            
            try (OutputStream output = conn.getOutputStream()) {
                output.write(query.getBytes(charset));
            }
            
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
