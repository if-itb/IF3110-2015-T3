/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.servlet;

import UserWS.User;
import UserWS.UserWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author zulvafachrina
 */
public class Register extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8083/Stack_Exchange_WS/UserWS.wsdl")
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
            
        String token = "";
        Cookie[] cookie = request.getCookies();
        
        for(Cookie obj : cookie){
                //out.println(obj.getName());
                if(obj.getName().equals("token")){
                token = obj.getValue();
                //out.println(obj.getValue());
                break;
            }
	}
        request.setAttribute("token",token);
            
            /* TODO output your page here. You may use following sample code. */
            String username= request.getParameter("username");
            String password= request.getParameter("password");
            String email= request.getParameter("email");
            
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPassword(password);
        
        int success = registerUser(user);
        if(success==1) {
            request.setAttribute("registrasi", 1);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else {
            request.setAttribute("registrasi", 0);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
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

    private int registerUser(UserWS.User user) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        UserWS.UserWS port = service.getUserWSPort();
        return port.registerUser(user);
    }

}
