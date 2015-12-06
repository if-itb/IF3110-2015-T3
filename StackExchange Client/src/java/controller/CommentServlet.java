/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/comment"})
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
        int id = Integer.parseInt(request.getParameter("question_id"));
        System.out.println(id);
        try (PrintWriter out = response.getWriter()){
            URL obj = new URL("http://localhost:8083/comment?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            
            //TRUS INI NGAPAIN LAGI HAHAHAHAHAHAH
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer responses = new StringBuffer();
            String currentLine;

            while ((currentLine = in.readLine()) != null) {
                responses.append(currentLine);
            }
            in.close();

            //print result
            out.println(responses.toString());
           
        } catch (IOException e) {
            e.printStackTrace();
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
        JSONParser parser = new JSONParser();
        try (PrintWriter out = response.getWriter()) {
            String auth=null; //INI DAPET DARI MANA???
            String charset = "UTF-8";
            String content = request.getParameter("content");
            String id_question=request.getParameter("question_id"); //GIMANA CARA DAPETNYAA
                   
            URL url = new URL("http://localhost:8083/comment");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
           
            String query = String.format("auth=%s&id_question=%s&content=%s",
                    URLEncoder.encode(auth, charset),
                    URLEncoder.encode(id_question, charset),
                    URLEncoder.encode(content, charset));
            
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                out.println(output);
            }
            connection.disconnect();
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
