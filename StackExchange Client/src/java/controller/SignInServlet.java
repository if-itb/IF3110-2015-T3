/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.ISConnector;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.json.simple.JSONObject;
import service.*;

/**
 *
 * @author visat
 */

@WebServlet(name = "SignInServlet", urlPatterns = {"/signin"})
public class SignInServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange.wsdl")
    private StackExchange_Service service;
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
            request.getRequestDispatcher("WEB-INF/view/signin.jsp").forward(request, response);
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

        User user = (User) request.getAttribute("user");
        if (user != null) { // user already login
            response.sendRedirect(request.getContextPath());
            return;
        }
        else { // user can login
            String  email = request.getParameter("email"),
                    password = request.getParameter("password");

            JSONObject object = null;
            if (email != null && password != null && (object = ISConnector.requestLogin(email, password)) != null) {
                if (object.containsKey("auth")){
                    Cookie cookie = new Cookie("auth", (String)object.get("auth"));
                    cookie.setPath("/");
                    long age = -1;
                    if (object.containsKey("expire_date")) {
                        age = new Timestamp(new Date().getTime()).getTime()-(long)object.get("expire_date");
                        age /= 1000;
                    }
                    cookie.setMaxAge((int)age);
                    response.addCookie(cookie);
                    response.sendRedirect(request.getContextPath());
                    return;
                } else if (object.containsKey("error")) {
                    request.setAttribute("error", (String)object.get("error"));
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
