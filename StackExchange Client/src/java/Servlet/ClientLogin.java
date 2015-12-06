/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author vickonovianto
 */
public class ClientLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    class User {
        private String email;
        private String password;
        
        public void setEmail(String newEmail) { email = newEmail; }
        public void setPassword(String newPassword) { password = newPassword; }
    }
    
    class Status {
        private boolean success;
        private String error_cause;
        private String access_token;
        private Integer lifetime;
        public boolean getSuccess() { return success; };
        public String getErrorCause() { return error_cause; };
        public String getAccessToken() { return access_token; };
        public int getLifetime() { return lifetime.intValue(); };
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
        //processRequest(request, response);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Gson gson = new Gson();
        
        try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            StringBuilder sb = new StringBuilder();
            sb.append(gson.toJson(user));
            
            URL url = new URL("http://localhost:8082/IdentityService/login");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + sb.length());

            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(sb.toString());
            outputWriter.flush();
            outputWriter.close();
            
            int responseCode = connection.getResponseCode();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder stringStatus = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
		stringStatus.append(inputLine);
            }
            in.close();
            
            Status status = (Status) gson.fromJson(stringStatus.toString(), Status.class);
            
            if (status.getSuccess()) {
                String token = status.getAccessToken();
                request.setAttribute("success", "true");
                request.setAttribute("token", token);
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/loginProcess.jsp");
                rd.forward(request, response);
            }
            else {
                request.setAttribute("success", "false");
                request.setAttribute("error_cause", status.getErrorCause());
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/loginProcess.jsp");
                rd.forward(request, response);
            }
               
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
