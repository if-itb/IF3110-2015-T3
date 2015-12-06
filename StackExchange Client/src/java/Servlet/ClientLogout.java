/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class ClientLogout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    class Status {
        private boolean success;
        private String description;
        public boolean getSuccess() { return success; };
        public String getDescription() { return description; };
    }
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Gson gson = new Gson();
            URL url = new URL("http://localhost:8082/IdentityService/LogoutServlet?token=" + request.getParameter("token"));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
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
                request.setAttribute("success", "true");
                request.setAttribute("token", null);
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/logoutProcess.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("success", "false");                
                request.setAttribute("token", null);
                request.setAttribute("description", status.getDescription());
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/logoutProcess.jsp");
                rd.forward(request, response);
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
        //processRequest(request, response);
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
