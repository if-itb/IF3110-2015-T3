/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import ClientValidate.ClientValidate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import user.UserWS_Service;

/**
 *
 * @author Ahmad Naufal Farhan
 */
public class Logout extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/UserWS.wsdl")
    private UserWS_Service service;

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
        String token =""; 
        boolean found = false; 
        int i = 0; 
        Cookie[] cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent").replace(';', '%');// Ambil user agent dari client
        useragent = useragent.replace(',', '$');
        String ipAddress = request.getHeader("X-FORWARDED-FOR");    // ** Ambil IP Address Client
        if (ipAddress == null)
           ipAddress = request.getRemoteAddr();  
//        
        token = ClientValidate.tokenExtract(cookies);
        
        if (token != null) {
            int res = logoutUser(token, ipAddress, useragent);
            if (res > 0) {
                Cookie cookie = new Cookie("token_cookie", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        response.sendRedirect("home");
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

    private int logoutUser(java.lang.String token, java.lang.String ipAddress, java.lang.String useragent) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service.getUserWSPort();
        return port.logoutUser(token, ipAddress, useragent);
    }


}
