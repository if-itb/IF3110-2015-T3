/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginIS;

import ConnectionIS.ConnectionIS;
import model.user.UserWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import model.user.User;
import model.user.UserWS_Service;
import org.json.simple.JSONObject;
import java.sql.Timestamp;

/**
 *
 * @author HP
 */
@WebServlet(name = "LoginIS", urlPatterns = {"/login"})
public class LoginIS extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginIS</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginIS at " + request.getContextPath() + "</h1>");
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
        User user = (User) request.getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath());
        } else {
            HttpSession session = request.getSession(false);
            String error = null;
            if (session != null && (error = (String) session.getAttribute("error")) != null) {
                request.setAttribute("error", error);
                session.removeAttribute("error");
            }
            request.getRequestDispatcher("/login.jsp").forward(request, response);
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
        User userlogin = (User) request.getAttribute("user");
        if (userlogin!=null)
        {
            response.sendRedirect(request.getContextPath());
            return;
        }
        else
        {          
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            JSONObject json = null;
            if (email!=null && password != null && (json = ConnectionIS.requestLogin(email,password))!=null)
            {
                if (json.containsKey("token"))
                {
                    Cookie cookie = new Cookie("stackexchange_token", (String)json.get("token"));
                    cookie.setPath("/");
                    long umur= -1;
                    if (json.containsKey("token_expired"))
                    {
                        umur = new Timestamp(new Date().getTime()).getTime() - (long) json.get("token_expired");
                        umur/=1000;
                    }
                    cookie.setMaxAge((int) umur);
                    response.addCookie(cookie);
                    response.sendRedirect(request.getContextPath());
                    return;
                }
                else 
                {
                    if(json.containsKey("ERROR"))
                    {
                        request.setAttribute("error", (String) json.get("ERROR"));
                    }
                }
            }
        }
        doGet(request, response);
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
