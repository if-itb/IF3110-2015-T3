package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class logout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        boolean found = false;
        int i = 0;
        String uname = "";
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if (cookies == null)
            response.sendRedirect("index");
        while (!found && i<cookies.length){
            if (cookies[i].getName().equals("usernameCookie")) {
                uname = cookies[i].getValue();
                found = true;
            }
            i++;
        }
        
        Form form = new Form();
        form.param("uname", uname);
       
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:21568/StackExchangeAuth/deleteToken";
        
        try {
            String result = client.target(url).request(MediaType.APPLICATION_XML).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED), String.class);
            
            int count = 0;
            i = 0;
            while (count<2 && i<cookies.length){
                if (cookies[i].getName().equals("usernameCookie") || cookies[i].getName().equals("tokenCookie")) {
                    cookies[i].setMaxAge(0);
                    cookies[i].setPath("/");
                    response.addCookie(cookies[i]);
                    count++;
                }
                i++;
            }
            
            response.sendRedirect("index");
        }
        catch (Exception e) {}
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
