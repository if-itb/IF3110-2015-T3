/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/Call"})
public class Call extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/xml;charset=UTF-8");
        
        Enumeration<String> params = request.getParameterNames();
        int numberOfParams = 0;
        while(params.hasMoreElements()) {
            numberOfParams++;
            params.nextElement();
        }
        
        if (numberOfParams == 3) {
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
        
            Form form = new Form();
            form.param("uname", uname);
            form.param("pass", pass);
       
            Client client = ClientBuilder.newClient();
            String url = "http://localhost:21568/StackExchangeAuth/getToken";
        
            try {
                String result = client.target(url).request(MediaType.TEXT_PLAIN).post(Entity.entity(form,
                    MediaType.APPLICATION_FORM_URLENCODED), String.class);
            
                PrintWriter tw = response.getWriter();
        
                tw.println(result);
                tw.close();
            }
                catch (Exception e) {
            }
        }
        else if (numberOfParams == 2) {
            String token_string = request.getParameter("token_string");
        
            Form form = new Form();
            form.param("token_string", token_string);
       
            Client client = ClientBuilder.newClient();
            String url = "http://localhost:21568/StackExchangeAuth/validateToken";
        
            try {
                String result = client.target(url).request(MediaType.TEXT_PLAIN).post(Entity.entity(form,
                    MediaType.APPLICATION_FORM_URLENCODED), String.class);
            
                PrintWriter tw = response.getWriter();
        
                tw.println(result);
                tw.close();
            }
                catch (Exception e) {
            }
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
