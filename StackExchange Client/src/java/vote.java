/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import stackexchangews.Operation_Service;

/**
 *
 * @author Raihan
 */
@WebServlet(urlPatterns = {"/vote"})
public class vote extends HttpServlet {    
    private Operation_Service service;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vote</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>");  
            boolean mode = Boolean.parseBoolean(request.getParameter("mode")) ;
            int val = Integer.parseInt(request.getParameter("val").trim());
            String id = request.getParameter("id");            
            String token = null;
            Cookie[] cookies = request.getCookies();                
                if (cookies != null) {
                    for (Cookie cookie : cookies) {                        
                        if (cookie.getName().equals("user_token")) {
                            token = cookie.getValue();                             
                        }
                    }
                }            
            out.println(token);
            out.println(getUIDbyToken(token));
            out.println(mode);
            out.println(id);
            out.println(val);
            out.println("<br>A");
            vote(token,true,"8",1);
            out.println("B");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
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

    private void vote(java.lang.String token, boolean mode, java.lang.String idq, int val) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        stackexchangews.Operation port = service.getOperationPort();
        port.vote(token, mode, idq, val);
    }

    private int getUIDbyToken(java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        stackexchangews.Operation port = service.getOperationPort();
        return port.getUIDbyToken(token);
    }


}
